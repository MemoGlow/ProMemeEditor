package com.promeme.model;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class EditableText extends Label implements Serializable{
    private void readObject(ObjectInputStream ois) throws IOException {
        this.setText(ois.readUTF());
        Font font = new Font(ois.readUTF(), ois.readDouble());
        this.setFont(font);
        this.setTextFill(Paint.valueOf(ois.readUTF()));
        this.setLayoutX(ois.readDouble());
        this.setLayoutY(ois.readDouble());
    }
    private void writeObject(ObjectOutputStream ous) throws IOException {
        ous.writeUTF(this.getText());
        ous.writeUTF(this.getFont().getFamily());
        ous.writeDouble(this.getFont().getSize());
        ous.writeUTF(String.valueOf(this.getTextFill()));
        ous.writeDouble(this.getLayoutX());
        ous.writeDouble(this.getLayoutY());
    }
    public void setProperty(EditableText text){
        this.setText(text.getText());
        this.setLayoutX(text.getLayoutX());
        this.setLayoutY(text.getLayoutY());
        this.setFont(text.getFont());
        this.setTextFill(text.getTextFill());
        this.setBackground(null);
    }
    public void transformByScale(double scale){
        this.setFont(new Font(this.getFont().getFamily(), this.getFont().getSize() * scale));
        this.setLayoutX(this.getLayoutX() * scale);
        this.setLayoutY(this.getLayoutY() * scale);
    }
}
    