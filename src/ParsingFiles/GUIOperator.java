/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/**
 *
 * @author Home
 */
public class GUIOperator {

    ArrayList<String> guiOperator = new ArrayList<String>(); // this list contains the output of mutants

    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant

    public static void main(String[] args) {
        File f = new File("E:\\projects\\PizzaHouse6\\_Mutant0\\src\\app\\pizza\\pizza.page.html");
        GUIOperator eo = new GUIOperator();
        eo.generateMutant(f, 4);
    }

    public void generateMutant(File file, int threadNumbers) {
        System.out.println("buttons");
        removeButtons(file, threadNumbers);
        System.out.println("badges");
        removeBadges(file, threadNumbers);
        System.out.println("checkbox");
        removeCheckBox(file, threadNumbers);
        System.out.println("chip");
        removeChip(file, threadNumbers);
        System.out.println("fabbutton");
        removeFabButton(file, threadNumbers);
        System.out.println("input");
        removeInput(file, threadNumbers);
        System.out.println("radio");
        removeRadioButton(file, threadNumbers);
        System.out.println("range");
        removeRange(file, threadNumbers);
        System.out.println("select");
        removeSelect(file, threadNumbers);
        System.out.println("item");
        removeItem(file, threadNumbers);
        System.out.println("card");
        removeCard(file, threadNumbers);
        if (!guiOperator.isEmpty()) {
            GenerateMutantFiles gm = new GenerateMutantFiles();
            gm.generateFiles(guiOperator, originalFiles, mutantInformation, threadNumbers);
        }

    }

