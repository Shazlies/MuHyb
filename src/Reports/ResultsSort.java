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
public class ResultsSort {

    ArrayList<Results> mainResults = new ArrayList<Results>();
    ArrayList<Results> mutationResults = new ArrayList<Results>();
    ArrayList<ResultsForEachFile> resultsForEachFile = new ArrayList<ResultsForEachFile>();

    public ResultsSort(ArrayList<Results> mainResults, ArrayList<Results> mutationResults, ArrayList<ResultsForEachFile> resultsForEachFile) {
        this.mainResults = mainResults;
        this.mutationResults = mutationResults;
        this.resultsForEachFile = resultsForEachFile;
        System.out.println("MainResultsSize: " + mainResults.size() + " MutationResultsSize: " + mutationResults.size());
    }

    public ArrayList<MutationResults> sortMutationResults() {
        System.out.println();
        ArrayList<MutationResults> mutRes = new ArrayList<MutationResults>();

        for (int i = 0; i < mutationResults.size(); i++) {
            
            Results s;
            s = mutationResults.get(i);
            System.out.println(s+" nnnnnnnnnnnnnnnnnnnnnnn");
            //System.out.println("Test this result: "+s);
            int index = getMainResult(mainResults, s);
            System.out.println(index);
            if (index != -1) {

                int ind = getMutationResult(mutRes, mainResults.get(index));
                // System.out.println("ind: "+ind);
                if (ind == -1) {
                    // add new mutationResults object

                    MutationResults mr = new MutationResults();
                    mr.setFileName(mainResults.get(index).getFileName());
                    // check if killed or not
                    if (mutationResults.get(i).getTestCase().equalsIgnoreCase("FAILED")) {
                        ///resultsForEachFile

                        mr.increaseKilled();
                    }
                    if (mutationResults.get(i).getTestCase().equalsIgnoreCase("SUCCESS")) {

                        mr.increaseSurvived();
                    }
                    if (mutationResults.get(i).getTestCase().equalsIgnoreCase("DISCONNECTED")) {

                        mr.increaseExceptions();
                    }
                    if (mutationResults.get(i).getTestCase().equalsIgnoreCase("ERROR")) {

                        mr.increaseSyntaxErrors();
                    }
                    mutRes.add(mr);

                } else {
                    if (mutationResults.get(i).getTestCase().equalsIgnoreCase("FAILED")) {

                        mutRes.get(ind).increaseKilled();
                    }
                    if (mutationResults.get(i).getTestCase().equalsIgnoreCase("SUCCESS")) {
                        mutRes.get(ind).increaseSurvived();

                    }
                    if (mutationResults.get(i).getTestCase().equalsIgnoreCase("DISCONNECTED")) {
                        mutRes.get(ind).increaseExceptions();
                    }
                    if (mutationResults.get(i).getTestCase().equalsIgnoreCase("ERROR")) {
                        mutRes.get(ind).increaseSyntaxErrors();
                    }
                    

                }
            }
        }
        System.out.println("*********************");
        for (int i = 0; i < mutRes.size(); i++) {
            System.out.println("Filename: " + mutRes.get(i).getFileName() + " killed: " + mutRes.get(i).getKillMutant() + " Survived: " + mutRes.get(i).getSurvivedMutants() + " Diconnected: " + mutRes.get(i).getExceptions()+ " Sytax Errors: "+mutRes.get(i).getSyntaxErrors());
        }
        System.out.println("*****************");
        return mutRes;
    }

    public int getMainResult(ArrayList<Results> mainResults, Results rs) {

        for (int i = 0; i < mainResults.size(); i++) {
            //System.out.println("fromgetMainResults "+mainResults.get(i).getFileName()+"   "+rs.getFileName());
            if (mainResults.get(i).getFileName().equals(rs.getFileName())) {
                //  System.out.println("return: "+i);
                return i;
            }
        }
        return -1;
    }

    public int getMutationResult(ArrayList<MutationResults> mutationResults, Results rs) {

        for (int i = 0; i < mutationResults.size(); i++) {
            System.out.println("fromgetMutationResults " + mutationResults.get(i).getFileName() + "  " + rs.getFileName());
            if (mutationResults.get(i).getFileName().equals(rs.getFileName())) {
                return i;
            }
        }
        return -1;
    }

//    public void sortResults() {
//        ArrayList<Results> output = new ArrayList<Results>();
//        ArrayList<String> fileName = new ArrayList<String>();v
//        if (!arr.isEmpty()) {
//            fileName.add(arr.get(0).getFileName());
//            output.add(arr.get(0));
//
//            for (int i = 1; i < arr.size(); i++) {
//                if (fileName.contains(arr.get(i).getFileName())) {
//                    int index = indexOfFileName(fileName, arr.get(i).getFileName());
//                    Results rs = new Results(fileName.get(index), "",output.get(index).getExecuted()+arr.get(i).getExecuted(), output.get(index).getOutOf()+arr.get(i).getOutOf(),output.get(index).getNoOfFailed()+arr.get(i).getNoOfFailed());
//                    output.remove(index);
//                    output.add(index, rs);
//                } else {
//                    fileName.add(arr.get(i).getFileName());
//                    output.add(arr.get(i));
//                }
//            }
//        }
//        for (int i = 0; i < output.size(); i++) {
//            System.out.println(output.get(i));
//        }
//
//    }
//    public int indexOfFileName(ArrayList<String> arr, String fileName) {
//
//        for (int i = 0; i < arr.size(); i++) {
//            if (arr.get(i).equals(fileName)) {
//                return i;
//            }
//        }
//        return -1;
//    }
}
