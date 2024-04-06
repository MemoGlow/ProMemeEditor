package com.promeme.view;

import com.promeme.controller.EditableImageController;
import com.promeme.model.EditableText;
import com.promeme.model.EditableTextList;
import com.promeme.ulti.DraggableMaker;
import com.promeme.ulti.PaneScaler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EditableImageView {
    public final int EDIT = 3;
    public final int NORMAL = 1;
    public final int ADD = 2;
//    public final int
    private EditableText currentText;
    private EditableTextList texts;
    private ImageView imageView;

    public EditableTextList getTexts() {
        return texts;
    }

    public void setTexts(EditableTextList texts) {
        this.texts = texts;
    }
    private Image image;

    public Image getImage() {
        return image;
    }

    @FXML
    HBox alignBox;
    @FXML
    ComboBox<String> colorMenu;

    @FXML
    TextField sizeField;
    @FXML
    private Label modeLabel;
    int mode;
    @FXML
    TextArea content;
    @FXML
    private TextField scaleField;
    @FXML
    AnchorPane centerPane;
    @FXML
    BorderPane pane;
    private double scale;

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    private EditableImageController editableImageController;
    @FXML
    AnchorPane imageHolder;
    @FXML
    ComboBox<String> fontFamilyMenu;

    @FXML
    public void setOnOpenFromImageButton() throws FileNotFoundException {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        image = new Image(new FileInputStream(file));
        imageView.setImage(image);
        editableImageController.getEditableImage().setImagePath(file.getPath());
        loadImage(file);
    }
    public void loadImage(File file) throws FileNotFoundException {

        imageHolder.getChildren().clear();
        texts.clear();
        editableImageController.getEditableImage().getTexts().clear();
//        editableImageController.getEditableImage().setImagePath();
        this.image = new Image(new FileInputStream(file));
        editableImageController.getEditableImage().setImagePath(file.getPath());
        imageView.setImage(image);
        initializeImageViewProperties(imageView);
        scale = imageView.getFitWidth() / imageView.getImage().getWidth();
        scaleField.setText(String.valueOf(imageView.getFitWidth() * 100 / imageView.getImage().getWidth()));

        imageHolder.getChildren().add(imageView);
    }
    public void loadProject() throws FileNotFoundException {
        imageHolder.getChildren().clear();
        texts.clear();

        this.image = new Image(new FileInputStream(new File(editableImageController.getEditableImage().getImagePath())));

        imageView.setImage(image);
        initializeImageViewProperties(imageView);
        imageHolder.getChildren().add(imageView);

        scale = imageView.getFitWidth() / imageView.getImage().getWidth();
        scaleField.setText(String.valueOf(imageView.getFitWidth() * 100 / imageView.getImage().getWidth()));
        for(EditableText text : editableImageController.getEditableImage().getTexts()){
            EditableText textToAdd = new EditableText();
            textToAdd.setProperty(text);
            textToAdd.transformByScale(scale);
            setUpOnScreenTextBehavior(textToAdd);
            imageHolder.getChildren().add(textToAdd);
            texts.add(textToAdd);
        }
    }

    @FXML
    public void initialize() {
        assert imageHolder != null : "fx:id=\"imageHolder\" was not injected: check your FXML file 'editable-image-view.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'editable-image-view.fxml'.";

        // Khởi tạo combo box font family
        fontFamilyMenu.getItems().addAll(Font.getFamilies());
        fontFamilyMenu.setValue("Times New Roman");

        // khởi tạo combo box cho text fill color
        colorMenu.getItems().addAll(            "Red",
                "Green",
                "Blue",
                "Yellow",
                "Orange",
                "Purple",
                "Pink",
                "Cyan",
                "Black");

        // Khởi tạo controller cho editable image


        // Khởi tạo các thuộc tính ban đầu cho text
        colorMenu.setValue("Black");
        sizeField.setText("12.0");

        mode = 1;
        // Khởi tạo các thuộc tính còn lại
        texts = new EditableTextList();
        editableImageController = new EditableImageController(this);

        imageView = new ImageView();

        centerPane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                alignBox.heightProperty().add(centerPane.heightProperty());
                System.out.println(alignBox.getHeight() + " " + alignBox.getWidth());
            }
        });
        centerPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                alignBox.widthProperty().add(centerPane.widthProperty());
                System.out.println(alignBox.getHeight() + " " + alignBox.getWidth());
            }
        });
    }
    @FXML
    public void setOnAddTextButton(){
        setMode(ADD);
    }

    @FXML
    void setOnImageHolderClicked(MouseEvent event) {
        if (mode == ADD) {
            EditableText text = new EditableText();
            text.setLayoutX(event.getX());
            text.setLayoutY(event.getY());
            loadTextPropertiesFromUser(text);
            imageHolder.getChildren().add(text);
            texts.add(text);
            setUpOnScreenTextBehavior(text);
            setMode(NORMAL);
        }
    }

    public void loadTextPropertiesFromUser(EditableText text) {
        double size;
        try {
            size = Double.parseDouble(sizeField.getText());
        } catch (NumberFormatException ex) {
            size = 12;
        }
        text.setFont(new Font(fontFamilyMenu.getValue(), size));
        if(colorMenu.getValue() != null){
            switch (colorMenu.getValue()) {
                case "Red":
                    text.setTextFill(Color.RED);
                    break;
                case "Green":
                    text.setTextFill(Color.GREEN);
                    break;
                case "Blue":
                    text.setTextFill(Color.BLUE);
                    break;
                case "Yellow":
                    text.setTextFill(Color.YELLOW);
                    break;
                case "Orange":
                    text.setTextFill(Color.ORANGE);
                    break;
                case "Purple":
                    text.setTextFill(Color.PURPLE);
                    break;
                case "Pink":
                    text.setTextFill(Color.PINK);
                    break;
                case "Cyan":
                    text.setTextFill(Color.CYAN);
                    break;
                case "Black":
                    text.setTextFill(Color.BLACK);
                    break;
            }
        }
        text.setText(content.getText());
        sizeField.setText(String.valueOf(size));
        System.out.println(text);
    }

    @FXML
    void setOnUndoButton(ActionEvent event) {
        if (imageHolder.getChildren().size() > 0) {
            imageHolder.getChildren().remove(imageHolder.getChildren().size() - 1);
        }
    }

    @FXML
    void setOnExportButton() throws IOException {
        File file = new FileChooser().showSaveDialog(null);
        editableImageController.export(file);
    }

    @FXML
    void setOnFitButton() {
        double oldScale = scale;
        initializeImageViewProperties(imageView);
        System.out.println(imageView.getFitWidth() + " " + imageView.getFitHeight());
        scale = imageView.getFitWidth() / image.getWidth();
        scaleField.setText(String.valueOf(scale * 100));
        new PaneScaler(imageHolder).makeScale(oldScale, scale);
    }

    @FXML
    void setOnContentTyped() {
        if (currentText != null) {
            currentText.setText(content.getText());
        }
    }

    @FXML
    void setOnFontSizeTyped(KeyEvent event) {
        if(currentText != null){
            String sizeValue = null;
            if (event.getCode() == KeyCode.ENTER) {
                sizeValue = new String(sizeField.getText());
            } else {
                return;
            }
            double size;
            try {
                size = Double.parseDouble(sizeValue);
            } catch (Exception e) {
                size = 12;
            }
            loadTextPropertiesFromUser(currentText);
        }
    }
    @FXML
    void setOnDeleteButton() {
        if(currentText != null){
            texts.remove(currentText);
            imageHolder.getChildren().remove(currentText);
        }
    }
    @FXML
    void setOnFontChange(){
        if(mode == EDIT){
            loadTextPropertiesFromUser(currentText);
        }
    }
    @FXML
    void setOnColorChange(){
        if(mode == EDIT){
            loadTextPropertiesFromUser(currentText);
        }
    }
    @FXML
    void setOnIncreaseFontSizeButton(ActionEvent event) {
        double size = Double.parseDouble(sizeField.getText());
        size *= 1.05;
        sizeField.setText(String.valueOf(size));
        if(mode == EDIT){
            loadTextPropertiesFromUser(currentText);
        }
    }

    @FXML
    void setOnDecreaseFontSizeButton(ActionEvent event) {
        double size = Double.parseDouble(sizeField.getText());
        size *= 0.95;
        sizeField.setText(String.valueOf(size));
        if(mode == EDIT){
            loadTextPropertiesFromUser(currentText);
        }
    }
    @FXML
    public void setOnSaveButton() throws IOException {
        File file = new FileChooser().showSaveDialog(null);
        editableImageController.saveToProjectFile(file);
    }
    @FXML
    void setOnOpenFromProjectButton(ActionEvent event) throws IOException, ClassNotFoundException {
        File file = new FileChooser().showOpenDialog(null);
        editableImageController.openFromProjectFile(file);
        loadProject();
    }
    void setMode(int mode){
        this.mode = mode;
    }

    public void setUpOnScreenTextBehavior(EditableText text){
        new DraggableMaker().makeDraggable(text);
        text.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(text.getBackground() != null){
                    currentText = null;
                    updateOnScreenProperties();
                    text.setBackground(null);
                }
                if (event.getClickCount() == 2) {
                    if(currentText != null) currentText.setBackground(null);
                    System.out.println("Hello");
                    text.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null,null)));
                    setMode(EDIT);
                    currentText = text;
                    updateOnScreenProperties();
                }
            }
        });

    }
    public void updateOnScreenProperties(){
        if(currentText != null){
            content.setText(currentText.getText());
            sizeField.setText(String.valueOf(currentText.getFont().getSize()));
            fontFamilyMenu.setValue(currentText.getFont().getFamily());
            colorMenu.setValue(currentText.getTextFill().toString());
        }
    }

    public void initializeImageViewProperties(ImageView imageView){
        imageView.setPreserveRatio(true);
        double imageRatio = imageView.getImage().getHeight() / imageView.getImage().getWidth();
        double paneRatio = centerPane.getHeight() / centerPane.getWidth();
        if(imageRatio > paneRatio){
            imageView.setFitHeight(centerPane.getHeight());
            imageView.setFitWidth(centerPane.getHeight() / imageRatio);
        }else{
            imageView.setFitWidth(centerPane.getWidth());
            imageView.setFitHeight(centerPane.getWidth() * imageRatio);
        }

    }
    @FXML
    void setOnDecreaseImageHolderSize(ActionEvent event) {
        double oldScale = scale;
        scale *= 0.9;
        scaleField.setText(String.valueOf(scale * 100));
        new PaneScaler(imageHolder).makeScale(oldScale, scale);
    }
    @FXML
    void setOnIncreaseImageHolderSize(ActionEvent event) {
        double oldScale = scale;
        scale *= 1.1;
        scaleField.setText(String.valueOf(scale * 100));
        new PaneScaler(imageHolder).makeScale(oldScale, scale);
    }
}
