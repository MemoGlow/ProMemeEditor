package com.promeme.ulti;

import com.promeme.model.EditableText;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;

public class PaneScaler {
    AnchorPane pane;
    public PaneScaler(AnchorPane pane){
        this.pane = pane;
    }
    public void makeScale(double oldScale, double newScale){
        for(Node node : pane.getChildren()){
            if(node instanceof ImageView){
                ImageView imageView = (ImageView) node;
                imageView.setFitHeight(imageView.getImage().getHeight() * newScale);
                imageView.setFitWidth(imageView.getImage().getWidth() * newScale);
            }else if(node instanceof EditableText){
                EditableText text = (EditableText) node;
                text.transformByScale(1 / oldScale * newScale);
            }
        }
    }
}
