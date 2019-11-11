/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.sussex.ianw.fp.futoshiki2;

import javafx.scene.control.Button;

/**
 *
 * @author 184737
 */
public class FutoshikiGUISquare extends Button {

    FutoshikiSquare square;
    int row;
    int col;
    int size;
    boolean editable;
    private Futoshiki futo;

    public FutoshikiGUISquare(FutoshikiSquare square, int row, int col, int size, boolean editable, Futoshiki futo) {
        super(square.getSymbol());
        this.square = square;
        this.row = square.getRow();
        this.col = square.getColumn();
        this.size = size;
        this.editable = editable;
        this.futo = futo;

        if (square.isEditable()) {
            setOnAction((event) -> {
                if (square.getValue() < size) {
                    square.setValue(square.getValue() + 1);
                    setText(String.valueOf(square.getValue()));
                    if (!futo.isLegal()) {
                        setStyle("-fx-background-color: #d83615; -fx-text-fill: #FFF;");
                    }
                    else{
                        setStyle("-fx-background-color: #29e56b;");
                    }
                } else {
                    square.setValue(0);
                    setText("");
                    setStyle(null);
                }
                });
            }
        else {
            setStyle("-fx-border-color: #010101; -fx-border-width: 1px;");
            setDisable(true);
        }
            
        

    }
}
