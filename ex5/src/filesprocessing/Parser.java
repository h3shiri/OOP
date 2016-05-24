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

	public ArrayList<Section> parseCommands() throws FileNotFoundException, TypeTwoError{
		int numOfFilterHeadings = 0;
		int numOfOrderHeadings = 0;
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
		/** boolean edge case for conjunction of ORDER then FILTER */
		boolean edgeCase = false;
		/** A string holder for the current line */
		String line;
		while(scanner.hasNextLine()){
			if(edgeCase){
				line = FILTERMARKER;
				edgeCase = false;
			}
			else{
				line = scanner.nextLine();
				lineNumber++;
			}
			if (line.contains(FILTERMARKER)){
				String filterData = scanner.nextLine();
				lineNumber++;
				try {
					Filter filter = FilterFactory.build(filterData, lineNumber);
					currentFilter = filter;
					filterTest = true;
					numOfFilterHeadings++;
					continue;
				} catch (TypeOneError e) {
					currentFilter = new AiFilter();
					filterTest = true;
					filterError = true;
					potentialFilterError = e.getMessage();
					numOfFilterHeadings++;
					continue;
				}
			}
			else if (line.contains(ORDERMARKER)) {
				try{
					/** In case of finishing with empty line */
					if (!scanner.hasNext()){
						orderTest = true;
						currentOrder = new AbsOrder();
						numOfOrderHeadings++;
					}
					else if (scanner.hasNext()) {
						String orderData = scanner.nextLine();
						lineNumber++;
						/** Testing the sub field has some data */
						if (orderData.equals(FILTERMARKER)) {
							currentOrder = new AbsOrder();
							orderTest = true;
							numOfOrderHeadings++;
							edgeCase = true;
						} else {
							Order order = OrderFactory.build(orderData, lineNumber);
							currentOrder = order;
							orderTest = true;
							numOfOrderHeadings++;
						}
					}
				} catch (TypeOneError e) {
					currentOrder = new AbsOrder();
					orderTest = true;
					orderError = true;
					potentialOrderError = e.getMessage();
					numOfOrderHeadings++;
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
			/** In case of a non valid heading */
			else if((!line.contains(FILTERMARKER))&&(!line.contains(ORDERMARKER))){
				/** In case of a non-valid heading */
				throw new BadSubSectionName();
			}
			/** In case of non matching number of Filter headings and Order Headings after parsing the file. */
			if ((!scanner.hasNext()) && (numOfFilterHeadings != numOfOrderHeadings)){
				throw new MisMatchingNumberOfHeadings();
			}
		}
		return res;
	}

	/**
	 * A getter function for the names of the files
	 * @return - An array list of the files containing their names.
     */
	public ArrayList<String> getNames(){
		return fileNames;
	}

	/**
	 * A getter function for all the files.
	 * @return - all the files in the given target directory.
     */
	public ArrayList<File> getFiles(){
		return files;
	}
}