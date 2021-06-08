/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingFiles;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Home
 */
public class OriginalCopiesClass implements Runnable {

    //Thread t;
    String appDirectoryRoot;
    int count;

    public OriginalCopiesClass(String appDirectoryRoot, int count) {
        this.appDirectoryRoot = appDirectoryRoot;
        this.count = count;
//        System.out.println(this.appDirectoryRoot);
//        System.out.println(this.count);
//        t = new Thread(this, "here");
//        t.setPriority(Thread.MAX_PRIORITY);
//        
//        t.start();
    }

    public void run() {
//        System.out.println(this.appDirectoryRoot);
//        System.out.println(this.count);
        takeOrginalCopy(this.appDirectoryRoot, this.count);
    }

    public void takeOrginalCopy(String appDirectoryRoot, int count) {
        String OrignalCopy = appDirectoryRoot + "\\_Mutant" + count;
        File f1 = new File(appDirectoryRoot);
        File f2 = new File(OrignalCopy);
//        System.out.println(f1.getAbsolutePath());
//        System.out.println(f2.getAbsolutePath());
        try {
            //FileUtils.copyDirectory(f1, f2);
            FileUtils.copyDirectory(f1, f2, new FileFilter() {
                public boolean accept(File pathName) {
                    String n = pathName.getName();

                    if ((n.equals("_Mutant") || n.contains("_Mutant") || n.equals("node_modules") || n.equals("MuHubAppsTestFiles") || n.contains("Report") || n.equals("www")) && pathName.isDirectory()) {
                        return false;
                    }
                    //System.out.println(n);
                    //if
                    return true;

                }
            }, true);
        } catch (IOException ex) {
            System.out.println("Orignal Copy Failed");
        }
        System.out.println("Done Copy No. " + (count + 1) + "....");
        //copyNode_modules(appDirectoryRoot, count);
    }

    public void copyNode_modules(String appDirectoryRoot, int count) {
        File f1 = new File(appDirectoryRoot + "\\node_modules\\protractor");
        String OrignalCopy = appDirectoryRoot + "\\_Mutant" + count+"\\node_modules\\protractor";
        File f2 = new File(OrignalCopy);
        try {
            FileUtils.copyDirectory(f1, f2);
        } catch (IOException ex) {
            System.out.println("Orignal Copy Failed");
        }
    }
}
