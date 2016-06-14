package oop.ex6.main.sJavacUtil;


import oop.ex6.main.ifAndWhileBlock.BooleanFactory;
import oop.ex6.main.line.SjavacLine;
import oop.ex6.main.method.MethodCall;
import oop.ex6.main.method.MethodVariable;
import oop.ex6.main.method.SjavacMethod;
import oop.ex6.main.variable.NonCompatibleTypes;
import oop.ex6.main.variable.SjavacVariable;
import oop.ex6.main.variable.UnlegalVariableException;
import oop.ex6.main.variable.VariableFactory;

import java.util.ArrayList;

public class ScopeBuilder {
    /* The genesis node reference */
    private LinkComplexNode genesisNode;

    /* The globals */
    public ArrayList<SjavacVariable> globals = new ArrayList<>();

    /* The function decelerations */
    private ArrayList<SjavacMethod> declaredMethods = new ArrayList<>();

    /**
     * A basic constructor.
     * @param genesisNode - the node representing the whole file.
     */
    public ScopeBuilder(LinkComplexNode genesisNode,
                        ArrayList<SjavacLine> classifiedLines) throws SjavaFormatException{
        this.genesisNode = genesisNode;
        setGlobalsAndFunctions(classifiedLines);
        buildScopes(classifiedLines);
    }

