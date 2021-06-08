/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilesFounder;

import java.util.Scanner;

/**
 *
 * @author Home
 */
public class Test4 {
    static boolean mainFound = false;
    public static void main(String[] args) {
        String x = "constructor() { }\n"
                + "  addOne(num1){\n"
                + "	  var found = true;\n"
                + "	  var found2 = false;\n"
                + "	  let x = parseInt(num1);\n"
                + "	  if(true){\n"
                + "			if(x>1){\n"
                + "				x++;\n"
                + "			}\n"
                + "	  }\n"
                + "    this.total = x;\n"
                + "    console.log(this.total);\n"
                + "  }\n"
                + "\n"
                + "  getTotal(){\n"
                + "    return this.total;\n"
                + "  }";

        Scanner s = new Scanner(x);

        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.contains("(") && line.contains(")")) {
                mainFound = true;
                
            }
            if(mainFound){
                setOperator(line);
            }
        }

    }
    static int counter = 0;
    static boolean found = false;
    public static void setOperator(String line) {
        String part = "";
        System.out.println(line);
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '{') {
                counter++;
                found = true;
            }
            if (line.charAt(i) == '}') {
                counter--;
            }
            if(!found) {
                part += line.charAt(i);
            }
        }
        System.out.println(counter);
        if(counter==0&&found){
            System.out.println(part);
            found = false;
        }
        

    }
}
