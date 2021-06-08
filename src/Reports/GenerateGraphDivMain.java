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
public class GenerateGraphDivMain {



    public GenerateGraphDivMain(String path) {
        
    }

    public String generateScript(ArrayList<MutationResults> arr) {

        String firstString =  "<script type=\"text/javascript\">    google.charts.load(\"current\", {packages:['corechart']});\n"
                + "    google.charts.setOnLoadCallback(drawChart);\n"
                + "    function drawChart() {\n"
                + "      var data = google.visualization.arrayToDataTable([\n"
                + "        ['Genre', 'Killed', 'Survived', 'Discarded', { role: 'annotation' } ],\n";
        String middleString = "";
        for (int i = 0; i < arr.size(); i++) {
            if(i ==arr.size()-1){
                middleString += "['"+arr.get(i).getFileName()+"', "+arr.get(i).getKillMutant()+" ,"+arr.get(i).getSurvivedMutants()+" ,"+arr.get(i).getSyntaxErrors()+", '']\n";
            }
            else{
                middleString += "['"+arr.get(i).getFileName()+"', "+arr.get(i).getKillMutant()+" ,"+arr.get(i).getSurvivedMutants()+" ,"+arr.get(i).getSyntaxErrors()+", ''],\n";
            }
            
        }


        String secondString = "      ]);\n"
                + "\n"
                + "      var view = new google.visualization.DataView(data);\n"
                + "      view.setColumns([0, 1, 2, 3,\n"
                + "                       { calc: \"stringify\",\n"
                + "                         type: \"string\",\n"
                + "                         role: \"annotation\" },\n"
                + "                       4]);\n"
                + "\n"
                + "      var options = {\n"
                + "        width: 1100,\n"
                + "        height: 400,\n"
                + "        legend: { position: 'top', maxLines: 3 },\n"
                + "        bar: { groupWidth: '75%' },\n"
                + "        isStacked: true,\n"
                +"          colors: [\'blue\', \'red\', \'green\'],\n"
                + "      };\n"
                + "      var chart = new google.visualization.ColumnChart(document.getElementById(\"columnchart_values\"));\n"
                + "      chart.draw(view, options);\n"
                + "  } </script>\n";
                
        
        
        
        return firstString + middleString + secondString;
    }
}
