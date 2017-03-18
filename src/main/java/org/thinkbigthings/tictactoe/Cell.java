package org.thinkbigthings.tictactoe;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Cell {
    private int row = 0;
    private int col = 0;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Cell rhs = (Cell) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(row, rhs.row)
                .append(col, rhs.col)
                .isEquals();
    }

    @Override
    public int hashCode() {

        // TODO evaluate whether easier to use java.util.Objects for equals/hashcode
        // java.util.Objects.hash(row, col);

        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        return new HashCodeBuilder(17, 37).
                append(row).
                append(col).
                toHashCode();
    }
}
