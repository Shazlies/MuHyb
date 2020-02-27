/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingFiles;

/**
 *
 * @author Home
 */
public class MutantInformation {

    private int lineCode;
    private String Operator;
    private String originalLine;
    private String mutantLine;
    
    public MutantInformation(int lineCode, String operator, String originalLine, String mutantLine){
        this.lineCode = lineCode;
        this.Operator = operator;
        this.originalLine = originalLine;
        this.mutantLine = mutantLine;

    }

    @Override
    public String toString() {
        return "MutantInformation{" + "lineCode=" + lineCode + ", Operator=" + Operator + ", originalLine=" + originalLine + ", mutantLine=" + mutantLine + '}';
    }

    public int getLineCode() {
        return lineCode;
    }

    public String getOperator() {
        return Operator;
    }

    public String getOriginalLine() {
        return originalLine;
    }

    public String getMutantLine() {
        return mutantLine;
    }


}
