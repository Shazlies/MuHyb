/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilesFounder;

/**
 *
 * @author Home
 */
public class FileAsString {
    private String fileName;
    private String fileCode;
    
    public FileAsString (String fileCode, String fileName){
        this.fileCode = fileCode;
        this.fileName = fileName;
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
}
