package uk.ac.sussex.ianw.fp.futoshiki2;


/**
 * "Model Answer" to stage 3 of Futoshiki Exercise
 * 
 * @author Rudi Lutz 
 * @version 1.0
 */
public  class FutoshikiSquare {
    private int row, column;
    private int value = 0;
    private boolean editable = true;
    
    public FutoshikiSquare(FutoshikiSquare toCopy) {
        this.row = toCopy.row;
        this.column = toCopy.column;
        this.value = toCopy.value;
        this.editable = toCopy.editable;
    }
    
    public FutoshikiSquare(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public boolean isEmpty() {
        return this.value == 0;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public String getSymbol() {
        if(isEmpty()) {
            return " ";
        } else {
            return "" + getValue();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.row;
        hash = 53 * hash + this.column;
        hash = 53 * hash + this.value;
        return hash;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FutoshikiSquare other = (FutoshikiSquare) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        if (this.value != other.value) {
            return false;
        }
        return true;
    }
    
 
}
