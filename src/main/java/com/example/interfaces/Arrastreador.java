package com.example.interfaces;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class Arrastreador {
    static private double mouseX;
    static private double mouseY;

    public static void makeArrastrable(Node node) {

        node.setOnMousePressed(mouseEvent -> {
            mouseX = mouseEvent.getX();
            mouseY = mouseEvent.getY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseX);
            node.setLayoutY(mouseEvent.getSceneY() - mouseY);
        });
    }
}
