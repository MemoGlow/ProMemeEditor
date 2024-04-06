package com.promeme.view;

import com.promeme.controller.LibraryController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class LibraryView {
    LibraryController libraryController;
    @FXML
    private GridPane directoriesPane;
    @FXML
    TextField pageField;
    AnchorPane mainPane;
    @FXML
    private GridPane gridPane;
    private int currentPage;
    private File currentDirectory;
    private ArrayList<File> currentImagePaths = new ArrayList<File>();

    @FXML
    ImageView logo;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {


        assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'library-view.fxml'.";
        logo.setImage(new Image(Path.of("Material//logo pro@8x.png").toUri().toURL().toExternalForm(), 150, 0, true, true, true));
        libraryController = new LibraryController(this);
//        libraryController.loadData();

        gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
        gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
        gridPane.setMaxWidth(gridPane.getPrefWidth());

        gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
        gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
        gridPane.setMaxHeight(gridPane.getPrefHeight());

        try{
            libraryController.loadData();
        }catch (Exception e){
            libraryController.addDirectory(new File("MemeImage"));
        }
        ArrayList<File> directories = libraryController.getLibrary().getDirectories();
        currentDirectory = directories.getFirst();
        libraryController.writeData();
        for (File fileToAdd : directories) {
            addDirectoryToPanel(fileToAdd);
        }
        currentPage = 1;
        loadItemInPage(currentPage);
    }

    public void loadItemInPage(int page) throws IOException {
        int row = 1, col = 0;
        gridPane.getChildren().clear();
        loadCurrentDirectory();
        for (int i = 9 * (page - 1); i < Integer.min(9 * page, currentImagePaths.size()); i++) {
            FXMLLoader itemLoader = new FXMLLoader(ItemView.class.getResource("item.fxml"));

            VBox item = itemLoader.load();
            ItemView itemView = itemLoader.getController();
            itemView.setData(currentImagePaths.get(i));
            gridPane.add(item, col++, row);
            GridPane.setMargin(item, new Insets(10));
            if (col == 3) {
                row++;
                col = 0;
            }
        }

        pageField.setText(String.valueOf(currentPage));
    }

    @FXML
    void setOnNextPageButton(ActionEvent event) throws IOException {
        currentPage++;
        try {
            loadItemInPage(currentPage);
        } catch (Exception e) {
            currentPage--;
            loadItemInPage(currentPage);
        }
    }

    @FXML
    void setOnPreviousePageButton(ActionEvent event) throws IOException {
        currentPage--;
        try {
            loadItemInPage(currentPage);
        } catch (Exception e) {
            currentPage++;
            loadItemInPage(currentPage);
        }
    }

    @FXML
    void setOnPageType(KeyEvent event) throws IOException {
        int oldPage = currentPage;
        if (event.getCode() == KeyCode.ENTER) {
            try {
                currentPage = Integer.parseInt(pageField.getText());
                loadItemInPage(currentPage);
            } catch (Exception e) {
                currentPage = oldPage;
                loadItemInPage(currentPage);
            }
        }
    }

    public void loadCurrentDirectory() {
        File files[] = currentDirectory.listFiles();
        currentImagePaths.clear();
        for (File file : files) {
            currentImagePaths.add(file);
        }
    }

    @FXML
    public void setOnAddDirectoryButton() throws IOException {
        File file = new DirectoryChooser().showDialog(null);
        libraryController.addDirectory(file);
        addDirectoryToPanel(file);
    }

    public void addDirectoryToPanel(File file) throws IOException {
        Label label = new Label(file.getName());
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                currentDirectory = file;
                try {
                    loadItemInPage(1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        directoriesPane.add(label, 0, directoriesPane.getChildren().size());

    }
    @FXML
    public void setOnOpenImageEditor() throws IOException {
        FXMLLoader editableImageloader = new FXMLLoader(EditableImageView.class.getResource("editable-image-view.fxml"));

        AnchorPane pane = editableImageloader.load();

        Scene scene = new Scene(pane);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
