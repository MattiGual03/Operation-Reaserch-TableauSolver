package Tableau;

import java.util.Arrays;


public class VariablesColumn {

    String[] variable;


    /**
     * Constructs a new VariablesColumn filling it with variables starting from "-profit"
     * in the zero position and going on since "x1" to "xn".
     *
     * @param n number of variables you want to insert into a column
     */
    public VariablesColumn(int n) {
        fillVariable(n);
    }


    /**
     * Returns the VariablesColumn.
     *
     * @return the VariablesColumn
     */
    public String[] getVariable() {
        return variable;
    }


    private void fillVariable(int n) {
        if ( n > 0 ) {
            this.variable = new String[n];
            for ( int j = 0; j < variable.length; j++ ) {
                int a = j + 1;
                this.variable[j] = "x" + a;
            }
        }
        else {
            throw new ArrayStoreException("Number of variable <= 0");
        }
    }

    /**
     * Returns a variable from index
     *
     * @param index index
     * @return a variable from index
     */
    public String getVariableFromIndex(int index) {
        return variable[index];
    }


    /**
     * Compares two VariablesColumn returning true if are equal else false.
     *
     * @param o VariablesColumn you want to compare
     * @return true if two VariablesColumn contain the same variables
     */
    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        VariablesColumn that = ( VariablesColumn ) o;
        return Arrays.equals(variable, that.variable);
    }


    @Override
    public int hashCode() {
        return Arrays.hashCode(variable);
    }


    /**
     * @return return all parameters as a String
     */
    @Override
    public String toString() {
        return "VariableColumn{" + "variable=" + Arrays.toString(variable) + '}';
    }
}
