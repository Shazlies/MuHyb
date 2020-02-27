/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class FileResults {

    private String fileCode;
    private String fileName;
    private ArrayList<ResultsForEachFile> arr = new ArrayList<ResultsForEachFile>();
    public FileResults(String fileCode, String fileName, ArrayList<ResultsForEachFile> arr) {
        this.fileCode = fileCode;
        this.fileName = fileName;
        this.arr = arr;
    }

    /**
     * @return the fileCode
     */
    public String getFileCode() {
        return fileCode;
    }

    /**
     * @param fileCode the fileCode to set
     */
    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
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
     * @return the arr
     */
    public ArrayList<ResultsForEachFile> getArr() {
        return arr;
    }

    /**
     * @param arr the arr to set
     */
    public void setArr(ArrayList<ResultsForEachFile> arr) {
        this.arr = arr;
    }

    

}
