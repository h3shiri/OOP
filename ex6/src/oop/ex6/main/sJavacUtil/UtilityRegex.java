package oop.ex6.main.sJavacUtil;
import oop.ex6.main.line.IllegalMethodCallException;

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
	public final static String commentLine = "//.*";
	public final static String typesRegex = "(int |double |boolean |String |char )";
	public final static String spaces = "(\\s*)";
	public final static String variableName = "([a-zA-Z]+[\\w]*|_+[a-zA-Z]+\\w*)";
	public final static String substitution = "( = [^\\s]+)";
	public final static String variablesDelim = ",";
	public final static String methodName = "[a-zA-Z]+[\\w]*";
	public final static String methodParametersForSimpleCall = "[(]([a-zA-Z]+[\\w,\\s]*|_+[a-zA-Z]+[\\w,\\s]*)[)]";
	public final static String methodCall = methodName+"\\s?"+methodParametersForSimpleCall;
	public final static String variableRegex =
			"^(\\s*)(int |double |boolean |String |char )?([a-zA-Z]+[\\w]*|_+[a-zA-Z]+\\w*)( = [^\\s]+)?(\\s*|,)$";
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

	/**
	 * Get the name and parameters out of a method call line.
	 * @param line
     */
	//TODO: test this thing. It may not work if there are spaces in a param call, e.g foo(3,    4);
	public static ArrayList<String> parseMethodCall(String line){
		ArrayList<String> rawMethodCallData = new ArrayList<>();
		Pattern pat = Pattern.compile(methodCall);
		Matcher mat = pat.matcher(line);
		String variables = null;
		while(mat.find()){
			String methodName = mat.group(1);
			rawMethodCallData.add(methodName); //first in raw will be the name, the rest are params
			variables = mat.group(2);
		}
		while(variables.length() != 0){ //now we get the params
			String next = variables.substring(variables.lastIndexOf(",") +1);
			rawMethodCallData.add(next);
			variables = variables.substring(0,variables.lastIndexOf(","));
		}
		return rawMethodCallData;
	}
	/**
	 * Check whether a line is an empty spaces line
	 * @param lineInput
	 * @return
     */
	public static boolean checkLineIsEmpty(String lineInput){
		Pattern tempVar = Pattern.compile(emptyLine);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}

	/**
	 * Check whether the line is a comment line
	 * @param lineInput
	 * @return
     */
	public static boolean checkLineIsComment(String lineInput){
		Pattern tempVar = Pattern.compile(commentLine);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}

	/**
	 * This method check whether a line is a method call.
	 * @param lineInput
	 * @return
     */
	public static boolean checkLineIsMethodCall(String lineInput){
		Pattern tempVar = Pattern.compile(methodCall);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}
	public static boolean checkLineIsVariableDeclaration(String lineInput) {
		Pattern tempVar = Pattern.compile(variableRegex);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}
}