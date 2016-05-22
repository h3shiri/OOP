package filesprocessing;
import java.io.*;
import java.util.*;
import orders.*;
import filters.*;

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
		ArrayList<Section> res = new ArrayList<Section>();
		boolean filterTest = false;
		boolean orderTest = false;
		Filter currentFilter = new AlFilter();
		Order currentOrder = new TypeOrder();
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			if (line.contains(FILTERMARKER)){
				String filterData = scanner.nextLine();
				Filter filter = FilterFactory.build(filterData);
				currentFilter = filter;
				filterTest = true;
			}
			else if (line.contains(ORDERMARKER)) {
				String orderData = scanner.nextLine();
				Order order = OrderFactory.build(orderData);
				currentOrder = order;
				orderTest = true;
			}
			if (orderTest && filterTest) {
				res.add(new Section(currentFilter, currentOrder));
				orderTest = false;
				filterTest = false;
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