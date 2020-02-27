/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

/**
 *
 * @author Home
 */
public class Results {
    String fileName;
    private String testCase;
    private int executed;
    private int outOf;
    private int noOfFailed;
    private int lineNumber;
    private String line;
    public String getFileName(){
        return fileName;
    }
    public Results(){
        
    }
    public Results(String fileName, String testCase, int executed, int outOf, int noOfFailed) {
        this.fileName= fileName;
        this.testCase = testCase;
        this.executed = executed;
        this.outOf = outOf;
        if (testCase.equalsIgnoreCase("failed")) {
            this.noOfFailed = noOfFailed;
        }
        //System.out.println("FileName: "+fileName+" "+testCase+" executed " + executed + " of " + outOf + " Number of failed  " + noOfFailed);
    }

    public String toString(){
        return "FileName: "+fileName+" "+getTestCase()+" executed " + getExecuted() + " of " + getOutOf() + " Number of failed  " + getNoOfFailed();
    }

    /**
     * @return the testCase
     */
    public String getTestCase() {
        return testCase;
    }

    /**
     * @return the executed
     */
    public int getExecuted() {
        return executed;
    }

    /**
     * @return the outOf
     */
    public int getOutOf() {
        return outOf;
    }

    /**
     * @return the noOfFailed
     */
    public int getNoOfFailed() {
        return noOfFailed;
    }

    /**
     * @return the lineNumber
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * @return the line
     */
    public String getLine() {
        return line;
    }
}
