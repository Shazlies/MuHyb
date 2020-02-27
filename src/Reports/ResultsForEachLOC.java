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
public class ResultsForEachLOC {
    String fileName;
    int killed;
    int survived;
    int exceptions;
    int syntaxErrors;
    int lineNumber;
    
    public ResultsForEachLOC(String fileName, int lineNumber){
        this.fileName = fileName;
        this.lineNumber = lineNumber;
    }

    public int getKilled() {
        return killed;
    }

    public int getSurvived() {
        return survived;
    }

    public int getExceptions() {
        return exceptions;
    }

    public int getSyntaxErrors() {
        return syntaxErrors;
    }

    public int getLineNumber() {
        return lineNumber;
    }
    
    public void increaseKilled(){
        killed++;
    }
    public void increaseSurvived(){
        survived++;
    }
    public void increaseExeptions(){
        exceptions++;
    }
    public void increaseSyntaxErrors(){
        syntaxErrors++;
    }
    
}
