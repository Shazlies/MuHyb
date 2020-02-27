/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class GenerateReportForEachFile {

    ArrayList<FileResults> arr = new ArrayList<FileResults>();
    FileResults fr;
    //////////////////////////////////////////
    String firstPart;
    String secondPart;
    String id;
    String thirdPart;

    public GenerateReportForEachFile(FileResults fr) {
        this.fr = fr;
        //this.fr = fr;

        firstPart = "<!DOCTYPE html>\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n"
                + "    <meta charset=\"utf-8\" />\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\" />\n"
                + "    <meta name=\"description\" content=\"\" />\n"
                + "    <meta name=\"author\" content=\"\" />\n"
                + "    <!--[if IE]>\n"
                + "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n"
                + "        <![endif]-->\n"
                + "    <title>MuHybApps Report</title>\n"
                + "    <!-- BOOTSTRAP CORE STYLE  -->\n"
                + "    <link href=\"assets/css/bootstrap.css\" rel=\"stylesheet\" />\n"
                + "    <!-- CUSTOM STYLE  -->\n"
                + "    <link href=\"assets/css/style.css\" rel=\"stylesheet\" />\n"
                + "<script>\n"
                + "\n"
                + "\n"
                + "function toRed(y){\n"
                + "		var x = document.getElementById(y);\n"
                + "	x.style.backgroundColor=\"red\";\n"
                + "\n"
                + "}\n"
                + "\n"
                + "function toGray(y){\n"
                + "	\n"
                + "		var x = document.getElementById(y);\n"
                + "	x.style.backgroundColor=\"#eee\";\n"
                + "\n"
                + "}\n"
                + "\n"
                + "function generateLineNumbers() {\n"
                + "\n"
                + "var pre = document.getElementsByTagName('pre'),\n"
                + "pl = pre.length;\n"
                + "for (var i=0; i<pl; i++){\n"
                + "pre[i].innerHTML = '<span class=\"line-number\"></span>'+pre[i].innerHTML + '<span class=\"cl\"></span>';\n"
                + "var num = pre[i].innerHTML.split(/\\n/).length;\n"
                + "for(var j=0; j<num; j++){\n"
                + "var line_num = pre[i].getElementsByTagName('span')[0];\n"
                + "line_num.innerHTML +='<span id=\"l'+j+'\">' + (j+1) + '</span>';\n"
                + "}\n"
                + "}\n"
                + "\n"
                + "}\n"
                + "\n"
                + "\n"
                + "\n"
                + "</script>\n"
                + "\n"
                + "\n"
                + "</head>\n"
                + "<body onload=\"generateLineNumbers()\">\n"
                + "    <div class=\"navbar navbar-inverse set-radius-zero\" >\n"
                + "        <div class=\"container\">\n"
                + "            <div class=\"navbar-header\">\n"
                + "                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n"
                + "                    <span class=\"icon-bar\"></span>\n"
                + "                    <span class=\"icon-bar\"></span>\n"
                + "                    <span class=\"icon-bar\"></span>\n"
                + "                </button>\n"
                + "                <span class=\"navbar-brand\" href=\"index.html\">\n"
                + "\n"
                + "                    <!-- <img src=\"assets/img/logo.png\" /> -->\n"
                + "                  <a href=\"Report.html\"><h1>MuHybApps</h1></a>\n"
                + "                </span>\n"
                + "\n"
                + "            </div>\n"
                + "\n"
                + "        </div>\n"
                + "    </div>\n"
                + "    <!-- LOGO HEADER END-->\n"
                + "    <section class=\"menu-section\">\n"
                + "        <div class=\"container\">\n"
                + "            <div class=\"row\">\n"
                + "                <div class=\"col-md-12\">\n"
                + "                    <div class=\"navbar-collapse collapse \">\n"
                + "                        <ul id=\"menu-top\" class=\"nav navbar-nav navbar-right\">\n"
                + "                            <li><a href=\"Report.html\">Mutation Report</a></li>\n"
                + "                            <li><a href=\"About.html\" > About</a></li>\n"
                + "\n"
                + "                        </ul>\n"
                + "                    </div>\n"
                + "                </div>\n"
                + "\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </section>\n"
                + "     <!-- MENU SECTION END-->\n"
                + "    <div class=\"content-wrapper\">\n"
                + "         <div class=\"container\">\n"
                + "        <div class=\"row pad-botm\">\n"
                + "            <div class=\"col-md-12\">\n"
                + "                <h4 class=\"header-line\">" + fr.getFileName() + "</h4>\n"
                + "                \n"
                + "                            </div>\n"
                + "\n"
                + "        </div>\n"
                + "		<div class=\"row\">\n"
                + "                <div class=\"col-md-12\">\n"
                + "                    <!-- Advanced Tables -->\n"
                + "                    <div class=\"panel panel-default\">\n"
                + "                        <div class=\"panel-heading\">\n"
                + "                             Test Results\n"
                + "                        </div>\n"
                + "                        <div class=\"panel-body\">\n"
                + "                            <div class=\"table-responsive\">\n"
                + "                                <table class=\"table table-striped table-bordered\" >\n"
                + "                                    <thead>\n"
                + "                                        <tr>\n"
                + "                                            <th>Code</th>"+"<span class=\"badge badge-info\">Killed</span>"+"<span class=\"badge badge-error\">Survived</span>"+"<span class=\"badge badge-warning\">Exceptions</span>"+"<span class=\"badge badge-success\">Discarded</span>"
                + "                                            <th>Mutants</th>\n"
                + "                                        </tr>\n"
                + "                                    </thead>\n"
                + "                                    <tbody>\n"
                + "									<th>\n"
                + "									\n"
                + "									<pre>";
        secondPart = "</pre>\n"
                + "									\n"
                + "									</th>\n"
                + "                                            <th>\n"
                + "											<table class=\"table table-striped table-bordered table-hover\">\n"
                + "												<thead>\n"
                + "                                        <tr>\n"
                + "                                            <th>Line number</th>\n"
                + "                                            <th>Operator</th>\n"
                + "											<th>State</th>\n"
                + "                                            <th>Original line</th>\n"
                + "											<th>Replacement line</th>\n"
                + "                                            \n"
                + "                                        </tr>\n"
                + "                                    </thead>\n"
                + "									<tbody>";

        thirdPart = "</tbody>\n"
                + "											</table>\n"
                + "											\n"
                + "											\n"
                + "											</th>\n"
                + "									</tbody>\n"
                + "                                </table>\n"
                + "                            </div>\n"
                + "                            \n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                    <!--End Advanced Tables -->\n"
                + "                </div>\n"
                + "            </div>\n"
                + "		\n"
                + "		\n"
                + "		\n"
                + "		\n"
                + "\n"
                + "		\n"
                + "\n"
                + "                <!-- /. ROW  -->\n"
                + "            \n"
                + "                <!-- /. ROW  -->\n"
                + "            \n"
                + "    </div>\n"
                + "    </div>\n"
                + "     <!-- CONTENT-WRAPPER SECTION END-->\n"
                + "    <section class=\"footer-section\">\n"
                + "        <div class=\"container\">\n"
                + "            <div class=\"row\">\n"
                + "                <div class=\"col-md-12\">\n"
                + "                   &copy; 2019 MuHypApps | Designed by : Shazly Magdy\n"
                + "                </div>\n"
                + "\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </section>\n"
                + "\n"
                + "</body>\n"
                + "</html>";

    }

    public void generateHtmlFile(String path) {
        try {
            File mainFolder = new File(path + "\\Reports");

            PrintWriter pw = new PrintWriter(mainFolder + "\\" + fr.getFileName() + ".html");
            pw.print(firstPart);
            String fileCode = fr.getFileCode();
            String finalFileCode = "";
            Scanner s = new Scanner(fileCode);
            ArrayList<ResultsForEachFile> arr2 = fr.getArr();
            ArrayList<ResultsForEachLOC> arr3 = new ArrayList<ResultsForEachLOC>();
            boolean welldone = false;
            for (int i = 0; i < arr2.size(); i++) {
                if (arr3.isEmpty()) {
                    ResultsForEachLOC res = new ResultsForEachLOC(arr2.get(i).getFileName(), arr2.get(i).getLineCode());
                    if (arr2.get(i).getState().equals("Killed")) {
                        res.increaseKilled();
                    }
                    if (arr2.get(i).getState().equals("Survived")) {
                        res.increaseSurvived();
                    }
                    if (arr2.get(i).getState().equals("Exception")) {
                        res.increaseExeptions();
                    }
                    if (arr2.get(i).getState().equals("Syntax Error")) {
                        res.increaseSyntaxErrors();
                    }
                    arr3.add(res);
                } else {
                    for (int j = 0; j < arr3.size(); j++) {
                        if (arr2.get(i).getLineCode() == arr3.get(j).getLineNumber()) {
                            if (arr2.get(i).getState().equals("Killed")) {
                                arr3.get(j).increaseKilled();
                            }
                            if (arr2.get(i).getState().equals("Survived")) {
                                arr3.get(j).increaseSurvived();
                            }
                            if (arr2.get(i).getState().equals("Exception")) {
                                arr3.get(j).increaseExeptions();
                            }
                            if (arr2.get(i).getState().equals("Syntax Error")) {
                                arr3.get(j).increaseSyntaxErrors();
                            }
                            welldone = true;
                            break;
                        }
                    }
                    if (!welldone) {
                        ResultsForEachLOC res = new ResultsForEachLOC(arr2.get(i).getFileName(), arr2.get(i).getLineCode());
                        if (arr2.get(i).getState().equals("Killed")) {
                            res.increaseKilled();
                        }
                        if (arr2.get(i).getState().equals("Survived")) {
                            res.increaseSurvived();
                        }
                        if (arr2.get(i).getState().equals("Exception")) {
                            res.increaseExeptions();
                        }
                        if (arr2.get(i).getState().equals("Syntax Error")) {
                            res.increaseSyntaxErrors();
                        }
                        arr3.add(res);
                    }
                    welldone = false;
                }

            }
            int counter = 0;
            boolean done = false;
            while (s.hasNext()) {
                counter++;
                String line = s.nextLine();
                for (int i = 0; i < arr3.size(); i++) {
                    if (arr3.get(i).getLineNumber() == counter) {
                        finalFileCode += line;
                        if (arr3.get(i).getKilled() != 0) {
                            finalFileCode += "<span class=\"badge badge-info\">" + arr3.get(i).getKilled() + "</span>";
                        }
                        if (arr3.get(i).getSurvived() != 0) {
                            finalFileCode += "<span class=\"badge badge-error\">" + arr3.get(i).getSurvived() + "</span>";
                        }
                        if (arr3.get(i).getExceptions() != 0) {
                            finalFileCode += "<span class=\"badge badge-warning\">" + arr3.get(i).getExceptions() + "</span>";
                        }
                        if (arr3.get(i).getSyntaxErrors() != 0) {
                            finalFileCode += "<span class=\"badge badge-success\">" + arr3.get(i).getSyntaxErrors() + "</span>";
                        }
                        finalFileCode += "\n";
                        done = true;

                    } else {

                    }
                }
                if (!done) {
                    finalFileCode += line + "\n";
                }
                done = false;

            }
            pw.print(finalFileCode);
            pw.print(secondPart);
            String restString = generateTable(arr2);
            pw.print(restString);
            pw.print(thirdPart);
            pw.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerateReports.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String generateTable(ArrayList<ResultsForEachFile> arr) {

        ResultsForEachFile temp;
        for (int i = 1; i < arr.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (arr.get(j).getLineCode() < arr.get(j - 1).getLineCode()) {
                    temp = arr.get(j);
                    arr.set(j, arr.get(j - 1));
                    arr.set(j - 1, temp);
                }
            }
        }

        String restString = "";
        for (int i = 0; i < arr.size(); i++) {

            restString += "<tr class=\"odd gradeX\" onmouseover=\"toRed('l" + (arr.get(i).getLineCode() - 1) + "');\" onmouseleave=\"toGray('l" + (arr.get(i).getLineCode() - 1) + "');\">";
            restString += "<td>" + arr.get(i).getLineCode() + "</td>";
            restString += "<td>" + arr.get(i).getOperator() + "</td>";
            restString += "<td>" + arr.get(i).getState() + "</td>";
            restString += "<td><div  class=\"tooltip\">Show LOC<div class=\"top\"> <h3>" + arr.get(i).getOriginalLine() + "</h3><i></i></td>";

            restString += "<td><div class=\"tooltip\">Show LOC<div class=\"top\"> <h3>" + arr.get(i).getMutationLine() + "</h3><i></i></td>";
            restString += "</tr>";
        }

        return restString;
    }

}
