package Tableau;

import java.util.Arrays;


public class Matrix {

    private final double[][] matrix;

    /**
     * Returns a Matrix
     *
     * @return a Matrix
     */
    public double[][] getMatrix() {
        return matrix;
    }

    /**
     * Constructs a new Matrix from a matrix of double
     * which every row represents a single constraints
     *
     * @param matrix matrix of double which every row represents a single constraints
     */
    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }


    /**
     * Returns a new Matrix having an altered row which represents the sum of two rows from indexes
     *
     * @param indexPivotRow index of the first row you want to sum
     * @param indexOtherRow index of the second row you want to sum and alter values
     * @return a new Matrix having an altered row which represents the sum of two rows from indexes
     */
    public Matrix sumRowsfromIndexes(double[] pivotRow, int indexOtherRow) {
        double[] line = rowSum(pivotRow, this.matrix[indexOtherRow]);
        return copyMatrixWithAlteredLine(indexOtherRow, line);
    }

    /**
     * This method multiplies a Matrix row By a factor
     *
     * @param rowIndex Index of row you want to multiply
     * @param factor   number used to multiply the row
     * @return a new row with product between factor and the row
     */
    public double[] multiplyRowFromIndexByaFactor(int rowIndex, double factor) {
        return multiplyVectorForaFactor(this.matrix[rowIndex], factor);
    }

    /**
     * This method divides a Matrix row by a factor
     *
     * @param rowIndex Index of row you want to divide
     * @param factor   number used to divide the row
     * @return a new Matrix with an altered row
     */
    public Matrix divideRowFromIndexByaFactor(int rowIndex, double factor) {
        double[] line = divideVectorForaFactor(this.matrix[rowIndex], factor);
        return copyMatrixWithAlteredLine(rowIndex, line);
    }

    /**
     * it Controls if in a specified column into a matrix there is only a '1' and
     * all others '0'
     *
     * @param columnIndex
     * @return true if specified column contains only a '1' and all others '0',
     * false if specified column doesn't contain only a '1' and all others '0'
     */
    public boolean allZerosAndAOneInAColumn(int columnIndex) {
        int cont = 0;
        for ( int i = 0; i < this.matrix.length; i++ ) {
            if ( this.matrix[i][columnIndex] == 0 ) {
                cont++;
            }
        }
        if ( cont == this.matrix.length - 1 ) {
            return true;
        }
        return false;
    }

    /**
     * This method chooses a pivot element from a column returning its index
     *
     * @param column     column to choose from
     * @param lastColumn last column of the Matrix
     * @return index of pivot element
     */
    public int chosePivot(double[] column, double[] lastColumn) {

        boolean ris = pivotExists(column);
        if ( !ris ) {
            return -1;
        }
        double min = Double.MAX_VALUE;
        int index = 1;
        for ( int i = 1; i < column.length; i++ ) {
            if ( column[i] > 0 && lastColumn[i] / column[i] < min ) {
                min = lastColumn[i] / column[i];
                index = i;
            }
        }
        return index;
    }

    /**
     * Returns a matrix column from an index
     *
     * @param columnIndex index of column you want to calculate
     * @return a matrix column from an index
     */
    public double[] calculateColumnOfaMatrix(int columnIndex) {
        double[] ris = new double[this.matrix.length];
        for ( int i = 0; i < ris.length; i++ ) {
            ris[i] = this.matrix[i][columnIndex];
        }
        return ris;
    }

    /**
     * This method searches the row index of the only 1 int the column
     *
     * @param column a Matrix column
     * @return the position of the only 1 into the column
     */
    public int returnPositionofOnlyOne(double[] column) {
        for ( int i = 0; i < column.length; i++ ) {
            if ( column[i] == 1 ) {
                return i;
            }
        }
        return -1;
    }


    private Matrix copyMatrixWithAlteredLine(int rowIndex, double[] line) {
        double[][] m = new double[this.matrix.length][this.matrix[0].length];

        for ( int i = 0; i < m.length; i++ ) {
            if ( i == rowIndex ) {
                m[i] = Arrays.copyOf(line, line.length);
            }
            else {
                m[i] = Arrays.copyOf(this.matrix[i], this.matrix[i].length);
            }
        }

        return new Matrix(m);
    }

    private boolean pivotExists(double[] column) {
        boolean possible = false;

        for ( int i = 1; i < this.matrix.length - 1; i++ ) {
            if ( column[i] > 0 ) {
                possible = true;
            }
        }

        return possible;
    }

    private double[] multiplyVectorForaFactor(double[] v, double factor) {
        double[] ris = new double[v.length];
        for ( int i = 0; i < ris.length; i++ ) {
            ris[i] = v[i] * factor;
        }
        return ris;
    }


    private double[] divideVectorForaFactor(double[] v, double factor) {
        double[] ris = new double[v.length];
        for ( int i = 0; i < ris.length; i++ ) {
            ris[i] = v[i] / factor;
        }
        return ris;
    }


    private double[] rowSum(double[] first, double[] second) {
        double[] ris = new double[first.length];
        for ( int i = 0; i < ris.length; i++ ) {
            ris[i] = first[i] + second[i];
        }
        return ris;
    }


    @Override
    public String toString() {
        return "Matrix{" + "matrix=" + Arrays.toString(matrix[0]) + '}';
    }


    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Matrix matrix1 = ( Matrix ) o;
        return Arrays.equals(matrix, matrix1.matrix);
    }


    @Override
    public int hashCode() {
        return Arrays.hashCode(matrix);
    }


}
