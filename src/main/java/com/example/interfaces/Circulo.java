package com.example.interfaces;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Circulo {
    private Circle circulo;
    private Label label;

    public Circulo(int radio) {
        this.circulo = new Circle(radio);
        this.label = new Label();
        Bindings.bindBidirectional(this.label.layoutXProperty(), this.circulo.layoutXProperty());
        Bindings.bindBidirectional(this.label.layoutYProperty(), this.circulo.layoutYProperty());
        this.circulo.setStroke(Color.BLACK);
        this.circulo.setFill(Color.WHITE);
        this.circulo.setStrokeWidth(1);
        Arrastreador.makeArrastrable(this.circulo);
    }

    public void setPosicion(double x, double y) {
        this.label.translateXProperty().bind(this.circulo.centerXProperty().add(20));
        this.label.translateYProperty().bind(this.circulo.centerYProperty().subtract(20));
        this.circulo.setLayoutX(x);
        this.circulo.setLayoutY(y);
    }

    public void setTexto(String texto) {
        this.label.setText(texto);
    }

    public Circle getCirculo() {
        return this.circulo;
    }

    public Label getLabel() {
        return this.label;
    }

    public double getX() {
        return this.circulo.getCenterX();
    }
    public double getY() {
        return this.circulo.getCenterY();
    }

}


