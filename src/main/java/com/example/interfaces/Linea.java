package com.example.interfaces;


import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;


public class Linea {
    private LineTo inicio;
    private LineTo fin;
    private Path linea;

    public Linea() {
    }

    public Linea(double x, double y, double x2, double y2) {
            this.linea = new Path();
            this.inicio = new LineTo(x,y);
            this.fin = new LineTo(x2,y2);
            this.linea.getElements().add(this.inicio);
            this.linea.getElements().add(this.fin);
            this.linea.setStrokeWidth(2);
            this.linea.setStroke(javafx.scene.paint.Color.BLACK);
    }

    public void setInicio(double x, double y) {
        this.inicio.setX(x);
        this.inicio.setY(y);
    }

    public void setFin(double x, double y) {
        this.fin.setX(x);
        this.fin.setY(y);
    }
    public Path getLinea() {
        return this.linea;
    }

}
