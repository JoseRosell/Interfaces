package com.example.interfaces;//package com.example.interfaces;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javax.swing.*;
import java.util.ArrayList;

public class Controlador {

    @FXML
    private Button btCuadrado;
    @FXML
    private Button btRombo;
    @FXML
    private AnchorPane panelAP;
    @FXML
    private Button btInfo;

    private Circulo circle;
    private CirculoNegro blackCircle;
    private Rombo rombo;
    private Cuadrado cuadrado;
    private Line linea;
    private ArrayList<Object> figuras = new ArrayList<>();
    private Object nodo1;
    private Object nodo2;

    @FXML
    public void initialize() {

        /////ON MOUSE PRESSED///////
        btRombo.setOnMousePressed((MouseEvent event) -> {
            rombo = new Rombo(panelAP);
            panelAP.getChildren().addAll(rombo.getRombo(), rombo.getLabel1(), rombo.getLabelI(), rombo.getLabelD());
            rombo.setPosicion(100, 100);
        });

        btCuadrado.setOnMousePressed((MouseEvent event) -> {
            cuadrado = new Cuadrado();
            panelAP.getChildren().addAll(cuadrado.getCuadrado(), cuadrado.getLabel());
            cuadrado.setPosicion(100, 100);
        });

        btInfo.setOnMouseClicked((MouseEvent event) -> {
            JOptionPane.showMessageDialog(null, "Para introducir un elemento y sus parametros" +
                    " arrastre o haga click en el icono del cuadrado, se le pedirán el numero de claves primarias y sus nombres y atributos y sus nombres.\n" +

                    "Para introducir una relación arrastre o haga click en el icono del rombo, si desea añadirle cualquier numero de atributos, debe hacer doble clic sobre la relacción deseada. \n" +

                    "Para unir elementos y relacciones, haga click en el botón de la linea y de la lista selecciona los elementos que quieres relacionar.\n");
        });


        /////ON MOUSE DRAGGED///////
        btRombo.setOnMouseDragged((MouseEvent event) -> {
            rombo.setPosicion(event.getSceneX(), event.getSceneY());
        });

        btCuadrado.setOnMouseDragged((MouseEvent event) -> {
            cuadrado.setPosicion(event.getSceneX(), event.getSceneY());
        });

        /////ON MOUSE RELEASED///////

        btRombo.setOnMouseReleased((MouseEvent event) -> {
            String texto = JOptionPane.showInputDialog("Introduce el nombre de la relacción");
            String relacciones[] = {"1","N/M"};


            String figurakey[] = new String[figuras.size()];

            for (int i = 0; i < figuras.size(); i++) {
                Cuadrado cuadrado = (Cuadrado) figuras.get(i);
                figurakey[i] = cuadrado.getLabel().getText();
            }

            String nodoNombre1 = (String) JOptionPane.showInputDialog(null, "Selecciona el primer elemento", "Selecciona el primer elemento", JOptionPane.QUESTION_MESSAGE, null, figurakey, figurakey[0]);
            String relaccion1 = (String) JOptionPane.showInputDialog(null, "Selecciona su cardinalidad", "Selecciona su cardinalidad", JOptionPane.QUESTION_MESSAGE, null, relacciones, relacciones[0]);
            String nodoNombre2 = (String) JOptionPane.showInputDialog(null, "Selecciona el segundo elemento", "Selecciona el segundo elemento", JOptionPane.QUESTION_MESSAGE, null, figurakey, figurakey[1]);
            String relaccion2 = (String) JOptionPane.showInputDialog(null, "Selecciona su cardinalidad", "Selecciona su cardinalidad", JOptionPane.QUESTION_MESSAGE, null, relacciones, relacciones[0]);

            rombo.setTexto(texto, relaccion1, relaccion2);

            // Esto tiene pinta de fumada, pero "funciona"
            for (int i = 0; i < figuras.size(); i++) {

                Cuadrado cuadrado = (Cuadrado) figuras.get(i);
                if (cuadrado.getLabel().getText().equals(nodoNombre1)) {
                    nodo1 = figuras.get(i);
                } else if (cuadrado.getLabel().getText().equals(nodoNombre2)) {
                    nodo2 = figuras.get(i);
                }
            }

            Line linea1 = new Line();
            panelAP.getChildren().add(linea1);
            linea1.setViewOrder(1);

            linea1.startXProperty().bind(((Cuadrado) nodo1).getCuadrado().layoutXProperty().add(((Cuadrado) nodo1).getCuadrado().widthProperty().divide(2)));
            linea1.startYProperty().bind(((Cuadrado) nodo1).getCuadrado().layoutYProperty().add(((Cuadrado) nodo1).getCuadrado().heightProperty().divide(2)));
            linea1.endXProperty().bind(rombo.getRombo().layoutXProperty().subtract(6));
            linea1.endYProperty().bind(rombo.getRombo().layoutYProperty().add(rombo.getRombo().heightProperty().divide(2)));

            Line linea2 = new Line();
            panelAP.getChildren().add(linea2);
            linea2.setViewOrder(1);
            linea2.startXProperty().bind(rombo.getRombo().layoutXProperty().add(31));
            linea2.startYProperty().bind(rombo.getRombo().layoutYProperty().add(rombo.getRombo().heightProperty().divide(2)));
            linea2.endXProperty().bind(((Cuadrado) nodo2).getCuadrado().layoutXProperty().add(((Cuadrado) nodo2).getCuadrado().widthProperty().divide(2)));
            linea2.endYProperty().bind(((Cuadrado) nodo2).getCuadrado().layoutYProperty().add(((Cuadrado) nodo2).getCuadrado().heightProperty().divide(2)));
        });

        btCuadrado.setOnMouseReleased((MouseEvent event) -> {
            String texto = JOptionPane.showInputDialog("Introduce el el nombre del elemento");
            cuadrado.setTexto(texto);
            figuras.add(cuadrado);
            int pk = 0;
            try {
                pk = Integer.parseInt(JOptionPane.showInputDialog("Numero de claves primarias, al menos una", "1"));
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Debe haber al menos una clave primaria");
                pk = 1;
            }
            if (pk <= 0){
                JOptionPane.showMessageDialog(null, "Debe haber al menos una clave primaria");
                pk = 1;
            }
            if (pk >= 1) {
                for (int i = pk; i > 0; i--) {
                    blackCircle = new CirculoNegro(5);
                    panelAP.getChildren().addAll(blackCircle.getCirculo(), blackCircle.getLabel());
                    blackCircle.setPosicion(cuadrado.getCuadrado().getLayoutX(), cuadrado.getCuadrado().getLayoutY() + (30 * i));
                    String texto2 = JOptionPane.showInputDialog("Introduce el nombre de la clave primaria");
                    blackCircle.setTexto(texto2);
                    linea = new Line();
                    panelAP.getChildren().add(linea);
                    linea.setViewOrder(1);
                    linea.startXProperty().bind(cuadrado.getCuadrado().layoutXProperty().add(cuadrado.getCuadrado().widthProperty().divide(2)));
                    linea.startYProperty().bind(cuadrado.getCuadrado().layoutYProperty().add(cuadrado.getCuadrado().heightProperty().divide(2)));
                    linea.endXProperty().bind(blackCircle.getCirculo().layoutXProperty().add(blackCircle.getCirculo().centerXProperty()));
                    linea.endYProperty().bind(blackCircle.getCirculo().layoutYProperty().add(blackCircle.getCirculo().centerYProperty()));
                }
            }
            int par;
            try {
                par = Integer.parseInt(JOptionPane.showInputDialog("Numero de atributos"));
            }catch (NumberFormatException e){
                par = 0;
            }
            if (par < 0){
                par = 0;
            }
            if (par >= 1) {
                for (int i = par; i > 0; i--) {
                    circle = new Circulo(5);
                    panelAP.getChildren().addAll(circle.getCirculo(), circle.getLabel());
                    circle.setPosicion((cuadrado.getCuadrado().getLayoutX()+50), cuadrado.getCuadrado().getLayoutY() + (30 * i));
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
