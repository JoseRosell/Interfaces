package com.example.interfaces;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Cuadrado {
    private Rectangle cuadrado;
    private Label label;

    public Cuadrado(double x, double y) {
        this.label = new Label();
        this.cuadrado = new Rectangle(60,30);
        Arrastreador.makeArrastrable(this.cuadrado);
        Bindings.bindBidirectional(this.label.layoutXProperty(), this.cuadrado.layoutXProperty());
        Bindings.bindBidirectional(this.label.layoutYProperty(), this.cuadrado.layoutYProperty());
        this.cuadrado.setFill(javafx.scene.paint.Color.rgb(204, 196, 177));
        this.cuadrado.setLayoutX(x);
        this.cuadrado.setLayoutY(y);
    }

    public void setPosicion(double x, double y) {
        this.label.translateXProperty().bind(this.cuadrado.translateXProperty());
        this.label.translateYProperty().bind(this.cuadrado.translateYProperty());
        this.cuadrado.setLayoutX(x);
        this.cuadrado.setLayoutY(y);
    }

    public void setTexto(String texto) {
        this.label.setText(texto);
        this.cuadrado.setWidth(label.getText().length()*8);
    }

    public Rectangle getCuadrado() {
        return this.cuadrado;
    }

    public Node getLabel() {
        return this.label;
    }

}


