/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilesFounder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;


/**
 *
 * @author Home
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "pushd \"" + "c:\\" + "\"" + " && taskkill /F /IM \"node.exe\" /T");
            builder.redirectErrorStream(true);
            Process p = builder.start();
        
        
        
    }
    int i=0;
    ArrayList<File> testFiles = new ArrayList<File>();
    public void changePort(String path) throws FileNotFoundException {
        File root = new File(path + "\\e2e");
        String[] extensions = {"e2e-spec.ts"};
        boolean recursive = true;

        Collection files = FileUtils.listFiles(root, extensions, recursive);

        for (Iterator iterator = files.iterator(); iterator.hasNext();) {
            File file = (File) iterator.next();
            if (file.getName().endsWith(".e2e-spec.ts")) {
                //removeComments(file);
                String x = file.getAbsolutePath().replace(file.getName(), "");
                testFiles.add(file);
            }
        }
        for (int j = 0; j < testFiles.size(); j++) {
            File f = new File(testFiles.get(i).getAbsolutePath());
            Scanner scan = new Scanner(f);
            String js = "";
            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (line.contains("http://localhost:")) {
                    int c = line.indexOf("http://localhost:");
                    c+= 17;
                    String p = line.substring(c, c+4);
                    System.out.println(p);
                    line = line.replace(p, "8212");
                    //String line2 = "baseUrl: 'http://localhost:"+(8101+i) + "/',";
                    js += line + "\n";
                } else {
                    js += line + "\n";
                }
            }
            PrintWriter pw = new PrintWriter(f.getAbsoluteFile());
            pw.print(js);
            pw.close();

        }

    }
}
