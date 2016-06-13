package oop.ex6.main;
import oop.ex6.main.line.*;
import oop.ex6.main.sJavacUtil.*;

import java.io.*;
import java.util.ArrayList;
import java.io.File;

//TODO: remember to clean up later.
public class Sjavac {
    /**
     * This method gets a File and returns an array of Strings for each line
     * @param fileToRead - the file we are reading from, (relative/absolute path)
     * @return - A new String array that contains all the code lines.
     * @throws IOException - In case of a non valid path to file.
     * @throws IllegalLineFormatException - in case of a non valid line format.
     */
    private static ArrayList<String> fileToArray(File fileToRead) throws IOException,IllegalLineFormatException{
        try(BufferedReader buffer = new BufferedReader(new FileReader(fileToRead))){
            ArrayList<String> commandFileList = new ArrayList<String>();
            String line;
            while((line = buffer.readLine()) != null){
                commandFileList.add(line);
            }
            return commandFileList;
        }catch(IOException e){
            throw new IOException();
        }
    }

    /**
     * Main method
     * @param args - arguments for the main function.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException,IllegalLineFormatException {
        if (args.length != 1){
            System.err.print("Illegal arguments given (only 1 argument is allowed!)");
            System.out.print("2");
            return;
        }
        else {
            String file = args[0];
            try {
                ArrayList<String> fileInArray = fileToArray(new File(file));
                if (fileInArray == null) {
                    System.err.print("The file is empty, terminating");
                    System.out.println("2");
                    return;
                }
                /* main processing occurring in the various processors */
                try {
                    ArrayList<String> codeData = (FirstOrderProcessor.process(fileInArray));
                    ArrayList<SjavacLine> classifiedLines = (SecondOrderProcessor.process(codeData));
                    ArrayList<SjavacLine> processedLines = (ThirdOrderProcessor.process(classifiedLines));
                    LinkComplexNode genesisNode = new LinkComplexNode(processedLines.size());
                    ScopeBuilder flowTree = new ScopeBuilder(genesisNode, processedLines);
                    System.out.println(0);
                    return;
                } catch (SjavaFormatException e) {
                    System.out.println("1");
                }
            } catch (IOException e) {
                //TODO: remove should only print out 0,1 or 2.
                System.err.print("There was an I/O problem, terminating.\n");
                System.out.println("2");
                return;
            }
        }
    }

}
