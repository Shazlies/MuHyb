/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class GenerateSecondPieGraph {

    public String getPieString(ArrayList<ResultsForEachFile> arr3) {
        int booleanOp = 0;
        int equalityOp = 0;
        int logicalOp = 0;
        int incrementOp = 0;
        int mathematicalOp = 0;
        int relationalOp = 0;
        int ifStatementOp = 0;
        int emptyFunctionOp = 0;
        int loopOp = 0;
        int statementDeletionOp = 0;
        int clickEventOp = 0;
        int LCROp = 0;
        int interpolationOp=0;
        int ngFor=0;
        int guiOp=0;
        int navOp=0;
        for (int i = 0; i < arr3.size(); i++) {
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&");
            System.out.println(arr3.get(i).getOperator().equals("Click event Operator"));
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&");
            if (arr3.get(i).getOperator().equals("Boolean Operator")) {
                booleanOp++;
            }
            if (arr3.get(i).getOperator().equals("Equality Operator")) {
                equalityOp++;
            }
            if (arr3.get(i).getOperator().equals("Logical Operator")) {
                logicalOp++;
            }
            if (arr3.get(i).getOperator().equals("Increment Operator")) {
                incrementOp++;
            }
            if (arr3.get(i).getOperator().equals("Math Operator")) {
                mathematicalOp++;
            }
            if (arr3.get(i).getOperator().equals("Relational Operator")) {
                relationalOp++;
            }

            if (arr3.get(i).getOperator().equals("If Statement Operator")) {
                ifStatementOp++;
            }

            if (arr3.get(i).getOperator().equals("Empty Function Block Operator")) {
                emptyFunctionOp++;
            }
            if (arr3.get(i).getOperator().equals("Loop Operator")) {
                loopOp++;
            }
            if (arr3.get(i).getOperator().equals("Statement Deletion Operator")) {
                statementDeletionOp++;
            }
            if (arr3.get(i).getOperator().equals("Click event Operator")) {
                clickEventOp++;
            }
            if (arr3.get(i).getOperator().equals("Life Cycle Function Removal Operator")) {
                LCROp++;
            }

            if (arr3.get(i).getOperator().equals("Data Binding Operator")) {
                interpolationOp++;
            }
            if (arr3.get(i).getOperator().equals("Structural Directives Replace Operator")) {
                ngFor++;
            }
            if (arr3.get(i).getOperator().equals("GUI Components Removal Operator")) {
                guiOp++;
            }
            if (arr3.get(i).getOperator().equals("NavController Operator")) {
                navOp++;
            }
        }

        String firstPart = "<script type=\"text/javascript\">\n"
                + "      google.charts.load('current', {'packages':['corechart']});\n"
                + "      google.charts.setOnLoadCallback(drawChart);\n"
                + "\n"
                + "      function drawChart() {\n"
                + "\n"
                + "        var data = google.visualization.arrayToDataTable([\n"
                + "          ['Operators', 'Number of Mutants'],\n";
        String secondPart = "";
        if (booleanOp != 0) {
            secondPart += "          ['Boolean Operator',     " + booleanOp + "],";
        }
        if (equalityOp != 0) {
            secondPart += "          ['Equality Operator',     " + equalityOp + "],";
        }
        if (logicalOp != 0) {
            secondPart += "          ['Logical Operator',     " + logicalOp + "],";
        }
        if (incrementOp != 0) {
            secondPart += "          ['Increment Operator',     " + incrementOp + "],";
        }
        if (mathematicalOp != 0) {
            secondPart += "          ['Math Operator',     " + mathematicalOp + "],";
        }
        if (relationalOp != 0) {
            secondPart += "          ['Relational Operator',     " + relationalOp + "],";
        }
        if (ifStatementOp != 0) {
            secondPart += "          ['If Statement Operator',     " + ifStatementOp + "],";
        }
        if (emptyFunctionOp != 0) {
            secondPart += "          ['Empty Function Block Operator',     " + emptyFunctionOp + "],";
        }
        if (loopOp != 0) {
            secondPart += "          ['Loop Operator',     " + loopOp + "],";
        }
        if (statementDeletionOp != 0) {
            secondPart += "          ['Statement Deletion Operator',     " + statementDeletionOp + "],";
        }
        if (clickEventOp != 0) {
            secondPart += "          ['Click event Operator',     " + clickEventOp + "],";
        }
        if (LCROp != 0) {
            secondPart += "          ['Life Cycle Function Removal Operator',     " + LCROp + "],";
        }
        if (interpolationOp != 0) {
            secondPart += "          ['Data Binding Operator',     " + interpolationOp + "],";
        }
        if (ngFor != 0) {
            secondPart += "          ['Structural Directives Replace Operator',     " + ngFor + "],";
        }
        if (guiOp != 0) {
            secondPart += "          ['GUI Components Removal Operator',     " + guiOp + "],";
        }
        if (navOp != 0) {
            secondPart += "          ['NavController Operator',     " + navOp + "],";
        }
        secondPart = secondPart.substring(0, secondPart.length() - 1);
        String thirdPart = "        ]);\n"
                + "\n"
                + "        var options = {\n"
                + "          title: 'Operators Statistics'\n"
                + "        };\n"
                + "\n"
                + "        var chart = new google.visualization.PieChart(document.getElementById('piechart2'));\n"
                + "\n"
                + "        chart.draw(data, options);\n"
                + "      }\n"
                + "    </script>\n\n";

        return firstPart + secondPart + thirdPart;
    }
}