    /**
     * A builder method for our scope tree, traversing from one scope to another seamlessly.
     * @param classifiedLines - the classified lines data.
     * @throws SjavaFormatException - In case of scoping error we throw a format exception.
     */
    private void buildScopes(ArrayList<SjavacLine> classifiedLines) throws SjavaFormatException{
        LinkComplexNode currentNode = genesisNode;
        for (int i=0; i<classifiedLines.size(); i++){
            SjavacLine temp = classifiedLines.get(i);
            String type = temp.getType();
            int lineNumber = i;
            if (type == "functionDeclare"){
                if (!currentNode.isGenesisBlock()){
                    /* Scope problem issued function in the wrong scope*/
                    throw new SjavaFormatException();
                }
                else {
                    currentNode = new LinkComplexNode(genesisNode, lineNumber);
                    currentNode.setType("function");
                    String funcName = temp.getArguments().get(0);
                    for (SjavacMethod funcTarget : declaredMethods){
                        if (funcTarget.getMethodName().equals(funcName)){
                            ArrayList<MethodVariable> params = funcTarget.getParameters();
                            currentNode.methodVars = params;
                            if (params != null) {
                                for (MethodVariable par : params) {
                                    try {
                                        SjavacVariable newVar = new SjavacVariable(par, lineNumber, null );
                                        currentNode.addNewVariable(newVar);
                                    }catch (UnlegalVariableException e){
                                        throw new SjavaFormatException();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (type == "literalOpening"){
                if (currentNode.getType() == "GenesisBlock"){
                    /* In case of a literal declared outside of a function block*/
                    throw new SjavaFormatException();
                }
                currentNode = new LinkComplexNode(currentNode, lineNumber);
                currentNode.setType("literalBlock");
                String rawData = temp.getArguments().get(0);
                new BooleanFactory(rawData, currentNode);
            }
            else if (type == "Closer"){
                if (currentNode.isGenesisBlock()){
                    /* In case we attempt to close the genesis block non-valid format */
                    throw new SjavaFormatException();
                }
                else {
                /* Balancing the scopes and then tracing the current one */
                    currentNode.counterDecrease(lineNumber);
                    currentNode = currentNode.traceCurrentNode(currentNode);
                }
            }
            else if (type == "Return"){
                if (currentNode.isGenesisBlock()){
                    /* In case we reached a return within an the most outer scope scope */
                    throw new SjavaFormatException();
                }
            }
            else if (type == "VarDeclare"){
                ArrayList<String> arguments = temp.getArguments();
                boolean finalFlag = false;
                if (arguments.size() == 3){
                    finalFlag = true;
                }
                String valueType = arguments.get(0);
                String rawData = arguments.get(1);
                try {
                    ArrayList<SjavacVariable> newVars = new VariableFactory(finalFlag, valueType,
                            rawData, lineNumber, currentNode, currentNode.getMethodVars()).getVariables();

                    for (SjavacVariable newVar : newVars){
                        currentNode.addNewVariable(newVar);
                    }
                } catch (UnlegalVariableException e){
                    throw new SjavaFormatException();
                }
//                currentNode.testOverLoading();
            }
            else if (type == "MethodCall"){
                String nameOfFunc = temp.getArguments().get(0);
                String parametersRawData = temp.getArguments().get(1);
                try {
                    MethodCall newMethodCall =
                            new MethodCall(nameOfFunc, parametersRawData, currentNode, lineNumber);
                    newMethodCall.isLegal(declaredMethods);
                } catch (oop.ex6.main.method.IllegalMethodCallException e){
                    throw new SjavaFormatException();
                }
            }
            else if (type == "Substitution"){
                String target = temp.getArguments().get(0);
                String source = temp.getArguments().get(1);
                SjavacVariable toBeMended = currentNode.getVariable(target);
                if (toBeMended.isFinal){
                    throw new SjavaFormatException();
                }
                if (UtilityRegex.checkArgumentIsVariable(source)){
                    SjavacVariable toInsert = currentNode.getVariable(source);
                    try{
                        toBeMended.morph(toInsert);
                    } catch (NonCompatibleTypes e){
                        throw new SjavaFormatException();
                    }
                }
                else{
                    try {
                        toBeMended.setValue(source);
                    } catch (UnlegalVariableException e){
                        throw new SjavaFormatException();
                    }
                }
            }
        }
    }

    /**
     * An important traversing function that initialise the globals and functions declarations.
     * @param classifiedLines - the data to construct the tree from.
     * @throws SjavaFormatException - In case of a non-valid format.
     */
    private void setGlobalsAndFunctions(ArrayList<SjavacLine> classifiedLines) throws SjavaFormatException {
        LinkComplexNode cloneGenesis = new LinkComplexNode(genesisNode);
        LinkComplexNode currentNode = cloneGenesis;
        for (int i = 0; i < classifiedLines.size(); i++) {
            SjavacLine temp = classifiedLines.get(i);
            String type = temp.getType();
            int lineNumber = i;
            if (type == "functionDeclare") {
                if (!currentNode.isGenesisBlock()) {
                    /* Scope problem issued function in the wrong scope*/
                    throw new SjavaFormatException();
                } else {
                    currentNode = new LinkComplexNode(cloneGenesis, lineNumber);
                    currentNode.setType("function");
                    String funcName = temp.getArguments().get(0);
                    String parametersRawData = temp.getArguments().get(1);
                    SjavacMethod newFunction = new SjavacMethod(funcName, parametersRawData);
                    declaredMethods.add(newFunction);
                }
            } else if (type == "literalOpening") {
                if (currentNode.getType() == "GenesisBlock") {
                    /* In case of a literal declared outside of a function block*/
                    throw new SjavaFormatException();
                }
                currentNode = new LinkComplexNode(currentNode, lineNumber);
                currentNode.setType("literalBlock");

            } else if (type == "Closer") {
                if (currentNode.isGenesisBlock()) {
                    /* In case we attempt to close the genesis block non-valid format */
                    throw new SjavaFormatException();
                } else {
                /* Balancing the scopes and then tracing the current one */
                    currentNode.counterDecrease(lineNumber);
                    currentNode = currentNode.traceCurrentNode(currentNode);
                }
            } else if (type == "Return") {
                if (currentNode.getType() != "function") {
                    /* In case we reached a return within an inner scope */
                    //throw new SjavaFormatException();
                }
            /** Setting all the globals properly */
            } else if ((type == "VarDeclare") && (currentNode.isGenesisBlock())) {
                ArrayList<String> arguments = temp.getArguments();
                boolean isFinal = false;
                if (arguments.size() == 3) {
                    isFinal = true;
                }
                String varType = arguments.get(0);
                String varRawData = arguments.get(1);
                try {
                    ArrayList<SjavacVariable> cloneGlobals = new ArrayList<>();
                    for (SjavacVariable x : globals){
                        cloneGlobals.add(x);
                    }
                    VariableFactory Factory = new VariableFactory
                            (isFinal, varType, varRawData, lineNumber, currentNode, cloneGlobals,null);
                    ArrayList<SjavacVariable> newVariables = Factory.getVariables();
                    for (SjavacVariable var : newVariables) {
                        String name = var.getName();
                        for (int l = 0; l < globals.size(); l++) {
                            SjavacVariable tempVar = globals.get(l);
                            if (tempVar.getName().equals(name)) {
//                                globals.remove(l);
                                throw new SjavaFormatException();
                            }
                        }
                        globals.add(var);
                    }
                } catch (UnlegalVariableException e) {
                    throw new SjavaFormatException();
                }
            }
            else if ((type == "Substitution") && (currentNode.isGenesisBlock())){
                String target = temp.getArguments().get(0);
                String source = temp.getArguments().get(1);
                for (SjavacVariable targetVar : globals){
                    if (targetVar.getName().equals(target)){
                        if (targetVar.isFinal){
                            throw new SjavaFormatException();
                        }
                        else if (UtilityRegex.checkArgumentIsVariable(source)){
                            SjavacVariable tempSource = currentNode.getVariable(source);
                            String targetType = targetVar.getType();
                            try {
                                SjavacVariable newVar = new SjavacVariable
                                        (tempSource.isFinal, target, targetType, tempSource,null);
                                globals.remove(targetVar);
                                globals.add(newVar);
                            } catch (UnlegalVariableException e){
                                throw new SjavaFormatException();
                            }
                        }
                        /* In case of a primitive */
                        else {
                            try {
                                SjavacVariable newVar = new SjavacVariable
                                        (false, targetVar.type, targetVar.name, source, lineNumber,null);
                                globals.remove(targetVar);
                                globals.add(newVar);
                            } catch (UnlegalVariableException e){
                                throw new SjavaFormatException();
                            }
                        }
                    }
                }
            }
            for (SjavacVariable x: globals){
                genesisNode.addNewVariable(x);
            }
        }
    }
}
