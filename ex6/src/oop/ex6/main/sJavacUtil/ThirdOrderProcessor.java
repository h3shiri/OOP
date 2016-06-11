package oop.ex6.main.sJavacUtil;
import oop.ex6.main.line.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

/** classifying the lines into specific simple lines and oppeners closers */
public class ThirdOrderProcessor {

	public static ArrayList<SjavacLine> process(ArrayList<SjavacLine> linesOfCode) throws SjavaFormatException {
		String COMPLEXLINE = "Complex";
		String SIMPLELINE = "Simple";
		ArrayList<SjavacLine> res = new ArrayList<>();
		for (int i = 0; i < linesOfCode.size(); i++) {
			int lineNumber = i;
			SjavacLine code = linesOfCode.get(i);
			String type = code.getType();
	    	/* In case of a complex line breakdown to oppeners/closers */
			if (type == COMPLEXLINE) {

			}
	    	/* In case of simplelLine we have further breakdown */
			else if (type == SIMPLELINE) {
				if (UtilityRegex.checkLineIsVariableDeclaration(code.getRawData())){
					final String separator = "^\\s*(final)?\\s*(int|double|boolean|String|char)\\s*(.*)[;]\\s*$";
					Pattern tempVar = Pattern.compile(separator);
					Matcher tempMat = tempVar.matcher(code.getRawData());
					if (tempMat.matches()) {
						if (tempMat.group(1).equals("final")){
							String valueType = tempMat.group(2);
							String variablesData = tempMat.group(3);
							variablesData.trim();
							VariableDecleration temp = new VariableDecleration(valueType, variablesData, lineNumber, true);
							res.add(temp);
						}
						else {
							String valueType = tempMat.group(1);
							String variablesData = tempMat.group(2);
							variablesData.trim();
							//TODO: further processing into new variables, scope context.
							VariableDecleration temp = new VariableDecleration(valueType, variablesData, lineNumber);
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
			}
		}
		return res;
	}
}
