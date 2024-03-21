package Tableau;

import java.util.Arrays;
import java.util.Objects;

public class Tableau {
    private Matrix tableau;
    private final VariablesColumn variables;
    private final String typeofObjectFunction;
    private int n;

    /**
     * This method constructs a new Tableau from a Matrix, VariablesColumn, and
     * typeofObjectiveFunction
     *
     * @param tableau                 a tableau
     * @param variables               a VariablesColumn
     * @param typeofObjectiveFunction the type of object function
     */
    public Tableau(Matrix tableau, VariablesColumn variables, String typeofObjectiveFunction) {
        this.tableau = tableau;
        this.variables = variables;
        this.typeofObjectFunction = typeofObjectiveFunction;
        this.n = 1;
    }

    /**
     * Returns the Type of object function
     *
     * @return the Type of object function
     */
    public String getTypeofObjectFunction() {
        return typeofObjectFunction;
    }

    /**
     * Returns the tableau
     *
     * @return the tableau
     */
    public Matrix getTableau() {
        return tableau;
    }

    /**
     * Returns the variables
     *
     * @return the variables
     */
    public VariablesColumn getVariables() {
        return variables;
    }


    /**
     * This method solves the tableau entering a single variable for each iteration
     */
    public void calculate() {
        if ( this.typeofObjectFunction.equals("max") ) {
            int i = 0;
            while ( i < this.tableau.getMatrix()[0].length ) {
                if ( this.tableau.getMatrix()[0][i] > 0 ) {
                    int index = max(this.tableau.getMatrix()[0]);
                    visualizeSolution(index);
                }
                else {
                    i++;
                }
            }
        }
        else if ( this.typeofObjectFunction.equals("min") ) {
            int i = 0;
            while ( i < this.tableau.getMatrix()[0].length ) {
                if ( this.tableau.getMatrix()[0][i] < 0 ) {
                    int index = min(this.tableau.getMatrix()[0]);
                    visualizeSolution(index);
                    i = 0;
                }
                else {
                    i++;
                }
            }
        }
    }


    private void visualizeSolution(int index) {
        enterVariable(index);
        StringBuilder sb = new StringBuilder();
        int position;
        for ( int j = 0; j < tableau.getMatrix()[0].length - 1; j++ ) {
            sb.append(variables.getVariableFromIndex(j));
            if ( tableau.allZerosAndAOneInAColumn(j) ) {
                position = tableau.returnPositionofOnlyOne(tableau.calculateColumnOfaMatrix(j));
                sb.append(" = ").append(tableau.getMatrix()[position][tableau.getMatrix()[0].length - 1]);
                if ( j == tableau.getMatrix()[0].length - 2 ) {
                    sb.append(" ");
                }
                else {
                    sb.append(",  ");
                }
            }
            else {
                sb.append(" = ").append(0);
                if ( j == tableau.getMatrix()[0].length - 2 ) {
                    sb.append(" ");
                }
                else {
                    sb.append(",  ");
                }
            }
        }
        System.out.println("\nP" + n + " : ( " + sb.toString() + ")");
        System.out.println("Profit : " + (-tableau.getMatrix()[0][tableau.getMatrix()[0].length - 1]));
        this.n++;
        System.out.println("\n\n\n");
    }


    private void enterVariable(int columnIndex) {
        double[] column = this.tableau.calculateColumnOfaMatrix(columnIndex);
        int pivotIndex = this.tableau.chosePivot(column, this.tableau.calculateColumnOfaMatrix(this.tableau.getMatrix()[0].length - 1));
        if ( pivotIndex == -1 ) {
            System.out.println("The problem is unbounded");
            System.exit(0);
        }
        tableau = tableau.divideRowFromIndexByaFactor(pivotIndex, column[pivotIndex]);
        for ( int i = 0; i < column.length; i++ ) {
            if ( i != pivotIndex ) {
                tableau = tableau.sumRowsfromIndexes(tableau.multiplyRowFromIndexByaFactor(pivotIndex, -column[i]), i);
            }
            System.out.println(Arrays.toString(tableau.getMatrix()[i]));
        }
    }

    private int min(double[] v) {
        double min = v[0];
        int index = 0;
        for ( int i = 0; i < v.length - 1; i++ ) {
            if ( v[i] < min ) {
                min = v[i];
                index = i;
            }
        }
        return index;
    }

    private int max(double[] v) {
        double max = v[0];
        int index = 0;
        for ( int i = 0; i < v.length - 1; i++ ) {
            if ( v[i] > max ) {
                max = v[i];
                index = i;
            }
        }
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Tableau tableau1 = ( Tableau ) o;
        return Objects.equals(tableau, tableau1.tableau) && Objects.equals(variables, tableau1.variables) && Objects.equals(typeofObjectFunction, tableau1.typeofObjectFunction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableau, variables, typeofObjectFunction);
    }

    @Override
    public String toString() {
        return "Tableau{" +
                "tableau=" + tableau +
                ", variables=" + variables +
                ", typeofObjectFunction='" + typeofObjectFunction + '\'' +
                '}';
    }
}
