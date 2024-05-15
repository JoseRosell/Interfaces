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
    private Button btRaya;
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





        btRaya.setOnMouseClicked((MouseEvent event) -> {

            String figurakey[] = new String[figuras.size()];

            for(int i = 0; i < figuras.size(); i++){
                if(figuras.get(i) instanceof Cuadrado){
                    Cuadrado cuadrado = (Cuadrado) figuras.get(i);
                    figurakey[i] = cuadrado.getLabel().getText();
                }else if(figuras.get(i) instanceof Rombo){
                    Rombo rombo = (Rombo) figuras.get(i);
                    figurakey[i] = rombo.getLabel1().getText();
                }
            }

            String nodoNombre1 =  (String) JOptionPane.showInputDialog(null, "Selecciona el primer elemento", "Selecciona el primer elemento", JOptionPane.QUESTION_MESSAGE, null, figurakey, figurakey[0]);
            String nodoNombre2 =  (String) JOptionPane.showInputDialog(null, "Selecciona el segundo elemento", "Selecciona el segundo elemento", JOptionPane.QUESTION_MESSAGE, null, figurakey, figurakey[1]);

            // Esto tiene pinta de fumada, pero "funciona"
            for(int i = 0; i < figuras.size(); i++){
                if(figuras.get(i).getClass() == Cuadrado.class){
                    Cuadrado cuadrado = (Cuadrado) figuras.get(i);
                    if(cuadrado.getLabel().getText().equals(nodoNombre1)){
                        nodo1 = figuras.get(i);
                    }else if(cuadrado.getLabel().getText().equals(nodoNombre2)){
                        nodo2 = figuras.get(i);
                    }
                }else if(figuras.get(i).getClass() == Rombo.class){
                    Rombo rombo = (Rombo) figuras.get(i);
                    if(rombo.getLabel1().getText().equals(nodoNombre1)){
                        nodo1 = figuras.get(i);
                    }else if(rombo.getLabel1().getText().equals(nodoNombre2)){
                        nodo2 = figuras.get(i);
                    }
                }
            }

            Line linea = new Line();
            panelAP.getChildren().add(linea);
            linea.setViewOrder(1);
            if (nodo1.getClass() == Cuadrado.class) {
                Cuadrado cuadrado = (Cuadrado) nodo1;
                linea.startXProperty().bind(cuadrado.getCuadrado().layoutXProperty().add(cuadrado.getCuadrado().widthProperty().divide(2)));
                linea.startYProperty().bind(cuadrado.getCuadrado().layoutYProperty().add(cuadrado.getCuadrado().heightProperty().divide(2)));
            } else if (nodo1.getClass() == Rombo.class){
                Rombo rombo = (Rombo) nodo1;
                linea.startXProperty().bind(rombo.getRombo().layoutXProperty().add(rombo.getRombo().widthProperty().divide(2)));
                linea.startYProperty().bind(rombo.getRombo().layoutYProperty().add(rombo.getRombo().heightProperty().divide(2)));
            }
            if (nodo2.getClass() == Cuadrado.class){
                Cuadrado cuadrado = (Cuadrado) nodo2;
                linea.endXProperty().bind(cuadrado.getCuadrado().layoutXProperty().add(cuadrado.getCuadrado().widthProperty().divide(2)));
                linea.endYProperty().bind(cuadrado.getCuadrado().layoutYProperty().add(cuadrado.getCuadrado().heightProperty().divide(2)));
            } else if (nodo2.getClass() == Rombo.class){
                Rombo rombo = (Rombo) nodo2;
                linea.endXProperty().bind(rombo.getRombo().layoutXProperty().add(rombo.getRombo().widthProperty().divide(2)));
                linea.endYProperty().bind(rombo.getRombo().layoutYProperty().add(rombo.getRombo().heightProperty().divide(2)));
            }

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
            String relacciones[] = {"1,1","1,N","N,M","N,1"};

            String relaccion = (String) JOptionPane.showInputDialog(null, "Selecciona tipo de la relacción", "Selecciona el tipo de la relacción", JOptionPane.QUESTION_MESSAGE, null, relacciones, relacciones[0]);

            rombo.setTexto(texto, relaccion.split(",")[0], relaccion.split(",")[1]);
            figuras.add(rombo);

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
                    blackCircle = new CirculoNegro(20);
                    panelAP.getChildren().addAll(blackCircle.getCirculo(), blackCircle.getLabel());
                    blackCircle.setPosicion(Math.random() * 500, Math.random() * 500);
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
