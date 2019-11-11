/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.sussex.ianw.fp.futoshiki2;

import java.util.Optional;
import java.util.Random;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author 184737
 */
public class FutoshikiGUI extends Application {

    int gridsize;
    BorderPane border;
    private Futoshiki futo;
    private int size;
    FutoshikiGUISquare square;

    public GridPane newGame(Futoshiki futo) {
        GridPane grid = new GridPane();

        grid.setHgap(25);
        grid.setVgap(25);
        grid.setAlignment(Pos.CENTER);
        System.out.println(futo.displayString());
        for (int i = 0; i < futo.gridSize; i++) {

            for (int j = 0; j < futo.gridSize; j++) {

                square = new FutoshikiGUISquare(futo.getSquare(i, j), i, j, futo.gridSize, futo.getSquare(i, j).isEditable(), futo);

                square.setPrefSize(50, 50);
                grid.add(square, j * 2, i * 2);
            }

        }

        for (int i = 0; i < futo.gridSize; i++) {
            for (int j = 0; j < futo.gridSize - 1; j++) {
                Label rowConstraint = new Label(futo.getRowConstraint(i, j).getSymbol());
                grid.add(rowConstraint, (j * 2) + 1, i * 2);
            }
        }

        for (int i = 0; i < futo.gridSize - 1; i++) {
            for (int j = 0; j < futo.gridSize; j++) {
                Label colConstraint = new Label(futo.getColConstraint(j, i).getSymbol());
                GridPane.setHalignment(colConstraint, HPos.CENTER);
                grid.add(colConstraint, (j * 2), (i * 2) + 1);
            }
        }
        return grid;
    }

    @Override
    public void start(Stage primaryStage) {
        Random rand = new Random();
        border = new BorderPane();
        futo = new Futoshiki();
        this.gridsize = ((futo.gridSize * 2) - 1);
        futo.fillPuzzle(3, 3, 3);
        border.setCenter(newGame(futo));

        MenuBar menu = new MenuBar();
        VBox vBox = new VBox(menu);
        Menu menu1 = new Menu("Options");
        MenuItem menuItem1 = new MenuItem("New Game");
        menuItem1.setOnAction(e -> {

            ObservableList<String> choices = FXCollections.observableArrayList("Easy", "Hard");
            final ComboBox comboBox = new ComboBox(choices);
            Button create = new Button("Create");

            TextField input = new TextField();
            input.setMaxWidth(40);
            HBox options = new HBox(20);
            options.getChildren().addAll(comboBox, input, create);
            BorderPane container = new BorderPane();
            container.setBottom(options);
            Scene alert = new Scene(container);
            Stage newStage = new Stage();
            newStage.setScene(alert);
            newStage.sizeToScene();
            newStage.show();
            comboBox.getSelectionModel().selectFirst();
            create.setOnAction((event) -> {

                size = Integer.parseInt(input.getText());

                
                futo = new Futoshiki(size);
                if (comboBox.getValue().equals("Easy")) {
                    futo.fillPuzzle(rand.nextInt(futo.gridSize) / 3, rand.nextInt(futo.gridSize) / 3, rand.nextInt(futo.gridSize) / 3);
                } else {
                    futo.fillPuzzle(rand.nextInt(futo.gridSize), rand.nextInt(futo.gridSize), rand.nextInt(futo.gridSize));
                }
                border.setCenter(newGame(futo));
                System.out.println(futo.displayString());

            });


        }
        );

        MenuItem menuItem2 = new MenuItem("Clear");

        menuItem2.setOnAction(
                (event) -> {
                    for (int i = 0; i < futo.gridSize; i++) {
                        for (int j = 0; j < futo.gridSize; j++) {
                            if (futo.getSquare(i, j).isEditable()) {
                                futo.setSquare(i, j, 0);
                            }

                        }

                    }
                    border.setCenter(newGame(futo));
                }
        );
        menu1.getItems()
                .addAll(menuItem1, menuItem2);
        menu.getMenus()
                .add(menu1);
        border.setTop(vBox);

        Scene scene = new Scene(border, 600, 600);

        primaryStage.setTitle(
                "Futoshiki");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
