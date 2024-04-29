package com.example.interfaces;//package com.example.interfaces;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
            circle = new Circulo(20,100,100);
            panelAP.getChildren().addAll(circle.getCirculo(), circle.getLabel());
            circle.setPosicion(100,100);

        });

        btCirculoNegro.setOnMousePressed((MouseEvent event) -> {
            blackCircle = new CirculoNegro(20,100,100);
            panelAP.getChildren().addAll(blackCircle.getCirculo(), blackCircle.getLabel());
            blackCircle.setPosicion(100,100);
        });

        btRombo.setOnMousePressed((MouseEvent event) -> {
            rombo = new Rombo(100,100);
            panelAP.getChildren().addAll(rombo.getRombo(), rombo.getLabel());
            rombo.setPosicion(100,100);
        });

        btCuadrado.setOnMousePressed((MouseEvent event) -> {
            cuadrado = new Cuadrado(100,100);
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

    }
}