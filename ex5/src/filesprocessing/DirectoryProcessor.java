package filesprocessing;
/** Utility packages */
// TODO: clean redundant importations..
import java.util.*;
import java.util.function.*;
import java.io.*;
import java.lang.*;

public class DirectoryProcessor {
	/**
	 * The main driver method running the files management script.
	 * @param args - SourceDirectory and then FileCommandsPath (separated by a space).
     */
	public static void main(String[] args){
		Parser parser = new Parser(args[0], args[1]);
		try{
			ArrayList<Section> sections = parser.parseCommands();
			ArrayList<File> files = parser.getFiles();
			for (Section section : sections){
				ArrayList<File> toPrint;
				toPrint = selectRelevant(files, section.getFilter());
				Collections.sort(toPrint, section.getOrder());
				if (section.getOrder().checkReverseFlag()){
					Collections.reverse(toPrint);
				}
				if (section.errorCheck()){
					section.printErrorMessages();
				}
				for (File file : toPrint) {
					System.out.println(file.getName());
				}
			}

		} catch (java.io.FileNotFoundException e){
			String errorMsgCommandsFileLocation = "The path for the command file directory have" +
					" been inappropriately supplied";
			System.err.println(errorMsgCommandsFileLocation);
		}
	}

	/**
	 * A selection method give the relevant files and the appropriate filter.
	 * @param files - target files to be filtered.
	 * @param predicate - the specific predicate we use to filter the files.
     * @return - the relevant files who passed the predicate's test.
     */
	public static ArrayList<File> selectRelevant(List<File> files, Predicate<File> predicate){
		ArrayList<File> res = new ArrayList<>();
		for(File file : files){
			if (predicate.test(file)){
				res.add(file);
			}
		}
		return res;
	}
}
