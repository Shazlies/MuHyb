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
public class ResultsForEachFile {
    private String state;
    private int lineCode;
    private String operator;
    private String originalLine;
    private String mutationLine;
    private String fileName;
    
    public ResultsForEachFile(String fileName, int lineCode, String state, String operator, String originalLine, String mutationLine) {
        this.state = state;
        this.operator = operator;
        this.originalLine = originalLine;
        this.mutationLine = mutationLine;
        this.lineCode = lineCode;
        this.fileName=fileName;
    }

    public String getFileName() {
        return fileName;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the lineCode
     */
    public int getLineCode() {
        return lineCode;
    }

    /**
     * @param lineCode the lineCode to set
     */
    public void setLineCode(int lineCode) {
        this.lineCode = lineCode;
    }

    /**
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator the operator to set
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * @return the originalLine
     */
    public String getOriginalLine() {
        return originalLine;
    }

    /**
     * @param originalLine the originalLine to set
     */
    public void setOriginalLine(String originalLine) {
        this.originalLine = originalLine;
    }

    /**
     * @return the mutationLine
     */
    public String getMutationLine() {
        return mutationLine;
    }

    /**
     * @param mutationLine the mutationLine to set
     */
    public void setMutationLine(String mutationLine) {
        this.mutationLine = mutationLine;
    }
    
    public String toString(){
         return " State "+ state+" lineCode "+ lineCode+" operator "+ operator+" originalline "+ originalLine+" mutationline "+ mutationLine;
    }
    
}
