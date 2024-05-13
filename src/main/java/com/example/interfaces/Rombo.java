package com.example.interfaces;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Rombo {
    private Rectangle rectangulo;
    private Label label;
    private Label labeli;
    private Label labeld;


    public Rombo() {
        this.rectangulo = new Rectangle(30,30);
        rectangulo.setRotate(45);
        this.label = new Label();
        this.labeli = new Label();
        this.labeld = new Label();
        Arrastreador.makeArrastrable(this.rectangulo);
        Bindings.bindBidirectional(this.label.layoutXProperty(), this.rectangulo.layoutXProperty());
        Bindings.bindBidirectional(this.label.layoutYProperty(), this.rectangulo.layoutYProperty());
        Bindings.bindBidirectional(this.labeli.layoutXProperty(), this.rectangulo.layoutXProperty());
        Bindings.bindBidirectional(this.labeli.layoutYProperty(), this.rectangulo.layoutYProperty());
        Bindings.bindBidirectional(this.labeld.layoutXProperty(), this.rectangulo.layoutXProperty());
        Bindings.bindBidirectional(this.labeld.layoutYProperty(), this.rectangulo.layoutYProperty());
        this.rectangulo.setFill(javafx.scene.paint.Color.rgb(204, 196, 177));
    }

    public void setPosicion(double x, double y) {
        this.label.translateXProperty().bind(this.rectangulo.translateXProperty().add(20));
        this.label.translateYProperty().bind(this.rectangulo.translateYProperty().subtract(20));
        this.labeli.translateXProperty().bind(this.rectangulo.translateXProperty().add(-20));
        this.labeli.translateYProperty().bind(this.rectangulo.translateYProperty().subtract(0));
        this.labeld.translateXProperty().bind(this.rectangulo.translateXProperty().add(40));
        this.labeld.translateYProperty().bind(this.rectangulo.translateYProperty().subtract(0));
        this.rectangulo.setLayoutX(x);
        this.rectangulo.setLayoutY(y);
    }

    public void setTexto(String texto, String texto1, String texto2) {

        this.label.setText(texto);
        this.labeli.setText(texto1);
        this.labeld.setText(texto2);
    }

    public Rectangle getRombo() {
        return this.rectangulo;
    }

    public Label getLabel1() {
        return this.label;
    }
    public Label getLabelI() {
        return this.labeli;
    }
    public Label getLabelD() {
        return this.labeld;
    }

}


