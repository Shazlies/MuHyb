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

        for (int i = 0; i < arr3.size(); i++) {
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
        
        secondPart = secondPart.substring(0, secondPart.length()-1);
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

        return firstPart+secondPart+thirdPart;
    }
}
