package oop.ex6.main.sJavacUtil;
import oop.ex6.main.line.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

/** classifying the lines into spesific simple lines and oppeners closers */
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
					final String separator = "^\\s*(int|double|boolean|String|char)\\s*(.*)[;]\\s*$";
					Pattern tempVar = Pattern.compile(separator);
					Matcher tempMat = tempVar.matcher(code.getRawData());
					if (tempMat.matches()) {
						String valueType = tempMat.group(1);
						String variablesData = tempMat.group(2);
						VariableDecleration temp = new VariableDecleration(valueType, variablesData, lineNumber);
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
						MethodCallLine temp = new MethodCallLine(funcName, parametersData, lineNumber);
						res.add(temp);
					}
				}
			}
		}
		return res;
	}
}
