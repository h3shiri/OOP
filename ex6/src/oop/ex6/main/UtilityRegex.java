package oop.ex6.main;
import java.util.regex.*;
import java.util.ArrayList;

// TODO:clean all the redundent comments in the far future. 
/** This class contains useful regular expressions */

public class UtilityRegex{
	public final static String[] types = {"int","double","boolean", "string", "char"};
	/* Note the groups structure spaces (parsing dependent), type, name, value(optional)
	 * additional legality testing shall be done
	 * Assuming cut of trailling ; in order to support multi-value declaration in the future.
	 */
	public final static String emptyLine = "^(\\s*)$";

	public final static String typesRegex = "(int |double |boolean |String |char )";
	public final static String spaces = "(\\s*)";
	public final static String variableName = "([a-zA-Z]+[\\w_]*|_+[a-zA-Z]+\\w*)";
	public final static String substitution = "( = [^\\s]+)";
	public final static String variablesDelim = ",";
	// Nuclear 
	public final static String variableRegex = "^(\\s*)(int |double |boolean |String |char )?([a-zA-Z]+[\\w_]*|_+[a-zA-Z]+\\w*)( = [^\\s]+)?(\\s*|,)$";
	// $ sensitivity problem.. line ending (test).
	// group indexing starts from zero or one.

	// TODO: In progress finish..
	public static void parseSimpleVariableLine(String line){
		ArrayList<String> rawVariablesData = new ArrayList<>();
		Pattern var = Pattern.compile(variableRegex);
		Matcher mat = var.matcher(line);
		while(mat.find()){
			// Shit load of testing types destribution previously_casted/ down casting.. etc
			String type = mat.group(1);

			// declaration dependent group 1 or 2.
			String name =  mat.group(2);

			// Similarly only optional.
			String value = mat.group(3);
		}
	}

	public static boolean checkLineIsEmpty(String lineInput){
		Pattern tempVar = Pattern.compile(emptyLine);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}
}