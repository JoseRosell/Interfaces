package com.example.interfaces;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class Circulo {
    private Circle circulo;
    private Label label;

    public Circulo(int radio, double x, double y) {
        this.circulo = new Circle(radio);
        this.label = new Label();
        Arrastreador.makeArrastrable(this.circulo);
        Bindings.bindBidirectional(this.label.layoutXProperty(), this.circulo.layoutXProperty());
        Bindings.bindBidirectional(this.label.layoutYProperty(), this.circulo.layoutYProperty());
        this.circulo.setFill(javafx.scene.paint.Color.rgb(204, 196, 177));
        this.circulo.setCenterX(x);
        this.circulo.setCenterY(y);
    }

    public void setPosicion(double x, double y) {
        this.label.translateXProperty().bind(this.circulo.centerXProperty().add(20));
        this.label.translateYProperty().bind(this.circulo.centerYProperty().subtract(20));
        this.circulo.setCenterX(x);
        this.circulo.setCenterY(y);
    }

    public void setTexto(String texto) {
        this.label.setText(texto);
    }

    public Circle getCirculo() {
        return this.circulo;
    }

    public Node getLabel() {
        return this.label;
    }

}


