package oop.ex6.main.sJavacUtil;
import oop.ex6.main.line.IllegalMethodCallException;

import javax.naming.PartialResultException;
import java.util.regex.*;
import java.util.ArrayList;

// TODO:clean all the redundent comments in the far future. 
/** This class contains useful regular expressions */

public class UtilityRegex{
	public final static String[] types = {"int","double","boolean", "string", "char"};
	/* A useful list of regexes we use in our verifier */
	public final static String emptyLine = "^(\\s*)$";
	public final static String commentLine = "//.*";
	public final static String number = "^\\s*(\\d+|\\d+.\\d+)\\s*$";
	public final static String typesRegex = "(int |double |boolean |String |char )";
	public final static String spaces = "\\s*";
	public final static String variableName = "([a-zA-Z]+[\\w]*|_+[a-zA-Z]+\\w*)";
	public final static String substitution = "(?:\\s*=\\s*([^\\s])+)";
	public final static String variablesDelim = ",";
	public final static String methodName = "[a-zA-Z]+[\\w]*";
	//TODO: debug this isn't actually tight multi parameters have to comply with the naming format.
	public final static String methodParametersForSimpleCall = "[(]\\s*([a-zA-Z]+[\\w,\\s]*|_+[a-zA-Z]+[\\w,\\s]*)?[)]";
	public final static String methodCall = "^"+spaces+methodName+spaces+methodParametersForSimpleCall+spaces+"[;]"+spaces+"$";
	public final static String variableRegex =
			"^\\s*(final)?\\s*(int|double|boolean|String|char)\\s+((([a-zA-Z]+\\w*|_+[a-zA-Z]+\\w*)+)(?:\\s*=\\s*([^\\s])+)?\\s*,?\\s*)+;\\s*$";
	public final static String variableSubstitutionRegex = "^"+spaces+variableName+spaces+"="+spaces+"[^\\s]*"+spaces+";"+spaces+"$";
	public final static String returnLine = "^\\s*(return;)\\s*";
	public final static String blockCloser = "^\\s*}\\s*$";
	//TODO: perhaps write with normal substitution aka methodName, variable name..etc
	public final static String methodOpenner =
	"^\\s*void\\s+([a-zA-Z]+[\\w]*)[(](\\s*(final\\s*)?(int|char|String|double|boolean)\\s*([a-zA-Z]+[\\w]*|_+[a-zA-Z]+\\w*),?)*[)]\\s*\\{\\s*$";
	public final static String literal = "(true|false|\\d+|\\d+.\\d+|[a-zA-Z]+[\\w]*|_+[a-zA-Z]+\\w*)";
	public final static String conditionalExpression =
	"^\\s*(if|while)\\s*\\("+literal+"(\\s*\\&\\&\\s*"+literal+"|\\s*\\|\\|\\s*"+ literal+")*\\s*\\)\\s*\\{\\s*$";

//	Original_expression =
//			^\s*(if|while)\s*\(\s*(true|false|\d+|\d+.\d+|[a-zA-Z]+[\w]*|_+[a-zA-Z]+\w*)(\s*\&\&\s*(true|false|\d+|\d+.\d+|[a-zA-Z]+[\w]*|_+[a-zA-Z]+\w*)|\s*\|\|\s*(true|false|\d+|\d+.\d+|[a-zA-Z]+[\w]*|_+[a-zA-Z]+\w*))*\s*\)\s*\{\s*$

	/**
	 * Get the name and parameters out of a method call line.
	 * @param line - the line input for the method call parsing.
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
		while(variables.length() > 0){ //now we get the params
			String next = variables.substring(variables.lastIndexOf(",") +1);
			rawMethodCallData.add(next);
			variables = variables.substring(0,variables.lastIndexOf(","));
		}
		return rawMethodCallData;
	}
	/**
	 * Check whether a line is an empty spaces line
	 * @param lineInput - the given line input.
	 * @return - true iff the line is empty.
     */
	public static boolean checkLineIsEmpty(String lineInput){
		Pattern tempVar = Pattern.compile(emptyLine);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}

	/**
	 * Check whether the line is a comment line
	 * @param lineInput - the given line input.
	 * @return - true iff the line is a comment.
     */
	public static boolean checkLineIsComment(String lineInput){
		Pattern tempVar = Pattern.compile(commentLine);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}

	/**
	 * This method check whether a line is a method call.
	 * @param lineInput - A given line input.
	 * @return - true iff the line is a method call.
     */
	public static boolean checkLineIsMethodCall(String lineInput){
		Pattern tempVar = Pattern.compile(methodCall);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}

	/**
	 * A tester function for valid variable substitution.
	 * @param lineInput - the relevant line
	 * @return true iff it contains legal syntax.
     */
	public static boolean checkLineIsVariableSubstitution(String lineInput){
		Pattern tempVar = Pattern.compile(variableSubstitutionRegex);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}

	/**
	 * A tester function for the deceleration of new variables.
	 * @param lineInput - the relevant line.
	 * @return - true iff the line is a legal variable deceleration.
     */
	public static boolean checkLineIsVariableDeclaration(String lineInput) {
		Pattern tempVar = Pattern.compile(variableRegex);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}

	/**
	 * A tester function for a simple return line.
	 * @param lineInput - the relevant line input.
     * @return - true iff the line is return line with the right format.
     */
	public static boolean checkLineIsFunctionReturn(String lineInput){
		Pattern tempVar = Pattern.compile(returnLine);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}

	/**
	 * A tester function for the simple closing block.
	 * @param lineInput - the relevant line input.
	 * @return true iff the line is a closer (closing a block).
     */
	public static boolean checkLineIsBlockCloser(String lineInput){
		Pattern tempVar = Pattern.compile(blockCloser);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}

	/**
	 * A tester function for a complex line checking a clear syntax for method deceleration.
	 * @param lineInput - the relevant line input.
	 * @return - true idd the line syntax is correct.
     */
	public static boolean checkLineIsMethodOpenner(String lineInput){
		Pattern tempVar = Pattern.compile(methodOpenner);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}

	/**
	 * A tester function for a complex line regarding a conditional expression.
	 * @param lineInput - the relevant line input.
	 * @return - true iff the line is a legal conditional expression opening.
     */
	public static boolean checkLineIsConditional(String lineInput){
		Pattern tempVar = Pattern.compile(conditionalExpression);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}

	/**
	 * A tester function for a String argument checking whether it is a variable.
	 * @param lineInput - the actual argument.
	 * @return true - iff it has an actual variable name.
     */
	public static boolean checkArgumentIsVariable(String lineInput){
		Pattern tempVar = Pattern.compile(variableName);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}

	/**
	 * A tester function checking a value is an int or a double.
	 * @param lineInput - the actual argument.
     * @return true iff the argument is a number.
     */
	public static boolean checkArgumentIsANumber(String lineInput){
		Pattern tempVar = Pattern.compile(number);
		Matcher tempMat = tempVar.matcher(lineInput);
		return tempMat.matches();
	}
}