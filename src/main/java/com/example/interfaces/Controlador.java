package com.example.interfaces;//package com.example.interfaces;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import javax.swing.*;


public class Controlador {

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;


    @FXML
    private Button btCirculo ;
    @FXML
    private Button btCirculoNegro;
    @FXML
    private Button btCuadrado;
    @FXML
    private Button btRombo;
    @FXML
    private Button btRaya;
    @FXML
    private AnchorPane panelAP;

    private Circulo circle ;
    private CirculoNegro blackCircle;
    private Rombo rombo;
    private Cuadrado cuadrado;
    private Line linea;

    @FXML
    public void initialize() {

        /////ON MOUSE PRESSED///////
//        btCirculo.setOnMousePressed((MouseEvent event) -> {
//            circle = new Circulo(20,100,100);
//            panelAP.getChildren().addAll(circle.getCirculo(), circle.getLabel());
//            circle.setPosicion(100,100);
//
//        });

//        btCirculoNegro.setOnMousePressed((MouseEvent event) -> {
//            blackCircle = new CirculoNegro(20,100,100);
//            panelAP.getChildren().addAll(blackCircle.getCirculo(), blackCircle.getLabel());
//            blackCircle.setPosicion(100,100);
//        });

        btRombo.setOnMousePressed((MouseEvent event) -> {
            rombo = new Rombo();
            panelAP.getChildren().addAll(rombo.getRombo(), rombo.getLabel());
            rombo.setPosicion(100,100);
        });

        btCuadrado.setOnMousePressed((MouseEvent event) -> {
            cuadrado = new Cuadrado();
            panelAP.getChildren().addAll(cuadrado.getCuadrado(), cuadrado.getLabel());
            cuadrado.setPosicion(100,100);
        });

        /////ON MOUSE DRAGGED///////
        btCirculo.setOnMouseDragged((MouseEvent event) -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                circle.setPosicion(event.getSceneX(), event.getSceneY());
            }
        });

        btCirculoNegro.setOnMouseDragged((MouseEvent event) -> {
            blackCircle.setPosicion(event.getSceneX(), event.getSceneY());
        });

        btRombo.setOnMouseDragged((MouseEvent event) -> {
            rombo.setPosicion(event.getSceneX(), event.getSceneY());
        });

        btCuadrado.setOnMouseDragged((MouseEvent event) -> {
            cuadrado.setPosicion(event.getSceneX(), event.getSceneY());
        });

        /////ON MOUSE RELEASED///////
        btCirculo.setOnMouseReleased((MouseEvent event) -> {
            // Preguntar por el texto
            String texto = JOptionPane.showInputDialog("Introduce el texto que desea introducir");
            // Poner el texto en el circulo
            circle.setTexto(texto);
        });

        btCirculoNegro.setOnMouseReleased((MouseEvent event) -> {
            String texto = JOptionPane.showInputDialog("Introduce el nombr de la Clave Primaria");
            blackCircle.setTexto(texto);
        });

        btRombo.setOnMouseReleased((MouseEvent event) -> {
            String texto = JOptionPane.showInputDialog("Introduce el nombre de la relacción");
            rombo.setTexto(texto);
        });

        btCuadrado.setOnMouseReleased((MouseEvent event) -> {
            String texto = JOptionPane.showInputDialog("Introduce el el nombre del elemento");
            cuadrado.setTexto(texto);

            int pk = Integer.parseInt(JOptionPane.showInputDialog("Numero de claves primarias"));
            if ( pk >= 1) {
                for (int i = pk; i > 0; i--) {
                    blackCircle = new CirculoNegro(20);
                    panelAP.getChildren().addAll(blackCircle.getCirculo(), blackCircle.getLabel());
                    blackCircle.setPosicion(Math.random()*500, Math.random()*500);
                    String texto2 = JOptionPane.showInputDialog("Introduce el nombre de la clave primaria");
                    blackCircle.setTexto(texto2);
                    linea = new Line();
                    panelAP.getChildren().add(linea);
                    linea.startXProperty().bind(cuadrado.getCuadrado().layoutXProperty().add(cuadrado.getCuadrado().widthProperty().divide(2)));
                    linea.startYProperty().bind(cuadrado.getCuadrado().layoutYProperty().add(cuadrado.getCuadrado().heightProperty().divide(2)));
                    linea.endXProperty().bind(blackCircle.getCirculo().layoutXProperty().add(blackCircle.getCirculo().centerXProperty()));
                    linea.endYProperty().bind(blackCircle.getCirculo().layoutYProperty().add(blackCircle.getCirculo().centerYProperty()));
                }
            }
            int par = Integer.parseInt(JOptionPane.showInputDialog("Numero de atributos"));
            if (par >= 1) {
                for (int i = par; i > 0; i--) {
                    circle = new Circulo(20);
                    panelAP.getChildren().addAll(circle.getCirculo(), circle.getLabel());
                    circle.setPosicion(Math.random()*500, Math.random()*500);
                    String texto2 = JOptionPane.showInputDialog("Introduce el nombre del atributo");
                    circle.setTexto(texto2);
                    linea = new Line();
                    panelAP.getChildren().add(linea);
                    linea.startXProperty().bind(cuadrado.getCuadrado().layoutXProperty().add(cuadrado.getCuadrado().widthProperty().divide(2)));
                    linea.startYProperty().bind(cuadrado.getCuadrado().layoutYProperty().add(cuadrado.getCuadrado().heightProperty().divide(2)));
                    linea.endXProperty().bind(circle.getCirculo().layoutXProperty().add(circle.getCirculo().centerXProperty()));
                    linea.endYProperty().bind(circle.getCirculo().layoutYProperty().add(circle.getCirculo().centerYProperty()));
                }
            }
        });

    }

}