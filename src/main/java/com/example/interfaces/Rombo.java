package com.example.interfaces;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import javax.swing.*;

public class Rombo {
    private Rectangle rectangulo;
    private Label label;
    private Label labeli;
    private Label labeld;
    private Circulo circle;



    public Rombo(AnchorPane panelAP) {
        this.rectangulo = new Rectangle(25,25);
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
        this.rectangulo.setFill(Color.WHITE);
        this.rectangulo.setStroke(Color.BLACK);
        this.rectangulo.setStrokeWidth(1);

        this.rectangulo.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                int cantidad = 1;
                try {
                    cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de atributos que desea introducir"));
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Introduzca un número");
                }
                for (int i = cantidad; i > 0; i--) {
                    String texto = JOptionPane.showInputDialog("Introduzca el nombre del atributo " + i);
                    circle = new Circulo(5);
                    panelAP.getChildren().addAll(circle.getCirculo(), circle.getLabel());
                    circle.setPosicion(this.rectangulo.getLayoutX(), (this.rectangulo.getLayoutY() + (40 * i)));
                    circle.setTexto(texto);
                    Line linea = new Line();
                    panelAP.getChildren().add(linea);
                    linea.setViewOrder(1);
                    linea.startXProperty().bind(this.rectangulo.layoutXProperty().add(this.rectangulo.widthProperty().divide(2)));
                    linea.startYProperty().bind(this.rectangulo.layoutYProperty().add(this.rectangulo.heightProperty().divide(2)));
                    linea.endXProperty().bind(circle.getCirculo().layoutXProperty().add(circle.getCirculo().centerXProperty()));
                    linea.endYProperty().bind(circle.getCirculo().layoutYProperty().add(circle.getCirculo().centerYProperty()));
                }
            }
        });
    }

    public void setPosicion(double x, double y) {
        this.label.translateXProperty().bind(this.rectangulo.translateXProperty());
        this.label.translateYProperty().bind(this.rectangulo.translateYProperty().subtract(22));
        this.labeli.translateXProperty().bind(this.rectangulo.translateXProperty().subtract(17));
        this.labeli.translateYProperty().bind(this.rectangulo.translateYProperty().subtract(0));
        this.labeld.translateXProperty().bind(this.rectangulo.translateXProperty().add(35));
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


