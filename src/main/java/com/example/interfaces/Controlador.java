package com.example.interfaces;//package com.example.interfaces;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    private Linea linea;
    @FXML
    public void initialize() {

        /////ON MOUSE PRESSED///////
        btCirculo.setOnMousePressed((MouseEvent event) -> {
            // Crear un circulo cuando detecta el evento
            // Y ponerlo en la posición del mouse
            circle = new Circulo(20, event.getSceneX(), event.getSceneY());
            panelAP.getChildren().addAll(circle.getCirculo(), circle.getLabel());
        });

        btCirculoNegro.setOnMousePressed((MouseEvent event) -> {
            blackCircle = new CirculoNegro(20, event.getSceneX(), event.getSceneY());
            panelAP.getChildren().addAll(blackCircle.getCirculo(), blackCircle.getLabel());
        });

        btRombo.setOnMousePressed((MouseEvent event) -> {
            rombo = new Rombo(event.getSceneX(), event.getSceneY());
            panelAP.getChildren().addAll(rombo.getRombo(), rombo.getLabel());
        });

        btCuadrado.setOnMousePressed((MouseEvent event) -> {
            cuadrado = new Cuadrado(event.getSceneX(), event.getSceneY());
            panelAP.getChildren().addAll(cuadrado.getCuadrado(), cuadrado.getLabel());
        });

        btRaya.setOnMousePressed((MouseEvent event) -> {
            panelAP.setOnMousePressed((MouseEvent event1) -> {
                orgSceneX = event1.getSceneX();
                orgSceneY = event1.getSceneY();
            });
            panelAP.setOnMousePressed((MouseEvent event2) -> {
                orgTranslateX = event2.getSceneX();
                orgTranslateY = event2.getSceneY();
            });
            linea = new Linea(orgSceneX, orgSceneY, orgTranslateX, orgTranslateY);
            panelAP.getChildren().add(linea.getLinea());
        });


        /////ON MOUSE DRAGGED///////
        btCirculo.setOnMouseDragged((MouseEvent event) -> {
            circle.setPosicion(event.getSceneX(), event.getSceneY());
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
            String texto = JOptionPane.showInputDialog("Introduce el texto que desea introducir");
            blackCircle.setTexto(texto);
        });

        btRombo.setOnMouseReleased((MouseEvent event) -> {
            String texto = JOptionPane.showInputDialog("Introduce el texto que desea introducir");
            rombo.setTexto(texto);
        });

        btCuadrado.setOnMouseReleased((MouseEvent event) -> {
            String texto = JOptionPane.showInputDialog("Introduce el texto que desea introducir");
            cuadrado.setTexto(texto);
        });


        /////ON MOUSE DOUBLE CLICK///////





    }
}