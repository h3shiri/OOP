package oop.ex6.main.sJavacUtil;
import oop.ex6.main.line.*;
import java.util.ArrayList;
import java.util.regex.*;

/** classifying the lines into specific simple lines and openers closers */

public class ThirdOrderProcessor {

	public static ArrayList<SjavacLine> process(ArrayList<SjavacLine> linesOfCode) throws SjavaFormatException {
		String COMPLEXLINE = "Complex";
		String SIMPLELINE = "Simple";
		ArrayList<SjavacLine> res = new ArrayList<>();
		for (int i = 0; i < linesOfCode.size(); i++) {
			int lineNumber = i;
			SjavacLine code = linesOfCode.get(i);
			String type = code.getType();
	    	/* In case of a complex line breakdown to openers/closers */
			if (type == COMPLEXLINE) {
				if (UtilityRegex.checkLineIsBlockCloser(code.getRawData())){
					final String TYPE = "Closer";
					CompLine temp = new CompLine(lineNumber, code.getRawData());
					temp.setType(TYPE);
					res.add(temp);
				}
				else if(UtilityRegex.checkLineIsMethodOpenner(code.getRawData())){
					final String separator = "^\\s*void\\s*([^\\(\\s]*)\\s*\\(([^\\)]*)\\)\\s*\\{\\s*$";
					Pattern tempVar = Pattern.compile(separator);
					Matcher tempMat = tempVar.matcher(code.getRawData());
					if (tempMat.matches()){
						String funcName = tempMat.group(1);
						String funcParametersRawData = tempMat.group(2);
						funcParametersRawData.trim();
						MethodDefinitionLine temp =
								new MethodDefinitionLine(funcName, funcParametersRawData, lineNumber);
						res.add(temp);
					}
				}
				else if (UtilityRegex.checkLineIsConditional(code.getRawData())){
					final String seperator = "^\\s*(?:if|while)\\s*\\(([^\\)]*)\\)\\s*\\{\\s*";
					Pattern tempVar = Pattern.compile(seperator);
					Matcher tempMat = tempVar.matcher(code.getRawData());
					if (tempMat.matches()){
						String literalsRawData = tempMat.group(1);
						literalsRawData.trim();
						LiteralOpeningLine temp = new LiteralOpeningLine(literalsRawData, lineNumber);
						res.add(temp);
					}
				}
			}
	    	/* In case of a simple line we have further breakdown */
			else if (type == SIMPLELINE) {
				if (UtilityRegex.checkLineIsVariableDeclaration(code.getRawData())){
					final String separator = "^\\s*(final)?\\s*(int|double|boolean|String|char)\\s*(.*)[;]\\s*$";
					Pattern tempVar = Pattern.compile(separator);
					Matcher tempMat = tempVar.matcher(code.getRawData());
					if (tempMat.matches()) {
						if (tempMat.group(1) != null){
							String valueType = tempMat.group(2);
							String variablesData = tempMat.group(3);
							variablesData.trim();
							VariableDeclerationLine temp =
									new VariableDeclerationLine(valueType, variablesData, lineNumber, true);
							res.add(temp);
						}
						else {
							String valueType = tempMat.group(2);
							String variablesData = tempMat.group(3);
							variablesData.trim();
							VariableDeclerationLine temp =
									new VariableDeclerationLine(valueType, variablesData, lineNumber);
							res.add(temp);
						}
					}
				}
				else if(UtilityRegex.checkLineIsVariableSubstitution(code.getRawData())){
					final String separator = "^\\s*([^\\s]+)\\s*=\\s*([^\\s]+)\\s*[;]\\s*$";
					Pattern tempVar = Pattern.compile((separator));
					Matcher tempMat = tempVar.matcher(code.getRawData());
					if (tempMat.matches()){
						String target = tempMat.group(1);
						String source = tempMat.group(2);
						VariableSubstitutionLine temp = new VariableSubstitutionLine(target, source, lineNumber);
						res.add(temp);
					}
				}
				else if (UtilityRegex.checkLineIsMethodCall(code.getRawData())){
					final String separator = "^\\s*([^\\s]*)\\s*[(](.*)[)]\\s*[;]\\s*$";
					Pattern tempVar = Pattern.compile(separator);
					Matcher tempMat = tempVar.matcher(code.getRawData());
					if (tempMat.matches()) {
						String funcName = tempMat.group(1);
						String parametersData = tempMat.group(2);
						parametersData.trim();
						MethodCallLine temp = new MethodCallLine(funcName, parametersData, lineNumber);
						res.add(temp);
					}
				}
				else if (UtilityRegex.checkLineIsFunctionReturn(code.getRawData())){
					final String TYPE = "Return";
					SimpLine temp = new SimpLine(lineNumber, code.getRawData());
					temp.setType(TYPE);
					res.add(temp);
				}
				/* In case the simple line doesn't match any of the specified behaviours. */
				else {
					throw new SjavaFormatException();
				}
			}
		}
		return res;
	}
}
