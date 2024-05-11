package com.example.interfaces;//package com.example.interfaces;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;


public class Controlador {

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;


    @FXML
    private Button btRaya;
    @FXML
    private Button btCuadrado;
    @FXML
    private Button btRombo;
    @FXML
    private AnchorPane panelAP;


    private Circulo circle;
    private CirculoNegro blackCircle;
    private Rombo rombo;
    private Cuadrado cuadrado;
    private Line linea;
    private HashMap<Label, Node> figuras = new HashMap<Label, Node>();

    @FXML
    public void initialize() {

        /////ON MOUSE PRESSED///////
        btRombo.setOnMousePressed((MouseEvent event) -> {
            rombo = new Rombo();
            panelAP.getChildren().addAll(rombo.getRombo(), rombo.getLabel());
            rombo.setPosicion(100, 100);
        });

        btCuadrado.setOnMousePressed((MouseEvent event) -> {
            cuadrado = new Cuadrado();
            panelAP.getChildren().addAll(cuadrado.getCuadrado(), cuadrado.getLabel());
            cuadrado.setPosicion(100, 100);
        });


        btRaya.setOnMouseClicked((MouseEvent event) -> {
            String relaccion = JOptionPane.showInputDialog("Introduce el nombre de la relacción, separado por comas. i.e. 1,1  1,N  N,M");

            String figurakey[] =new String[figuras.size()];

            for (Label key : figuras.keySet()) {
                figurakey[figuras.size()-1] = key.getText();
            }


            Node nodo1 = (Node) JOptionPane.showInputDialog(null, "Selecciona el primer elemento", "Selecciona el primer elemento", JOptionPane.QUESTION_MESSAGE, null, figurakey, figurakey[0]);
            Node nodo2 = (Node) JOptionPane.showInputDialog(null, "Selecciona el segundo elemento", "Selecciona el segundo elemento", JOptionPane.QUESTION_MESSAGE, null, figurakey, figurakey[0]);

        });

//        panelAP.setOnMouseClicked((MouseEvent event1) -> {
//            // Si es el primer nodo clickeado
//            if (nodo1 == null) {
//                if (event1.getSource() instanceof Rombo || event1.getSource() instanceof Cuadrado) {
//                    nodo1 = (Node) event1.getSource();
//                }
//            } else if (event1.getSource() instanceof Rombo || event1.getSource() instanceof Cuadrado) {
//                Node nodo2 = (Node) event1.getSource();
//                // Verifica que los nodos sean diferentes
//                if (!nodo2.equals(nodo1)) {
//                    // Crea una nueva línea entre los dos nodos
//                    Line linea = new Line();
//                    linea.startXProperty().bind(nodo1.layoutXProperty());
//                    linea.startYProperty().bind(nodo1.layoutYProperty());
//                    linea.endXProperty().bind(nodo2.layoutXProperty());
//                    linea.endYProperty().bind(nodo2.layoutYProperty());
//                    panelAP.getChildren().add(linea);
//                    linea.setViewOrder(1);
//                    nodo1 = null;
//                    panelAP.setOnMouseClicked(null);
//                }
//            }
//        });



        /////ON MOUSE DRAGGED///////
        btRombo.setOnMouseDragged((MouseEvent event) -> {
            rombo.setPosicion(event.getSceneX(), event.getSceneY());
        });

        btCuadrado.setOnMouseDragged((MouseEvent event) -> {
            cuadrado.setPosicion(event.getSceneX() - cuadrado.getX(), event.getSceneY() - cuadrado.getY()); // todo
        });


        /////ON MOUSE RELEASED///////

        btRombo.setOnMouseReleased((MouseEvent event) -> {
            String texto = JOptionPane.showInputDialog("Introduce el nombre de la relacción");
            rombo.setTexto(texto);
            figuras.put(rombo.getLabel(), rombo.getRombo());
        });

        btCuadrado.setOnMouseReleased((MouseEvent event) -> {
            String texto = JOptionPane.showInputDialog("Introduce el el nombre del elemento");
            cuadrado.setTexto(texto);
            figuras.put(cuadrado.getLabel(), cuadrado.getCuadrado());

            int pk = Integer.parseInt(JOptionPane.showInputDialog("Numero de claves primarias"));
            if (pk >= 1) {
                for (int i = pk; i > 0; i--) {
                    blackCircle = new CirculoNegro(20);
                    panelAP.getChildren().addAll(blackCircle.getCirculo(), blackCircle.getLabel());
                    blackCircle.setPosicion(Math.random() * 500, Math.random() * 500);
                    String texto2 = JOptionPane.showInputDialog("Introduce el nombre de la clave primaria");
                    blackCircle.setTexto(texto2);
                    figuras.put(blackCircle.getLabel(),blackCircle.getCirculo());
                    linea = new Line();
                    panelAP.getChildren().add(linea);
                    linea.setViewOrder(1);
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
                    circle.setPosicion(Math.random() * 500, Math.random() * 500);
                    String texto2 = JOptionPane.showInputDialog("Introduce el nombre del atributo");
                    circle.setTexto(texto2);
                    linea = new Line();
                    panelAP.getChildren().add(linea);
                    linea.setViewOrder(1);
                    linea.startXProperty().bind(cuadrado.getCuadrado().layoutXProperty().add(cuadrado.getCuadrado().widthProperty().divide(2)));
                    linea.startYProperty().bind(cuadrado.getCuadrado().layoutYProperty().add(cuadrado.getCuadrado().heightProperty().divide(2)));
                    linea.endXProperty().bind(circle.getCirculo().layoutXProperty().add(circle.getCirculo().centerXProperty()));
                    linea.endYProperty().bind(circle.getCirculo().layoutYProperty().add(circle.getCirculo().centerYProperty()));
                }
            }
        });

    }

}
