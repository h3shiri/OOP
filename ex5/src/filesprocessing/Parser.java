package filesprocessing;
import java.io.*;
import java.util.*;
import orders.*;
import filters.*;
import errors.*;

public class Parser{
	/** all the relevant files under the source directory */
	private ArrayList<File> files;
	/** same files but in String formats for the different usage */
	private ArrayList<String> fileNames;
	/** commands file */
	private File commands;

	/** magic strings that act as delimeters for the filters/orders */
	private final String FILTERMARKER = "FILTER";
	private final String ORDERMARKER = "ORDER";
	/** magic string that acts as the filter delimeter between argumets */
	private final String FILTERDELIM = "#";

	/**
	 * The constructor of the parser class.
	 * @param sourceDirectory - the source directory location of all the files.
	 * @param sourceCommands - the location of the commands file.
     */
	public Parser(final String sourceDirectory, final String sourceCommands){
		files = new ArrayList<File>();
		fileNames = new ArrayList<String>();
		File source = new File(sourceDirectory);
		File commandsFile = new File(sourceCommands);
		File[] tempFiles = source.listFiles();
		for (File file : tempFiles) {
			if(file.isFile()){
				files.add(file);
				fileNames.add(file.getName());
			}
		}
		if(commandsFile.isFile()){
			commands = commandsFile;
		}
	}

	public ArrayList<Section> parseCommands() throws FileNotFoundException {
		// TODO: check whether the factory lines should be in try block.
		Scanner scanner = new Scanner(new FileReader(commands));
		ArrayList<Section> res = new ArrayList<>();
		String potentialFilterError = "EMPTY";
		boolean filterError = false;
		String potentialOrderError = "EMPTY";
		boolean orderError = false;
		/** boolean trackers of the initiation segment parts */
		boolean filterTest = false;
		boolean orderTest = false;
		Filter currentFilter = new AiFilter();
		Order currentOrder = new TypeOrder();
		/** Starts tracing for type I errors */
		int lineNumber = 0;
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			lineNumber++;
			if (line.contains(FILTERMARKER)){
				String filterData = scanner.nextLine();
				lineNumber++;
				try {
					Filter filter = FilterFactory.build(filterData, lineNumber);
					currentFilter = filter;
					filterTest = true;
				} catch (TypeOneError e) {
					currentFilter = new AiFilter();
					filterTest = true;
					filterError = true;
					potentialFilterError = e.getMessage();
				}
			}
			else if (line.contains(ORDERMARKER)) {
				String orderData = scanner.nextLine();
				lineNumber++;
				try {
					Order order = OrderFactory.build(orderData, lineNumber);
					currentOrder = order;
					orderTest = true;
				} catch (TypeOneError e) {
					currentOrder = new AbsOrder();
					orderTest = true;
					orderError = true;
					potentialOrderError = e.getMessage();
				}
			}
			if (orderTest && filterTest) {
				Section tempSection = new Section(currentFilter, currentOrder);
				if (filterError){
					tempSection.setError();
					tempSection.addError(potentialFilterError);
				}
				if (orderError){
					tempSection.setError();
					tempSection.addError(potentialOrderError);
				}
				res.add(tempSection);
				orderTest = false;
				filterTest = false;
				filterError = false;
				orderError = false;
			}
		}
		return res;
	}

	public ArrayList<String> getNames(){
		return fileNames;
	}

	public ArrayList<File> getFiles(){
		return files;
	}
}