    public void removeButtons(File file, int threadNumbers) {
        int lineNumber = 0; // to save the line number that we will edit in
        String outputEqualEqual = "";
        ArrayList<String> output = new ArrayList<String>();
        int counter = 0;
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            String fileText = "";
            while (sc.hasNext()) {
                String line = sc.nextLine();
                fileText += line;
            }

            Document doc = Jsoup.parse(fileText, "", Parser.xmlParser());
            Elements but = doc.select("ion-button");
            Iterator it = but.iterator();
            String html2 = doc.toString();

            String x = " ";
            html2 = html2.replaceAll("[\\t\\n]", "");
            for (int i = 0; i < 20; i++) {
                x += " ";
                html2 = html2.replace(x, "");
            }
            html2 = html2.replace("> ", ">");
            html2 = html2.replace(" <", "<");
            html2 = html2.replace("</br>", "");
            while (it.hasNext()) {
                String element = it.next().toString();
                output.add(element);
                String y = " ";
                element = element.replaceAll("[\\t\\n]", "");
                for (int i = 0; i < 20; i++) {
                    y += " ";
                    element = element.replace(y, "");
                }
                element = element.replace("> ", ">");
                element = element.replace(" <", "<");
                element = element.replace("</br>", "");
                System.out.println("X: " + element);
                String finalString = html2.replace(element, "%^%^%^%^" + counter + "%^%^%^%^");
                System.out.println("html: " + finalString);
                System.out.println("==========================");
                counter++;
                Document d = Jsoup.parse(finalString, "", Parser.xmlParser());
                guiOperator.add(d.toString().replace("</br>", ""));

            }

            counter = 0;
            for (int i = 0; i < guiOperator.size(); i++) {
                String guiText = guiOperator.get(i);
                Scanner scan = new Scanner(guiText);
                while (scan.hasNext()) {
                    lineNumber++;
                    String l = scan.nextLine();
                    if (l.contains("%^%^%^%^" + counter + "%^%^%^%^")) {
                        System.out.println("YESYESYEY");
                        guiOperator.set(i, guiOperator.get(i).replace("%^%^%^%^" + counter + "%^%^%^%^", ""));
                        counter++;
                        originalFiles.add(file);
                        MutantInformation m = new MutantInformation(lineNumber, "GUI Components Removal Operator", "<ion-button ..> </ion-button>", "");
                        mutantInformation.add(m);
                    }
                }
                lineNumber = 0;
            }
            System.out.println("******************************");
            System.out.println(guiOperator.size());
            System.out.println(originalFiles.size());
            System.out.println(mutantInformation.size());
            System.out.println("*****************************");

            sc.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void removeBadges(File file, int threadNumbers) {
        int lineNumber = 0; // to save the line number that we will edit in
        String outputEqualEqual = "";
        ArrayList<String> output = new ArrayList<String>();
        int counter = 0;
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            String fileText = "";
            while (sc.hasNext()) {
                String line = sc.nextLine();
                fileText += line;
            }

            Document doc = Jsoup.parse(fileText, "", Parser.xmlParser());
            Elements but = doc.select("ion-badge");
            Iterator it = but.iterator();
            String html2 = doc.toString();

            String x = " ";
            html2 = html2.replaceAll("[\\t\\n]", "");
            for (int i = 0; i < 20; i++) {
                x += " ";
                html2 = html2.replace(x, "");
            }
            html2 = html2.replace("> ", ">");
            html2 = html2.replace(" <", "<");
            html2 = html2.replace("</br>", "");
            while (it.hasNext()) {
                String element = it.next().toString();
                output.add(element);
                String y = " ";
                element = element.replaceAll("[\\t\\n]", "");
                for (int i = 0; i < 20; i++) {
                    y += " ";
                    element = element.replace(y, "");
                }
                element = element.replace("> ", ">");
                element = element.replace(" <", "<");
                element = element.replace("</br>", "");
                System.out.println("X: " + element);
                String finalString = html2.replace(element, "%^%^%^%^" + counter + "%^%^%^%^");
                System.out.println("html: " + finalString);
                System.out.println("==========================");
                counter++;
                Document d = Jsoup.parse(finalString, "", Parser.xmlParser());
                guiOperator.add(d.toString().replace("</br>", ""));

            }

            counter = 0;
            for (int i = 0; i < guiOperator.size(); i++) {
                String guiText = guiOperator.get(i);
                Scanner scan = new Scanner(guiText);
                while (scan.hasNext()) {
                    lineNumber++;
                    String l = scan.nextLine();
                    if (l.contains("%^%^%^%^" + counter + "%^%^%^%^")) {
                        System.out.println("YESYESYEY");
                        guiOperator.set(i, guiOperator.get(i).replace("%^%^%^%^" + counter + "%^%^%^%^", ""));
                        counter++;
                        originalFiles.add(file);
                        MutantInformation m = new MutantInformation(lineNumber, "GUI Components Removal Operator", "<ion-badge ..> </ion-badge>", "");
                        mutantInformation.add(m);
                    }
                }
                lineNumber = 0;
            }
            System.out.println("******************************");
            System.out.println(guiOperator.size());
            System.out.println(originalFiles.size());
            System.out.println(mutantInformation.size());
            System.out.println("*****************************");

            sc.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void removeCheckBox(File file, int threadNumbers) {
        int lineNumber = 0; // to save the line number that we will edit in
        String outputEqualEqual = "";
        ArrayList<String> output = new ArrayList<String>();
        int counter = 0;
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            String fileText = "";
            while (sc.hasNext()) {
                String line = sc.nextLine();
                fileText += line;
            }

            Document doc = Jsoup.parse(fileText, "", Parser.xmlParser());
            Elements but = doc.select("ion-checkbox");
            Iterator it = but.iterator();
            String html2 = doc.toString();

            String x = " ";
            html2 = html2.replaceAll("[\\t\\n]", "");
            for (int i = 0; i < 20; i++) {
                x += " ";
                html2 = html2.replace(x, "");
            }
            html2 = html2.replace("> ", ">");
            html2 = html2.replace(" <", "<");
            html2 = html2.replace("</br>", "");
            while (it.hasNext()) {
                String element = it.next().toString();
                output.add(element);
                String y = " ";
                element = element.replaceAll("[\\t\\n]", "");
                for (int i = 0; i < 20; i++) {
                    y += " ";
                    element = element.replace(y, "");
                }
                element = element.replace("> ", ">");
                element = element.replace(" <", "<");
                element = element.replace("</br>", "");
                System.out.println("X: " + element);
                String finalString = html2.replace(element, "%^%^%^%^" + counter + "%^%^%^%^");
                System.out.println("html: " + finalString);
                System.out.println("==========================");
                counter++;
                Document d = Jsoup.parse(finalString, "", Parser.xmlParser());
                guiOperator.add(d.toString().replace("</br>", ""));

            }

            counter = 0;
            for (int i = 0; i < guiOperator.size(); i++) {
                String guiText = guiOperator.get(i);
                Scanner scan = new Scanner(guiText);
                while (scan.hasNext()) {
                    lineNumber++;
                    String l = scan.nextLine();
                    if (l.contains("%^%^%^%^" + counter + "%^%^%^%^")) {
                        System.out.println("YESYESYEY");
                        guiOperator.set(i, guiOperator.get(i).replace("%^%^%^%^" + counter + "%^%^%^%^", ""));
                        counter++;
                        originalFiles.add(file);
                        MutantInformation m = new MutantInformation(lineNumber, "GUI Components Removal Operator", "<ion-checkbox ..> </ion-checkbox>", "");
                        mutantInformation.add(m);
                    }
                }
                lineNumber = 0;
            }
            System.out.println("******************************");
            System.out.println(guiOperator.size());
            System.out.println(originalFiles.size());
            System.out.println(mutantInformation.size());
            System.out.println("*****************************");

            sc.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void removeChip(File file, int threadNumbers) {
        int lineNumber = 0; // to save the line number that we will edit in
        String outputEqualEqual = "";
        ArrayList<String> output = new ArrayList<String>();
        int counter = 0;
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            String fileText = "";
            while (sc.hasNext()) {
                String line = sc.nextLine();
                fileText += line;
            }

            Document doc = Jsoup.parse(fileText, "", Parser.xmlParser());
            Elements but = doc.select("ion-chip");
            Iterator it = but.iterator();
            String html2 = doc.toString();

            String x = " ";
            html2 = html2.replaceAll("[\\t\\n]", "");
            for (int i = 0; i < 20; i++) {
                x += " ";
                html2 = html2.replace(x, "");
            }
            html2 = html2.replace("> ", ">");
            html2 = html2.replace(" <", "<");
            html2 = html2.replace("</br>", "");
            while (it.hasNext()) {
                String element = it.next().toString();
                output.add(element);
                String y = " ";
                element = element.replaceAll("[\\t\\n]", "");
                for (int i = 0; i < 20; i++) {
                    y += " ";
                    element = element.replace(y, "");
                }
                element = element.replace("> ", ">");
                element = element.replace(" <", "<");
                element = element.replace("</br>", "");
                System.out.println("X: " + element);
                String finalString = html2.replace(element, "%^%^%^%^" + counter + "%^%^%^%^");
                System.out.println("html: " + finalString);
                System.out.println("==========================");
                counter++;
                Document d = Jsoup.parse(finalString, "", Parser.xmlParser());
                guiOperator.add(d.toString().replace("</br>", ""));

            }

            counter = 0;
            for (int i = 0; i < guiOperator.size(); i++) {
                String guiText = guiOperator.get(i);
                Scanner scan = new Scanner(guiText);
                while (scan.hasNext()) {
                    lineNumber++;
                    String l = scan.nextLine();
                    if (l.contains("%^%^%^%^" + counter + "%^%^%^%^")) {
                        System.out.println("YESYESYEY");
                        guiOperator.set(i, guiOperator.get(i).replace("%^%^%^%^" + counter + "%^%^%^%^", ""));
                        counter++;
                        originalFiles.add(file);
                        MutantInformation m = new MutantInformation(lineNumber, "GUI Components Removal Operator", "<ion-chip ..> </ion-chip>", "");
                        mutantInformation.add(m);
                    }
                }
                lineNumber = 0;
            }
            System.out.println("******************************");
            System.out.println(guiOperator.size());
            System.out.println(originalFiles.size());
            System.out.println(mutantInformation.size());
            System.out.println("*****************************");

            sc.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void removeFabButton(File file, int threadNumbers) {
        int lineNumber = 0; // to save the line number that we will edit in
        String outputEqualEqual = "";
        ArrayList<String> output = new ArrayList<String>();
        int counter = 0;
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            String fileText = "";
            while (sc.hasNext()) {
                String line = sc.nextLine();
                fileText += line;
            }

            Document doc = Jsoup.parse(fileText, "", Parser.xmlParser());
            Elements but = doc.select("ion-fab-button");
            Iterator it = but.iterator();
            String html2 = doc.toString();

            String x = " ";
            html2 = html2.replaceAll("[\\t\\n]", "");
            for (int i = 0; i < 20; i++) {
                x += " ";
                html2 = html2.replace(x, "");
            }
            html2 = html2.replace("> ", ">");
            html2 = html2.replace(" <", "<");
            html2 = html2.replace("</br>", "");
            while (it.hasNext()) {
                String element = it.next().toString();
                output.add(element);
                String y = " ";
                element = element.replaceAll("[\\t\\n]", "");
                for (int i = 0; i < 20; i++) {
                    y += " ";
                    element = element.replace(y, "");
                }
                element = element.replace("> ", ">");
                element = element.replace(" <", "<");
                element = element.replace("</br>", "");
                System.out.println("X: " + element);
                String finalString = html2.replace(element, "%^%^%^%^" + counter + "%^%^%^%^");
                System.out.println("html: " + finalString);
                System.out.println("==========================");
                counter++;
                Document d = Jsoup.parse(finalString, "", Parser.xmlParser());
                guiOperator.add(d.toString().replace("</br>", ""));

            }

            counter = 0;
            for (int i = 0; i < guiOperator.size(); i++) {
                String guiText = guiOperator.get(i);
                Scanner scan = new Scanner(guiText);
                while (scan.hasNext()) {
                    lineNumber++;
                    String l = scan.nextLine();
                    if (l.contains("%^%^%^%^" + counter + "%^%^%^%^")) {
                        System.out.println("YESYESYEY");
                        guiOperator.set(i, guiOperator.get(i).replace("%^%^%^%^" + counter + "%^%^%^%^", ""));
                        counter++;
                        originalFiles.add(file);
                        MutantInformation m = new MutantInformation(lineNumber, "GUI Components Removal Operator", "<ion-fab-button ..> </ion-fab-button>", "");
                        mutantInformation.add(m);
                    }
                }
                lineNumber = 0;
            }
            System.out.println("******************************");
            System.out.println(guiOperator.size());
            System.out.println(originalFiles.size());
            System.out.println(mutantInformation.size());
            System.out.println("*****************************");

            sc.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void removeInput(File file, int threadNumbers) {
        int lineNumber = 0; // to save the line number that we will edit in
        String outputEqualEqual = "";
        ArrayList<String> output = new ArrayList<String>();
        int counter = 0;
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            String fileText = "";
            while (sc.hasNext()) {
                String line = sc.nextLine();
                fileText += line;
            }

            Document doc = Jsoup.parse(fileText, "", Parser.xmlParser());
            Elements but = doc.select("ion-input");
            Iterator it = but.iterator();
            String html2 = doc.toString();

            String x = " ";
            html2 = html2.replaceAll("[\\t\\n]", "");
            for (int i = 0; i < 20; i++) {
                x += " ";
                html2 = html2.replace(x, "");
            }
            html2 = html2.replace("> ", ">");
            html2 = html2.replace(" <", "<");
            html2 = html2.replace("</br>", "");
            while (it.hasNext()) {
                String element = it.next().toString();
                output.add(element);
                String y = " ";
                element = element.replaceAll("[\\t\\n]", "");
                for (int i = 0; i < 20; i++) {
                    y += " ";
                    element = element.replace(y, "");
                }
                element = element.replace("> ", ">");
                element = element.replace(" <", "<");
                element = element.replace("</br>", "");
                System.out.println("X: " + element);
                String finalString = html2.replace(element, "%^%^%^%^" + counter + "%^%^%^%^");
                System.out.println("html: " + finalString);
                System.out.println("==========================");
                counter++;
                Document d = Jsoup.parse(finalString, "", Parser.xmlParser());
                guiOperator.add(d.toString().replace("</br>", ""));

            }

            counter = 0;
            for (int i = 0; i < guiOperator.size(); i++) {
                String guiText = guiOperator.get(i);
                Scanner scan = new Scanner(guiText);
                while (scan.hasNext()) {
                    lineNumber++;
                    String l = scan.nextLine();
                    if (l.contains("%^%^%^%^" + counter + "%^%^%^%^")) {
                        System.out.println("YESYESYEY");
                        guiOperator.set(i, guiOperator.get(i).replace("%^%^%^%^" + counter + "%^%^%^%^", ""));
                        counter++;
                        originalFiles.add(file);
                        MutantInformation m = new MutantInformation(lineNumber, "GUI Components Removal Operator", "<ion-input ..> </ion-input>", "");
                        mutantInformation.add(m);
                    }
                }
                lineNumber = 0;
            }
            System.out.println("******************************");
            System.out.println(guiOperator.size());
            System.out.println(originalFiles.size());
            System.out.println(mutantInformation.size());
            System.out.println("*****************************");

            sc.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void removeRadioButton(File file, int threadNumbers) {
        int lineNumber = 0; // to save the line number that we will edit in
        String outputEqualEqual = "";
        ArrayList<String> output = new ArrayList<String>();
        int counter = 0;
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            String fileText = "";
            while (sc.hasNext()) {
                String line = sc.nextLine();
                fileText += line;
            }

            Document doc = Jsoup.parse(fileText, "", Parser.xmlParser());
            Elements but = doc.select("ion-radio");
            Iterator it = but.iterator();
            String html2 = doc.toString();

            String x = " ";
            html2 = html2.replaceAll("[\\t\\n]", "");
            for (int i = 0; i < 20; i++) {
                x += " ";
                html2 = html2.replace(x, "");
            }
            html2 = html2.replace("> ", ">");
            html2 = html2.replace(" <", "<");
            html2 = html2.replace("</br>", "");
            while (it.hasNext()) {
                String element = it.next().toString();
                output.add(element);
                String y = " ";
                element = element.replaceAll("[\\t\\n]", "");
                for (int i = 0; i < 20; i++) {
                    y += " ";
                    element = element.replace(y, "");
                }
                element = element.replace("> ", ">");
                element = element.replace(" <", "<");
                element = element.replace("</br>", "");
                System.out.println("X: " + element);
                String finalString = html2.replace(element, "%^%^%^%^" + counter + "%^%^%^%^");
                System.out.println("html: " + finalString);
                System.out.println("==========================");
                counter++;
                Document d = Jsoup.parse(finalString, "", Parser.xmlParser());
                guiOperator.add(d.toString().replace("</br>", ""));

            }

            counter = 0;
            for (int i = 0; i < guiOperator.size(); i++) {
                String guiText = guiOperator.get(i);
                Scanner scan = new Scanner(guiText);
                while (scan.hasNext()) {
                    lineNumber++;
                    String l = scan.nextLine();
                    if (l.contains("%^%^%^%^" + counter + "%^%^%^%^")) {
                        System.out.println("YESYESYEY");
                        guiOperator.set(i, guiOperator.get(i).replace("%^%^%^%^" + counter + "%^%^%^%^", ""));
                        counter++;
                        originalFiles.add(file);
                        MutantInformation m = new MutantInformation(lineNumber, "GUI Components Removal Operator", "<ion-radio ..> </ion-radio>", "");
                        mutantInformation.add(m);
                    }
                }
                lineNumber = 0;
            }
            System.out.println("******************************");
            System.out.println(guiOperator.size());
            System.out.println(originalFiles.size());
            System.out.println(mutantInformation.size());
            System.out.println("*****************************");

            sc.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void removeRange(File file, int threadNumbers) {
        int lineNumber = 0; // to save the line number that we will edit in
        String outputEqualEqual = "";
        ArrayList<String> output = new ArrayList<String>();
        int counter = 0;
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            String fileText = "";
            while (sc.hasNext()) {
                String line = sc.nextLine();
                fileText += line;
            }

            Document doc = Jsoup.parse(fileText, "", Parser.xmlParser());
            Elements but = doc.select("ion-range");
            Iterator it = but.iterator();
            String html2 = doc.toString();

            String x = " ";
            html2 = html2.replaceAll("[\\t\\n]", "");
            for (int i = 0; i < 20; i++) {
                x += " ";
                html2 = html2.replace(x, "");
            }
            html2 = html2.replace("> ", ">");
            html2 = html2.replace(" <", "<");
            html2 = html2.replace("</br>", "");
            while (it.hasNext()) {
                String element = it.next().toString();
                output.add(element);
                String y = " ";
                element = element.replaceAll("[\\t\\n]", "");
                for (int i = 0; i < 20; i++) {
                    y += " ";
                    element = element.replace(y, "");
                }
                element = element.replace("> ", ">");
                element = element.replace(" <", "<");
                element = element.replace("</br>", "");
                System.out.println("X: " + element);
                String finalString = html2.replace(element, "%^%^%^%^" + counter + "%^%^%^%^");
                System.out.println("html: " + finalString);
                System.out.println("==========================");
                counter++;
                Document d = Jsoup.parse(finalString, "", Parser.xmlParser());
                guiOperator.add(d.toString().replace("</br>", ""));

            }

            counter = 0;
            for (int i = 0; i < guiOperator.size(); i++) {
                String guiText = guiOperator.get(i);
                Scanner scan = new Scanner(guiText);
                while (scan.hasNext()) {
                    lineNumber++;
                    String l = scan.nextLine();
                    if (l.contains("%^%^%^%^" + counter + "%^%^%^%^")) {
                        System.out.println("YESYESYEY");
                        guiOperator.set(i, guiOperator.get(i).replace("%^%^%^%^" + counter + "%^%^%^%^", ""));
                        counter++;
                        originalFiles.add(file);
                        MutantInformation m = new MutantInformation(lineNumber, "GUI Components Removal Operator", "<ion-range ..> </ion-range>", "");
                        mutantInformation.add(m);
                    }
                }
                lineNumber = 0;
            }
            System.out.println("******************************");
            System.out.println(guiOperator.size());
            System.out.println(originalFiles.size());
            System.out.println(mutantInformation.size());
            System.out.println("*****************************");

            sc.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void removeSelect(File file, int threadNumbers) {
        int lineNumber = 0; // to save the line number that we will edit in
        String outputEqualEqual = "";
        ArrayList<String> output = new ArrayList<String>();
        int counter = 0;
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            String fileText = "";
            while (sc.hasNext()) {
                String line = sc.nextLine();
                fileText += line;
            }

            Document doc = Jsoup.parse(fileText, "", Parser.xmlParser());
            Elements but = doc.select("ion-select");
            Iterator it = but.iterator();
            String html2 = doc.toString();

            String x = " ";
            html2 = html2.replaceAll("[\\t\\n]", "");
            for (int i = 0; i < 20; i++) {
                x += " ";
                html2 = html2.replace(x, "");
            }
            html2 = html2.replace("> ", ">");
            html2 = html2.replace(" <", "<");
            html2 = html2.replace("</br>", "");
            while (it.hasNext()) {
                String element = it.next().toString();
                output.add(element);
                String y = " ";
                element = element.replaceAll("[\\t\\n]", "");
                for (int i = 0; i < 20; i++) {
                    y += " ";
                    element = element.replace(y, "");
                }
                element = element.replace("> ", ">");
                element = element.replace(" <", "<");
                element = element.replace("</br>", "");
                System.out.println("X: " + element);
                String finalString = html2.replace(element, "%^%^%^%^" + counter + "%^%^%^%^");
                System.out.println("html: " + finalString);
                System.out.println("==========================");
                counter++;
                Document d = Jsoup.parse(finalString, "", Parser.xmlParser());
                guiOperator.add(d.toString().replace("</br>", ""));

            }

            counter = 0;
            for (int i = 0; i < guiOperator.size(); i++) {
                String guiText = guiOperator.get(i);
                Scanner scan = new Scanner(guiText);
                while (scan.hasNext()) {
                    lineNumber++;
                    String l = scan.nextLine();
                    if (l.contains("%^%^%^%^" + counter + "%^%^%^%^")) {
                        System.out.println("YESYESYEY");
                        guiOperator.set(i, guiOperator.get(i).replace("%^%^%^%^" + counter + "%^%^%^%^", ""));
                        counter++;
                        originalFiles.add(file);
                        MutantInformation m = new MutantInformation(lineNumber, "GUI Components Removal Operator", "<ion-select ..> </ion-select>", "");
                        mutantInformation.add(m);
                    }
                }
                lineNumber = 0;
            }
            System.out.println("******************************");
            System.out.println(guiOperator.size());
            System.out.println(originalFiles.size());
            System.out.println(mutantInformation.size());
            System.out.println("*****************************");

            sc.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void removeItem(File file, int threadNumbers) {
        int lineNumber = 0; // to save the line number that we will edit in
        String outputEqualEqual = "";
        ArrayList<String> output = new ArrayList<String>();
        int counter = 0;
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            String fileText = "";
            while (sc.hasNext()) {
                String line = sc.nextLine();
                fileText += line;
            }

            Document doc = Jsoup.parse(fileText, "", Parser.xmlParser());
            Elements but = doc.select("ion-item");
            Iterator it = but.iterator();
            String html2 = doc.toString();

            String x = " ";
            html2 = html2.replaceAll("[\\t\\n]", "");
            for (int i = 0; i < 20; i++) {
                x += " ";
                html2 = html2.replace(x, "");
            }
            html2 = html2.replace("> ", ">");
            html2 = html2.replace(" <", "<");
            html2 = html2.replace("</br>", "");
            while (it.hasNext()) {
                String element = it.next().toString();
                output.add(element);
                String y = " ";
                element = element.replaceAll("[\\t\\n]", "");
                for (int i = 0; i < 20; i++) {
                    y += " ";
                    element = element.replace(y, "");
                }
                element = element.replace("> ", ">");
                element = element.replace(" <", "<");
                element = element.replace("</br>", "");
                System.out.println("X: " + element);
                String finalString = html2.replace(element, "%^%^%^%^" + counter + "%^%^%^%^");
                System.out.println("html: " + finalString);
                System.out.println("==========================");
                counter++;
                Document d = Jsoup.parse(finalString, "", Parser.xmlParser());
                guiOperator.add(d.toString().replace("</br>", ""));

            }

            counter = 0;
            for (int i = 0; i < guiOperator.size(); i++) {
                String guiText = guiOperator.get(i);
                Scanner scan = new Scanner(guiText);
                while (scan.hasNext()) {
                    lineNumber++;
                    String l = scan.nextLine();
                    if (l.contains("%^%^%^%^" + counter + "%^%^%^%^")) {
                        System.out.println("YESYESYEY");
                        guiOperator.set(i, guiOperator.get(i).replace("%^%^%^%^" + counter + "%^%^%^%^", ""));
                        counter++;
                        originalFiles.add(file);
                        MutantInformation m = new MutantInformation(lineNumber, "GUI Components Removal Operator", "<ion-item ..> </ion-item>", "");
                        mutantInformation.add(m);
                    }
                }
                lineNumber = 0;
            }
            System.out.println("******************************");
            System.out.println(guiOperator.size());
            System.out.println(originalFiles.size());
            System.out.println(mutantInformation.size());
            System.out.println("*****************************");

            sc.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    public void removeCard(File file, int threadNumbers) {
        int lineNumber = 0; // to save the line number that we will edit in
        String outputEqualEqual = "";
        ArrayList<String> output = new ArrayList<String>();
        int counter = 0;
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            String fileText = "";
            while (sc.hasNext()) {
                String line = sc.nextLine();
                fileText += line;
            }

            Document doc = Jsoup.parse(fileText, "", Parser.xmlParser());
            Elements but = doc.select("ion-card");
            Iterator it = but.iterator();
            String html2 = doc.toString();

            String x = " ";
            html2 = html2.replaceAll("[\\t\\n]", "");
            for (int i = 0; i < 20; i++) {
                x += " ";
                html2 = html2.replace(x, "");
            }
            html2 = html2.replace("> ", ">");
            html2 = html2.replace(" <", "<");
            html2 = html2.replace("</br>", "");
            while (it.hasNext()) {
                String element = it.next().toString();
                output.add(element);
                String y = " ";
                element = element.replaceAll("[\\t\\n]", "");
                for (int i = 0; i < 20; i++) {
                    y += " ";
                    element = element.replace(y, "");
                }
                element = element.replace("> ", ">");
                element = element.replace(" <", "<");
                element = element.replace("</br>", "");
                System.out.println("X: " + element);
                System.out.println("html2: "+html2);
                String finalString = html2.replace(element, "%^%^%^%^" + counter + "%^%^%^%^");
                System.out.println("html: " + finalString);
                System.out.println("==========================");
                counter++;
                Document d = Jsoup.parse(finalString, "", Parser.xmlParser());
                guiOperator.add(d.toString().replace("</br>", ""));

            }

            counter = 0;
            for (int i = 0; i < guiOperator.size(); i++) {
                String guiText = guiOperator.get(i);
                Scanner scan = new Scanner(guiText);
                while (scan.hasNext()) {
                    lineNumber++;
                    String l = scan.nextLine();
                    if (l.contains("%^%^%^%^" + counter + "%^%^%^%^")) {
                        System.out.println("YESYESYEY");
                        guiOperator.set(i, guiOperator.get(i).replace("%^%^%^%^" + counter + "%^%^%^%^", ""));
                        counter++;
                        originalFiles.add(file);
                        MutantInformation m = new MutantInformation(lineNumber, "GUI Components Removal Operator", "<ion-card ..> </ion-card>", "");
                        mutantInformation.add(m);
                    }
                }
                lineNumber = 0;
            }
            System.out.println("******************************");
            System.out.println(guiOperator.size());
            System.out.println(originalFiles.size());
            System.out.println(mutantInformation.size());
            System.out.println("*****************************");

            sc.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
}
