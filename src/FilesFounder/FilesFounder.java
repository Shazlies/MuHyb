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
public class FilesFounder {

    ArrayList<FileAsString> tsFileCode = new ArrayList<FileAsString>();
    ArrayList<FileAsString> htmlFileCode = new ArrayList<FileAsString>();
    ArrayList<File> htmlFiles = new ArrayList<File>();
    ArrayList<File> tsFiles = new ArrayList<File>();
    ArrayList<File> testFiles = new ArrayList<File>();

    public ArrayList<FileAsString> getTsFileCode() {
        return tsFileCode;

    }

    public ArrayList<FileAsString> getHtmlFileCode() {
        return htmlFileCode;

    }

    public ArrayList<File> getHtmlFiles(String directoryName) throws IOException {

        File root = new File(directoryName);
        String[] extensions = {"html"};
        boolean recursive = true;

        Collection files = FileUtils.listFiles(root, extensions, recursive);

        for (Iterator iterator = files.iterator(); iterator.hasNext();) {
            File file = (File) iterator.next();
            if (file.getName().endsWith(".html")) {
                addHtmlFileCode(file);
                htmlFiles.add(file);
            }
        }
        return htmlFiles;
    }

    public ArrayList<File> getTsFiles(String directoryName) throws IOException {

        File root = new File(directoryName);
        String[] extensions = {"ts"};
        boolean recursive = true;

        Collection files = FileUtils.listFiles(root, extensions, recursive);

        for (Iterator iterator = files.iterator(); iterator.hasNext();) {
            File file = (File) iterator.next();
            if (file.getName().endsWith(".ts") && !file.getName().contains("spec") && !file.getName().contains("module") && !file.getName().equals("polyfills.ts") && !file.getName().equals("main.ts") && !file.getName().equals("test.ts")) {
                removeComments(file);
                tsFiles.add(file);
            }

        }
        return tsFiles;
    }

    ArrayList<String> arr = new ArrayList<String>();

    public ArrayList<File> getTestFiles(String directoryName) throws IOException {

        File root = new File(directoryName);
        String[] extensions = {"spec.ts"};
        boolean recursive = true;

        Collection files = FileUtils.listFiles(root, extensions, recursive);

        for (Iterator iterator = files.iterator(); iterator.hasNext();) {
            File file = (File) iterator.next();
            if (file.getName().endsWith("spec.ts")) {
                //removeComments(file);
                String x = file.getAbsolutePath().replace(file.getName(), "");
                arr.add(x.substring(0, x.length() - 1));
                testFiles.add(file);
            }
        }
        return testFiles;
    }

    public ArrayList<String> getTestFilesPath() {
        return arr;
    }

    boolean found = false;

    public void removeComments(File file) {
        try {
            Scanner sc = new Scanner(file);
            String output = "";
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (found) {
                    if (line.contains("*/")) {
                        int x = line.indexOf("*/");
                        String line2 = line.substring(x + 2, line.length());
                        output += line2 + "\n";
                        found = false;
                    }
                } else if (line.contains("/*")) {
                    int x = line.indexOf("/*");
                    String line2 = line.substring(0, x);
                    output += line2 + "\n";
                    found = true;
                } else if (line.contains("//")&&!line.contains("http")) {
                    int x = line.indexOf("//");
                    String line2 = line.substring(0, x);
                    output += line2 + "\n";
                    //System.out.println(")()())()()()(");

                    //System.out.println(line);
                    //System.out.println(x);
                } else {

                    output += line + "\n";
                }

            }

            PrintWriter pw = new PrintWriter(file);
            String adjusted = output.replaceAll("(?m)^[ \t]*\r?\n", "");
            pw.print(adjusted.trim());
            FileAsString f = new FileAsString(adjusted.trim(), file.getName(), "ts");
            tsFileCode.add(f);
            //System.out.println(output.trim());
            pw.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    public void addHtmlFileCode(File file) {
        try {
            Scanner sc = new Scanner(file);
            String output = "";
            while (sc.hasNext()) {
                String line = sc.nextLine();
                output += line + "\n";

            }

            PrintWriter pw = new PrintWriter(file);
            String adjusted = output.replaceAll("(?m)^[ \t]*\r?\n", "");
            pw.print(adjusted.trim());
            FileAsString f = new FileAsString(adjusted.trim(), file.getName(), "html");
            htmlFileCode.add(f);
            //System.out.println(output.trim());
            pw.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

}
