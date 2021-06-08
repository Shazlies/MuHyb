/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestRun;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Shad0o0
 */
public class RunServers implements Runnable {

    public static void main(String[] args) {
//        RunServers r = new RunServers();
//        r.runServers(4, "D:\\projects2\\ionic-angular-cart");
    }
    String path;
    static ArrayList<Process> processes = new ArrayList<Process>();
    static ArrayList<BufferedReader> readers = new ArrayList<BufferedReader>();
    static ArrayList<File> testFiles = new ArrayList<File>();
    static ArrayList<InputStreamReader> reads = new ArrayList<InputStreamReader>();
    static ArrayList<Integer> counters = new ArrayList<Integer>();
    int i;

    @Override
    public void run() {

       //runServers(path);
    }

    public RunServers(String path, int i) {
        System.out.println("from runservers " + path);
        System.out.println(i);
        this.path = path;
        this.i = i;
    }

    public static void changePort(String path, int i) {

        File root = new File(path + "\\e2e");
        String[] extensions = {"e2e-spec.ts"};
        boolean recursive = true;
        int counter = 8101 + i;
        System.out.println(counter);
        Collection files = FileUtils.listFiles(root, extensions, recursive);

        for (Iterator iterator = files.iterator(); iterator.hasNext();) {
            System.out.println("e2e-spec.ts");
            File file = (File) iterator.next();
            if (file.getName().endsWith(".e2e-spec.ts")) {
                //removeComments(file);
                String x = file.getAbsolutePath().replace(file.getName(), "");
                testFiles.add(file);
            }
        }
        for (int j = 0; j < testFiles.size(); j++) {
            File f = new File(testFiles.get(i).getAbsolutePath());
            Scanner scan;
            try {
                scan = new Scanner(f);

                String js = "";
                while (scan.hasNext()) {
                    String line = scan.nextLine();
                    if (line.contains("http://localhost:")) {
                        int c = line.indexOf("http://localhost:");
                        c += 17;
                        String p = line.substring(c, c + 4);
                        System.out.println(p);
                        line = line.replace(p, counter + "");
                        js += line + "\n";
                    } else {
                        js += line + "\n";
                    }
                }
                PrintWriter pw;

                pw = new PrintWriter(f.getAbsoluteFile());
                pw.print(js);
                pw.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RunServers.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void runServers2(String path) {
        ////////////////////////////////////////////
//        File root = new File(path + "\\e2e");
//        String[] extensions = {"e2e-spec.ts"};
//        boolean recursive = true;
        int counter = 8101 + i;
//        System.out.println(counter);
//        Collection files = FileUtils.listFiles(root, extensions, recursive);
//
//        for (Iterator iterator = files.iterator(); iterator.hasNext();) {
//            System.out.println("e2e-spec.ts");
//            File file = (File) iterator.next();
//            if (file.getName().endsWith(".e2e-spec.ts")) {
//                //removeComments(file);
//                String x = file.getAbsolutePath().replace(file.getName(), "");
//                testFiles.add(file);
//            }
//        }
//        for (int j = 0; j < testFiles.size(); j++) {
//            File f = new File(testFiles.get(i).getAbsolutePath());
//            Scanner scan;
//            try {
//                scan = new Scanner(f);
//            
//            String js = "";
//            while (scan.hasNext()) {
//                String line = scan.nextLine();
//                if (line.contains("http://localhost:")) {
//                    int c = line.indexOf("http://localhost:");
//                    c += 17;
//                    String p = line.substring(c, c + 4);
//                    System.out.println(p);
//                    line = line.replace(p, counter + "");
//                    js += line + "\n";
//                } else {
//                    js += line + "\n";
//                }
//            }
//            PrintWriter pw;
//            
//                pw = new PrintWriter(f.getAbsoluteFile());
//                pw.print(js);
//            pw.close();
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(RunServers.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//
//        }

        ////////////////////////
        System.out.println("RUN SERVERS RUNNNNNNN");
        File f = new File(path + "\\e2e\\protractor.conf.js");
        Scanner scan;
        try {
            scan = new Scanner(f);

            String js = "";
            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (line.contains("baseUrl: ")) {
                    String line2 = "baseUrl: 'http://localhost:" + counter + "/',";
                    js += line2 + "\n";
                } else {
                    js += line + "\n";
                }
            }
            PrintWriter pw = new PrintWriter(f.getAbsoluteFile());
            pw.print(js);
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RunServers.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            System.out.println(path + "\"" + " ionic serve --host=localhost --port=" + counter);
            //System.out.println(path);

            final ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/C", "ping -a www.google.com -n 10");
            final Process process = processBuilder.start();
            // final InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());

            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "pushd \"" + path + "\"" + " && ionic serve --host=localhost --port=" + counter);
            
            builder.redirectErrorStream(true);

            Process p = builder.start();
            final InputStreamReader inputStreamReader = new InputStreamReader(p.getInputStream());
            reads.add(inputStreamReader);
            counters.add(counter);
            while (appendText(inputStreamReader)) {
                ;
            }
            try {
                process.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(RunServers.class.getName()).log(Level.SEVERE, null, ex);
            }

            //BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            processes.add(p);
//            readers.add(r);
//            reads.add(r);
//            counters.add(counter);
//            String output = "";
//            while (true) {
//
//                String line = r.readLine();
//                output += line + "\n";
//                //System.out.println(line + "          "+i);
//                if (line.contains("Browser window opened")) {
////                    System.out.println("Mutant"+i);
////                    System.out.println(output);
////                    System.out.println("done");
//                    break;
//                }
//
//            }
        } catch (IOException ex) {
            Logger.getLogger(RunServers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean appendText(final InputStreamReader inputStreamReader) {
        try {
            final char[] buf = new char[256];
            final int read = inputStreamReader.read(buf);
//            System.out.println(read);
//            System.out.println(new String(buf));
//            if(new String(buf)==null){
//                return false;
//            }
//            return true;
            if(new String(buf).contains("Browser window opened")){
                return false;
            }
            else{
                return true;
            }
            
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void runServers(String path) {
        ////////////////////////////////////////////
//        File root = new File(path + "\\e2e");
//        String[] extensions = {"e2e-spec.ts"};
//        boolean recursive = true;
        int counter = 8101 + i;
//        System.out.println(counter);
//        Collection files = FileUtils.listFiles(root, extensions, recursive);
//
//        for (Iterator iterator = files.iterator(); iterator.hasNext();) {
//            System.out.println("e2e-spec.ts");
//            File file = (File) iterator.next();
//            if (file.getName().endsWith(".e2e-spec.ts")) {
//                //removeComments(file);
//                String x = file.getAbsolutePath().replace(file.getName(), "");
//                testFiles.add(file);
//            }
//        }
//        for (int j = 0; j < testFiles.size(); j++) {
//            File f = new File(testFiles.get(i).getAbsolutePath());
//            Scanner scan;
//            try {
//                scan = new Scanner(f);
//            
//            String js = "";
//            while (scan.hasNext()) {
//                String line = scan.nextLine();
//                if (line.contains("http://localhost:")) {
//                    int c = line.indexOf("http://localhost:");
//                    c += 17;
//                    String p = line.substring(c, c + 4);
//                    System.out.println(p);
//                    line = line.replace(p, counter + "");
//                    js += line + "\n";
//                } else {
//                    js += line + "\n";
//                }
//            }
//            PrintWriter pw;
//            
//                pw = new PrintWriter(f.getAbsoluteFile());
//                pw.print(js);
//            pw.close();
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(RunServers.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//
//        }

        ////////////////////////
        System.out.println("RUN SERVERS RUNNNNNNN");
        File f = new File(path + "\\e2e\\protractor.conf.js");
        Scanner scan;
        try {
            scan = new Scanner(f);

            String js = "";
            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (line.contains("baseUrl: ")) {
                    String line2 = "baseUrl: 'http://localhost:" + counter + "/',";
                    js += line2 + "\n";
                } else {
                    js += line + "\n";
                }
            }
            PrintWriter pw = new PrintWriter(f.getAbsoluteFile());
            pw.print(js);
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RunServers.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            System.out.println(path + "\"" + " ionic serve --host=localhost --port=" + counter);
            //System.out.println(path);
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "pushd \"" + path + "\"" + " && ionic serve --host=localhost --port=" + counter);

            builder.redirectErrorStream(true);

            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            processes.add(p);
            readers.add(r);
            //sreads.add(r);
            counters.add(counter);
            String output = "";
            while (true) {

                String line = r.readLine();
                output += line + "\n";
                //System.out.println(line + "          "+i);
                if(line.isEmpty()){
                    System.out.println("BReakkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                    break;
                }
//                if (line.contains("Browser window opened")) {
////                    System.out.println("Mutant"+i);
////                    System.out.println(output);
////                    System.out.println("done");
//                    break;
//                }

            }
        } catch (IOException ex) {
            Logger.getLogger(RunServers.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 

    public RunServers() {
        closeConnections();
    }

    public static void closeConnections() {
//        for (int j = 0; j < processes.size(); j++) {
//            //long pid = processes.get(i).pid(); 
//            processes.get(i).destroyForcibly().destroy();
//            try {
//                readers.get(i).close();
//                
//            } catch (IOException ex) {
//                Logger.getLogger(RunServers.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "pushd \"" + "c:\\" + "\"" + " && taskkill /F /IM \"node.exe\" /T");
        builder.redirectErrorStream(true);
        try {
            Process p = builder.start();
        } catch (IOException ex) {
            Logger.getLogger(RunServers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
