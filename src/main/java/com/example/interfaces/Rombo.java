package com.example.interfaces;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Rombo {
    private Rectangle rectangulo;
    private Label label;

    public Rombo() {
        this.rectangulo = new Rectangle(30,30);
        rectangulo.setRotate(45);
        this.label = new Label();
        Arrastreador.makeArrastrable(this.rectangulo);
        Bindings.bindBidirectional(this.label.layoutXProperty(), this.rectangulo.layoutXProperty());
        Bindings.bindBidirectional(this.label.layoutYProperty(), this.rectangulo.layoutYProperty());
        this.rectangulo.setFill(javafx.scene.paint.Color.rgb(204, 196, 177));
    }

    public void setPosicion(double x, double y) {
        this.label.translateXProperty().bind(this.rectangulo.translateXProperty().add(20));
        this.label.translateYProperty().bind(this.rectangulo.translateYProperty().subtract(20));
        this.rectangulo.setLayoutX(x);
        this.rectangulo.setLayoutY(y);
    }

    public void setTexto(String texto) {
        this.label.setText(texto);
    }

    public Rectangle getRombo() {
        return this.rectangulo;
    }

    public Node getLabel() {
        return this.label;
    }

}


