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
public class MutationResults {
    private String fileName;
    private int killMutant;
    private int survivedMutants;
    private int exceptions;
    private int totalMutants;
    private int syntaxErrors;

    
    public void increaseSurvived(){
     survivedMutants++;   
    }
    public void increaseKilled(){
     killMutant++;   
    }
    public void increaseExceptions(){
     exceptions++;   
    }
    public void increaseSyntaxErrors(){
     syntaxErrors++;   
    }

    public int getSyntaxErrors() {
        return syntaxErrors;
    }

    public void setSyntaxErrors(int syntaxErrors) {
        this.syntaxErrors = syntaxErrors;
    }
    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the killMutant
     */
    public int getKillMutant() {
        return killMutant;
    }

    /**
     * @param killMutant the killMutant to set
     */
    public void setKillMutant(int killMutant) {
        this.killMutant = killMutant;
    }

    /**
     * @return the survivedMutants
     */
    public int getSurvivedMutants() {
        return survivedMutants;
    }

    /**
     * @param survivedMutants the survivedMutants to set
     */
    public void setSurvivedMutants(int survivedMutants) {
        this.survivedMutants = survivedMutants;
    }

    /**
     * @return the exceptions
     */
    public int getExceptions() {
        return exceptions;
    }

    /**
     * @param exceptions the exceptions to set
     */
    public void setExceptions(int exceptions) {
        this.exceptions = exceptions;
    }

    /**
     * @return the totalMutants
     */
    public int getTotalMutants() {
        return totalMutants;
    }

    /**
     * @param totalMutants the totalMutants to set
     */
    public void setTotalMutants(int totalMutants) {
        this.totalMutants = totalMutants;
    }
    
    
    
    
}
