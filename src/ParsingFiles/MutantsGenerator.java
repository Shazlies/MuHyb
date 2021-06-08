/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingFiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class MutantsGenerator {

    private static int counter = 0;
    ArrayList<Mutant> mutantsArray = new ArrayList<Mutant>();

    public void createMutantsFirstStep(String path, File file, boolean[] selectedOperators, int threadNumbers) throws IOException, InterruptedException {
        for (int i = 0; i < selectedOperators.length; i++) {
            if (selectedOperators[i]) {
                createMutantsSecondStep(path, file, i, threadNumbers);
            }
        }
    }
    
    public int getCounter(){
        return counter;
    }
    public static void setCounter(int c){
        counter += c;
    }
    public void createMutantsSecondStep(String appDirectoryRoot, File OriginalFile, int number, int threadNumbers) throws InterruptedException {
        String appMutantRoot = appDirectoryRoot + "\\_Mutant0"; // App root + "\\_Mutant"
        String filePath = OriginalFile.getAbsolutePath(); // Original file path
        //System.out.println(filePath + " filePath");
        String filePathWithoutFileName = filePath.replace(OriginalFile.getName(), ""); // File path without file name
        filePathWithoutFileName = filePathWithoutFileName.substring(0, filePathWithoutFileName.length() - 1); // remove last letter from the path "/"
        String MutantFiletoPlant = appMutantRoot + filePathWithoutFileName.replace(appDirectoryRoot, ""); // final mutant path to plant in
        System.out.println(number);
        if (number == 0&&filePath.toLowerCase().charAt(filePath.length()-1)=='s') {
            BooleanOperator b = new BooleanOperator();
            b.generateMutant(OriginalFile, threadNumbers);
            System.out.println("done boolean");
        }
        if (number == 1&&filePath.toLowerCase().charAt(filePath.length()-1)=='s') {
            EqualityOperator b = new EqualityOperator();
            b.generateMutant(OriginalFile, threadNumbers);
            System.out.println("done equality");
        }
        if (number == 2&&filePath.toLowerCase().charAt(filePath.length()-1)=='s') {
            LogicalOperators b = new LogicalOperators();
            b.generateMutant(OriginalFile, threadNumbers);
            System.out.println("done logical");
        }

        if (number == 3&&filePath.toLowerCase().charAt(filePath.length()-1)=='s') {
            IncrementsOperator i = new IncrementsOperator();
            i.generateMutant(OriginalFile, threadNumbers);
            System.out.println("done increment");
        }
        if (number == 4&&filePath.toLowerCase().charAt(filePath.length()-1)=='s') {
            MathOperators mo = new MathOperators();
            mo.generateMutant(OriginalFile, threadNumbers);
            System.out.println("done math");
        }
        if (number == 5&&filePath.toLowerCase().charAt(filePath.length()-1)=='s') {
            RelationalOperator ro = new RelationalOperator();
            ro.generateMutant(OriginalFile, threadNumbers);
            System.out.println("done relational");
        }
        if (number == 6&&filePath.toLowerCase().charAt(filePath.length()-1)=='s') {
            System.out.println(filePath);
            IfStatementOperator ro = new IfStatementOperator();
            ro.generateMutant(OriginalFile, threadNumbers);
            System.out.println("done if");
        }
        if (number == 7&&filePath.toLowerCase().charAt(filePath.length()-1)=='s') {
            FunctionBlockOperator ro = new FunctionBlockOperator();
            ro.generateMutant(OriginalFile, threadNumbers);
            System.out.println("done function empty");
        }
        if (number == 8&&filePath.toLowerCase().charAt(filePath.length()-1)=='s') {
            LoopOperator lo = new LoopOperator();
            lo.generateMutant(OriginalFile, threadNumbers);
            System.out.println("done loop");
        }
        if (number == 9&&filePath.toLowerCase().charAt(filePath.length()-1)=='s') {
            StatementDeletion sd = new StatementDeletion();
            sd.generateMutant(OriginalFile, threadNumbers);
            System.out.println("done statemenet deletion");
        }
        if (number == 10&&filePath.toLowerCase().charAt(filePath.length()-1)=='l') {
            RemoveClickEvent sd = new RemoveClickEvent();
            RepalceClickEvent as = new RepalceClickEvent();
            sd.generateMutant(OriginalFile, threadNumbers);
            as.generateMutant(OriginalFile, threadNumbers);
        }
        if (number == 11&&filePath.toLowerCase().charAt(filePath.length()-1)=='s') {
            LifeCycleMethodsOperators sd = new LifeCycleMethodsOperators();
            sd.generateMutant(OriginalFile, threadNumbers);
            System.out.println("lifecycle");
        }
        if (number == 12&&filePath.toLowerCase().charAt(filePath.length()-1)=='l') {
            
            InterpolationValueChangeOp sd = new InterpolationValueChangeOp();
            oneWayDataBindingReplacement o = new oneWayDataBindingReplacement();
            oneWayDataBindingReplacement2 o2 = new oneWayDataBindingReplacement2();
            TwoWayDataBindingReplacement t = new TwoWayDataBindingReplacement();
            System.out.println("----------------------1");
            sd.generateMutant(OriginalFile, threadNumbers);
            System.out.println("----------------------2");
            o.generateMutant(OriginalFile, threadNumbers);
            System.out.println("----------------------3");
            o2.generateMutant(OriginalFile, threadNumbers);
            System.out.println("----------------------4");
            t.generateMutant(OriginalFile, threadNumbers);
        }
        if (number == 13&&filePath.toLowerCase().charAt(filePath.length()-1)=='l') {
            NgForRemovalOp sd = new NgForRemovalOp();
            sd.generateMutant(OriginalFile, threadNumbers);
        }
        if (number == 14&&filePath.toLowerCase().charAt(filePath.length()-1)=='l') {
            GUIOperator sd = new GUIOperator();
            sd.generateMutant(OriginalFile, threadNumbers);
        }
        if (number == 15&&filePath.toLowerCase().charAt(filePath.length()-1)=='s') {
            //NavController sd = new NavController();
            //sd.generateMutant(OriginalFile, threadNumbers);
            
        }
        //write code here to test

    }
}
