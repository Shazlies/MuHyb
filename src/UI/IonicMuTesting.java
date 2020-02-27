/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import FilesFounder.FileAsString;
import java.io.File;
import java.util.ArrayList;

import FilesFounder.FilesFounder;
import ParsingFiles.GenerateTestFiles;
import ParsingFiles.MutantsGenerator;
import ParsingFiles.OriginalCopiesClass;
import Reports.AnalyzeResults;
import Reports.Results;
import Reports.GenerateReports;
import Reports.MutationResults;
import Reports.ResultsForEachFile;
import Reports.ResultsSort;
import TestRun.MainTestRunner;

import TestRun.TestRunner;
import java.awt.Desktop;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Home
 */
public class IonicMuTesting extends javax.swing.JFrame {

    boolean[] selectedOperators = new boolean[10];
    MutantsGenerator mu;
    //IncrementsOperator increment = new IncrementsOperator();
    ArrayList<File> htmlFiles = new ArrayList<File>();
    ArrayList<File> tsFiles = new ArrayList<File>();
    ArrayList<FileAsString> tsFileCodes = new ArrayList<FileAsString>();
    ArrayList<File> testFiles = new ArrayList<File>();

    /**
     * Creates new form IonicMuTesting
     */
    public IonicMuTesting() {

        this.setTitle("Ionic Mutation Testing");
        this.setVisible(true);
        initComponents();
        int processors = Runtime.getRuntime().availableProcessors();
        recomendedThreads.setText("Recemonded: " + (processors / 2));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        generateMutants = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        folderPath = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        booleanLiterals = new javax.swing.JCheckBox();
        equalityOperator = new javax.swing.JCheckBox();
        logicalOperator = new javax.swing.JCheckBox();
        increments = new javax.swing.JCheckBox();
        mathOperations = new javax.swing.JCheckBox();
        relationalOperations = new javax.swing.JCheckBox();
        jScrollBar1 = new javax.swing.JScrollBar();
        ifStatementOperator = new javax.swing.JCheckBox();
        emptyFunctionBlockOperator = new javax.swing.JCheckBox();
        loopOperator = new javax.swing.JCheckBox();
        statementDeletionOp = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        browse = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        pb = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        numberOfThreadsTF = new javax.swing.JTextField();
        recomendedThreads = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        generateMutants.setText("Generate Mutants");
        generateMutants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateMutantsActionPerformed(evt);
            }
        });

        jLabel1.setText("Folder Path:");

        folderPath.setEditable(false);

        booleanLiterals.setText("Boolean Operator");

        equalityOperator.setText("Equality Operator");

        logicalOperator.setText("Logical Operator");

        increments.setText("Incremental Operator");

        mathOperations.setText("Mathematics Operator");

        relationalOperations.setText("Relational Operator");

        jScrollBar1.setMaximum(30);
        jScrollBar1.setToolTipText("");

        ifStatementOperator.setText("If Statement Operator");

        emptyFunctionBlockOperator.setText("Empty Function Block Operator");

        loopOperator.setText("Loop Operator");

        statementDeletionOp.setText("Statement Deletion Operator");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(relationalOperations)
                    .addComponent(mathOperations)
                    .addComponent(increments)
                    .addComponent(ifStatementOperator)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(booleanLiterals)
                            .addComponent(equalityOperator)
                            .addComponent(logicalOperator))
                        .addGap(100, 100, 100)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statementDeletionOp)
                            .addComponent(loopOperator)
                            .addComponent(emptyFunctionBlockOperator))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(booleanLiterals)
                    .addComponent(emptyFunctionBlockOperator))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(equalityOperator)
                    .addComponent(loopOperator))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logicalOperator)
                    .addComponent(statementDeletionOp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increments)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mathOperations)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(relationalOperations)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ifStatementOperator)
                .addContainerGap(50, Short.MAX_VALUE))
            .addComponent(jScrollBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        browse.setText("Browse");
        browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        pb.setStringPainted(true);

        jLabel2.setText("Number of threads:");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem1.setText("Help");
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("About");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(folderPath)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(browse)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(33, 33, 33))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(generateMutants, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numberOfThreadsTF, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recomendedThreads)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(folderPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberOfThreadsTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(recomendedThreads))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateMutants)
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateMutantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateMutantsActionPerformed
        // TODO add your handling code here:
        new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                doWork();
                return null;
            }
        }.execute();

    }//GEN-LAST:event_generateMutantsActionPerformed
    ArrayList<String> testFilesPathes;
    int threadNumbers;

    public void doWork() {
        threadNumbers = Integer.parseInt(numberOfThreadsTF.getText());
        String path = folderPath.getText();
        //System.out.println(path);
        path = "E:\\projects\\PizzaHouse3";
        if (path.equals("")) {
            JOptionPane.showMessageDialog(null, "Please Enter a valid path");

        } else {
            jTextArea1.append("\nCopying " + threadNumbers + " Copies\n");
            ExecutorService ex = Executors.newFixedThreadPool(threadNumbers);
            for (int i = 0; i < threadNumbers; i++) {
                ex.submit(new OriginalCopiesClass(path, i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex1) {
                    Logger.getLogger(IonicMuTesting.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            ex.shutdown();
            jTextArea1.append("Copying Done!\n");
            try {
                ex.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException ex1) {
                System.out.println(ex1.toString());
            }
            
            long start = System.currentTimeMillis();
            jTextArea1.append("Testing Enviroment..\n");
            FilesFounder ff = new FilesFounder();
            try {
                testFiles = ff.getTestFiles(path + "\\src");
                testFilesPathes = ff.getTestFilesPath();
            } catch (IOException e) {
                Logger.getLogger(IonicMuTesting.class.getName()).log(Level.SEVERE, null, e);
            }
            GenerateTestFiles gt = new GenerateTestFiles();
            gt.generateFiles(testFiles, path);
            for (int i = 0; i < testFiles.size(); i++) {
                System.out.println(testFiles.get(i).getAbsolutePath());
            }
            MainTestRunner mtr = new MainTestRunner(path, testFiles);
            mtr.beforeRunTestFileByFile(path);

            ArrayList<Results> arr2 = AnalyzeResults.getArr2();
            System.out.println(arr2.size());
            for (int i = 0; i < arr2.size(); i++) {
                System.out.println(arr2.get(i));
            }
            /////////////////////
            
            
            //ExecutorService ex0 = Executors.newFixedThreadPool(threadNumbers);
            //int noOfTestFiles = testFiles.size();
           // for (int i = 0; i < testFiles.size(); i++) {
                //ex.submit(new MainTestRunner(path, testFiles));
    
              // System.out.println("File: "+testFiles.get(i).getName()+" Will test Now");
               //     ex0.submit(new MainTestRunner(path, testFiles, testFiles.get(i), threadNumbers, i));
                    
              //  }
               // noOfTestFiles--;

//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException ex1) {
//                    Logger.getLogger(IonicMuTesting.class.getName()).log(Level.SEVERE, null, ex1);
//                }
//            }
           // ex0.shutdown();
//            try {
//                ex0.awaitTermination(1, TimeUnit.DAYS);
//            } catch (InterruptedException ex1) {
//                System.out.println(ex1.toString());
//            }
            long start2 = System.currentTimeMillis();

            ///////////////////
            ////////////////////
            //TestRunner mainTest = new TestRunner(path);
            jTextArea1.append("Enviroment is OK!!\n");

            // copying 4 project copies .Mutant0, .Mutant1, .Mutant2, .Mutant3
            
//            OriginalCopiesClass o1 = new OriginalCopiesClass(path, 0);
//            OriginalCopiesClass o2 = new OriginalCopiesClass(path, 1);
//            OriginalCopiesClass o3 = new OriginalCopiesClass(path, 2);
//            OriginalCopiesClass o4 = new OriginalCopiesClass(path, 3);
//            Thread t1 = new Thread(o1);
//            Thread t2 = new Thread(o2);
//            Thread t3 = new Thread(o3);
//            Thread t4 = new Thread(o4);
//            t1.start();
//            t2.start();
//            t3.start();
//            t4.start();
            //JOptionPane.showMessageDialog(null, "Done Copying");

//            try {
////                t1.join();
////                t2.join();
////                t3.join();
//                t4.join();
//                //jTextArea1.setText("Done copying..\n");
//                jTextArea1.append("Done Copies\n");
//            } catch (InterruptedException ex) {
//                System.out.println(ex);
//            }
            //System.out.println("Hey");
            jTextArea1.append("Generate Mutants...\n");

            //jTextArea1.setText("Generate mutants...\n");
            String path2 = path + "\\.Mutant0\\src";
            try {
                htmlFiles = ff.getHtmlFiles(path2);
                tsFiles = ff.getTsFiles(path2);
                tsFileCodes = ff.getFileCode();
                //System.out.println(tsFiles.size());
                if (booleanLiterals.isSelected()) {
                    //System.out.println("Yes");
                    selectedOperators[0] = true;
                }
                if (equalityOperator.isSelected()) {
                    selectedOperators[1] = true;
                    // System.out.println("Yes");
                }
                if (logicalOperator.isSelected()) {
                    selectedOperators[2] = true;
                    //System.out.println("Yes");
                }
                if (increments.isSelected()) {
                    selectedOperators[3] = true;
                    //System.out.println("Yes");
                }
                if (mathOperations.isSelected()) {
                    selectedOperators[4] = true;
                }
                if (relationalOperations.isSelected()) {
                    selectedOperators[5] = true;
                }
                if (ifStatementOperator.isSelected()) {
                    selectedOperators[6] = true;
                }
                if (emptyFunctionBlockOperator.isSelected()) {
                    selectedOperators[7] = true;
                }
                if (loopOperator.isSelected()) {
                    selectedOperators[8] = true;
                }
                if (statementDeletionOp.isSelected()) {
                    selectedOperators[9] = true;
                }
                System.out.println("");
                mu = new MutantsGenerator();
                for (int i = 0; i < tsFiles.size(); i++) {
                    mu.createMutantsFirstStep(path, tsFiles.get(i), selectedOperators, threadNumbers);
                    //increment.makeMutant(path, tsFiles.get(i));
                }
                pb.setMaximum(mu.getCounter());
                //System.out.println(mu.getCounter());
                jTextArea1.append(mu.getCounter() + " Mutants Genereated!!\n");
                jTextArea1.append("Mutant test will start now please rest!!\n");

                try {

                } catch (Exception e) {

                }
                ExecutorService ex2 = Executors.newFixedThreadPool(threadNumbers);
                for (int i = 0; i < threadNumbers; i++) {
                    ex2.submit(new TestRunner(path, path + "\\.Mutant", i, testFiles, testFilesPathes, threadNumbers));
                    Thread.sleep(100);
                }
                ex2.shutdown();

                try {
                    ex2.awaitTermination(1, TimeUnit.DAYS);
                } catch (InterruptedException ex1) {
                    System.out.println(ex1.toString());
                }
                pb.setValue(mu.getCounter());
                jTextArea1.append("Test Done!!! Check the report for the final result\n");
                ArrayList<Results> arr = AnalyzeResults.getArr();
                ArrayList<ResultsForEachFile> arr3 = AnalyzeResults.getArr3();
                System.out.println(arr.size());
                for (int i = 0; i < arr3.size(); i++) {
                    System.out.println(arr3.get(i));
                }

                System.out.println("--------------");
                ResultsSort rs = new ResultsSort(arr2, arr, arr3);
                ArrayList<MutationResults> mutationResults = rs.sortMutationResults();
                System.out.println("--------------");
                //
                long end = System.currentTimeMillis();
                long millis = end - start;
                long millis2 = end - start2;
                String totalTime = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                        TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
                long averageMutantTime = millis2 / mu.getCounter();
                String averageMutationTime = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(averageMutantTime),
                        TimeUnit.MILLISECONDS.toMinutes(averageMutantTime) % TimeUnit.HOURS.toMinutes(1),
                        TimeUnit.MILLISECONDS.toSeconds(averageMutantTime) % TimeUnit.MINUTES.toSeconds(1));
                Thread.sleep(500);
                GenerateReports gr = new GenerateReports(totalTime, averageMutationTime);
                gr.setFileCode(tsFileCodes);
                gr.generateHtml(path, arr2, mutationResults, arr3);
                File f = new File(path + "\\Reports\\Report.html");
                Thread.sleep(500);
                Desktop.getDesktop().browse(f.toURI());

                // for HTML generate mutatns
//            System.out.println("===============================HTML FILES===============================");
//            for (int i = 0; i < htmlFiles.size(); i++) {
//                System.out.println(htmlFiles.get(i).getAbsolutePath());
//            }
//            System.out.println("===============================TS FILES===============================");
//            for (int i = 0; i < tsFiles.size(); i++) {
//                System.out.println(tsFiles.get(i).getAbsolutePath());
//            }
//                TestRunner test1 = new TestRunner(path + "\\.Mutant", 0);
//                TestRunner test2 = new TestRunner(path + "\\.Mutant", 1);
//                TestRunner test3 = new TestRunner(path + "\\.Mutant", 2);
//                TestRunner test4 = new TestRunner(path + "\\.Mutant", 3);
//                Thread tt1 = new Thread(test1);
//                Thread tt2 = new Thread(test2);
//                Thread tt3 = new Thread(test3);
//                Thread tt4 = new Thread(test4);
//                tt1.start();
//                tt2.start();
//                tt3.start();
//                tt4.start();
//
//                tt1.join();
//                tt2.join();
//                tt3.join();
//                tt4.join();
                jTextArea1.append("Total Time: " + totalTime);
                System.out.println("Total Time: " + totalTime);
                System.out.println("Done!!!!!!!!!!!!!!!!!!!");
            } catch (IOException | InterruptedException e) {
                System.out.println("here " + e);
            }
            try {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex1) {
                    Logger.getLogger(IonicMuTesting.class.getName()).log(Level.SEVERE, null, ex1);
                }
//                FileUtils.deleteDirectory(new File(path + "\\.Mutant0"));
//                FileUtils.deleteDirectory(new File(path + "\\.Mutant1"));
//                FileUtils.deleteDirectory(new File(path + "\\.Mutant2"));
//                FileUtils.deleteDirectory(new File(path + "\\.Mutant3"));
                FileUtils.deleteDirectory(new File(path + "\\MuHubAppsTestFiles"));
            } catch (IOException ex1) {
                jTextArea1.append(ex1.getMessage());
                Logger.getLogger(IonicMuTesting.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
    }
    static int zzz = 0;

    public static int getZzz() {
        return zzz;
    }

    public synchronized static void iterate() {

        System.out.println(zzz + " zzz");

        pb.setValue(zzz);
        zzz++;

    }

    private void browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseActionPerformed
        // TODO add your handling code here:
//        JFrame frame = new JFrame();  
//        FileChoose fc = new FileChoose();
//        frame.getContentPane().add(fc, "Center");
//        frame.setSize(fc.getPreferredSize());
//        frame.setVisible(true);
//        folderPath.setText(fc.getPath());

        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setAcceptAllFileFilterUsed(false);
        String jfcPath;
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            jfcPath = jfc.getSelectedFile() + "";
            folderPath.setText(jfcPath);
            //this.dispose();
            //this.setVisible(false);
        }
        jfc.setVisible(true);

    }//GEN-LAST:event_browseActionPerformed


    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);

    }//GEN-LAST:event_exitButtonActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "This Software Made By: Shazly Ahmed\nMaster of Software Engineering\nNational Egyptian E-Learning University\nEmail:smagdy@eelu.edu.eg - shazly.eelu.eg@gmail.com");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox booleanLiterals;
    private javax.swing.JButton browse;
    private javax.swing.JCheckBox emptyFunctionBlockOperator;
    private javax.swing.JCheckBox equalityOperator;
    private javax.swing.JButton exitButton;
    private javax.swing.JTextField folderPath;
    private javax.swing.JButton generateMutants;
    private javax.swing.JCheckBox ifStatementOperator;
    private javax.swing.JCheckBox increments;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JCheckBox logicalOperator;
    private javax.swing.JCheckBox loopOperator;
    private javax.swing.JCheckBox mathOperations;
    private javax.swing.JTextField numberOfThreadsTF;
    private static javax.swing.JProgressBar pb;
    private javax.swing.JLabel recomendedThreads;
    private javax.swing.JCheckBox relationalOperations;
    private javax.swing.JCheckBox statementDeletionOp;
    // End of variables declaration//GEN-END:variables
}
