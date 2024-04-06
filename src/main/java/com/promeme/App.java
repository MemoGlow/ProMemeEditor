package com.promeme;

import com.promeme.model.EditableImage;
import com.promeme.model.EditableText;
import com.promeme.view.EditableImageView;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.text.AttributeSet;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(EditableImageView.class.getResource("library-view.fxml"));
//        FXMLLoader fxml = new FXMLLoader(EditableImageView.class.getResource("editable-image-view.fxml"));
        Parent root = fxml.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMinHeight(550);
        stage.setMinWidth(835);
        stage.show();

//        EditableImage editableImage = new EditableImage();
//        editableImage.setImagePath("C:\\Users\\MemoGlow\\AppData\\Roaming\\Microsoft\\Windows\\Network Shortcuts\\hehe.png");
//        EditableText text = new EditableText();
//        text.setText("Hello");
//        text.setFont(new Font(100));
//        text.setFill(Color.RED);
//        editableImage.getTexts().add(text);
//        ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(new File("sample")));
//        System.out.println(editableImage.getImagePath());
//        System.out.println(editableImage.getTexts().size());
//        System.out.println(editableImage.getTexts().get(0));

//        ous.writeObject(editableImage);
//        ous.close();
//        editableImage = null;
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("sample")));
//        editableImage = (EditableImage) ois.readObject();
//        System.out.println(editableImage.getImagePath());
//        System.out.println(editableImage.getTexts().size());
//        System.out.println(editableImage.getTexts().get(0));
//        ois.close();
//        EditableText text = new EditableText();
//        text.setText("Hello ");
//        ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("sample"));
//        text.setX(23.5);
//        ous.writeObject(text);
//        System.out.println(text);
//        text = null;
//        ous.close();
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sample"));
//        text = (EditableText) ois.readObject();
//        System.out.println(text);
//        System.out.println();



//        ArrayList<EditableText> texts = new ArrayList<EditableText>();
//        EditableText textToAdd = new EditableText();
//        textToAdd.setText("Hell world");
//        texts.add(textToAdd);
//        EditableImage editableImage =  new EditableImage();
////        editableImage.setImagePath();
//        editableImage.setTexts(texts);
//        System.out.println(editableImage.getTexts().get(0));
//        ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("sample"));
//        ous.writeObject(editableImage);
//        ous.close();
//
//        editableImage = null;
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sample"));
//        editableImage = (EditableImage) ois.readObject();
//        System.out.println(editableImage.getTexts().get(0));
//        System.out.println(editableImage);

//        AnchorPane pane = new AnchorPane();
//        Label label = new Label("Hello world");
//        pane.getChildren().add(label);
//        WritableImage wb = pane.snapshot(null, null);
//        File file = new File("example.png");
//        ImageIO.write(SwingFXUtils.fromFXImage(wb, null), "png", file);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
