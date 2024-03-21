package Tableau;

import java.util.Arrays;


public class Test {

    public static void main(String[] args) {
        double[][] m = {{1.0, 3.0, 0.0, 0.0, 0.0},
                {1.0, 2.0, 1.0, 0.0, 4.0},
                {1.0, -1.0, 0.0, 1.0, 8.0}};

        VariablesColumn v = new VariablesColumn(4);
        Matrix prova = new Matrix(m);
        Tableau t = new Tableau(prova, v, "max");

        for ( int i = 0; i < prova.getMatrix().length; i++ ) {
            System.out.println(Arrays.toString(prova.getMatrix()[i]));
        }
        StringBuilder sb = new StringBuilder();
        int position;

        for ( int j = 0; j < t.getTableau().getMatrix()[0].length - 1; j++ ) {
            sb.append(v.getVariableFromIndex(j));
            if ( t.getTableau().allZerosAndAOneInAColumn(j) ) {
                position = t.getTableau().returnPositionofOnlyOne(t.getTableau().calculateColumnOfaMatrix(j));
                sb.append(" = ").append(t.getTableau().getMatrix()[position][t.getTableau().getMatrix()[0].length - 1]);
                if ( j == t.getTableau().getMatrix()[0].length - 2 ) {
                    sb.append(" ");
                }
                else {
                    sb.append(",  ");
                }
            }
            else {
                sb.append(" = ").append(0);
                if ( j == t.getTableau().getMatrix()[0].length - 2 ) {
                    sb.append(" ");
                }
                else {
                    sb.append(",  ");
                }

            }
        }
        System.out.println("\nP0 : ( " + sb.toString() + ")");
        System.out.println("Profit : " + (-t.getTableau().getMatrix()[0][t.getTableau().getMatrix()[0].length - 1]));
        System.out.println("\n\n\n");

        t.calculate();


    }
}
