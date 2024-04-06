package com.promeme.ulti;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class DraggableMaker {
    double anchorX;
    double anchorY;
    public void makeDraggable(Node node){
        node.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                anchorX = mouseEvent.getX();
                anchorY = mouseEvent.getY();
            }
        });
        node.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                node.setLayoutX(node.getLayoutX() + mouseEvent.getX() - anchorX);
                node.setLayoutY(node.getLayoutY() + mouseEvent.getY() - anchorY);
            }
        });
    }
}
