package oop.ex6.main.sJavacUtil;
import java.util.ArrayList;

import oop.ex6.main.line.*;
import oop.ex6.main.variable.SjavacVariable;

public class LinkComplexNode{
	/* data members */

	/** An array holding the scope variables */
	private ArrayList<SjavacVariable> scopeVars = new ArrayList<>();

	/** An array holding all the children */
	private ArrayList<LinkComplexNode> children = new ArrayList<>();

	/** A single pointer to the father */
	private LinkComplexNode father;

	/** boolean indicating if the node has a father similar to non-empty */
	private final boolean hasAFather;

	/** holding the line data */
	private ArrayList<SjavacLine> dataSet = new ArrayList<>();

	/** important string holding the clause type (method Decleration/if/while) */
	private String type;

	// TODO: check u support max.Integer depth functionallity
	/** The Openning/Closing internal counter */
	private int clausesCounter;

	/** internal counterer for tracing the openning line */
	private final int openningLine;

	/** internal counter for tracing the closing line */
	private int closingLine;

	public LinkComplexNode(LinkComplexNode father, int currentLine){
		this.father = father;
		this.hasAFather = true;
		this.openningLine = currentLine;
		father.addChild(this);
		counterIncrease();
	}

	/**
	 * Genesis constructor only works once for the whole file.
	 * The most outer scope in the code contains all functions as children eventually.
     */
	public LinkComplexNode(int numberOfLines){
		this.hasAFather = false;
		this.openningLine = -1; /* prior to any code being read */
		this.closingLine = numberOfLines;
		this.type = "GenesisBlock";
	}

	/**
	 * A tester function checking the current block is balanced after closing it.
	 * @return - true iff the current block has equal number of closers and openers.
     */
	public boolean checkClosure(){
		return (clausesCounter == 0);
	}

	/**
	 * Opening new clauses should increase counters.
	 */
	public void counterIncrease(){
		clausesCounter++;
		if (hasAFather) {
			father.counterIncrease();
		}
	}

	/**
	 * Closing a close should have a trickle up affect.
	 * in case of a the clauses counter reaching 0 should set closing line.
	 * @param currentLine - the current line reference.
	 */
	public void counterDecrease(int currentLine){
		clausesCounter--;
		if (clausesCounter == 0){
			if (!isGenesisBlock()) {
				setClosingLine(currentLine);
			}
		}
		if (hasAFather) {
			father.counterDecrease(currentLine);
		}
	}

	/**
	 * A setter function for the closing line.
	 * @param currentLine - the reference for the current line.
     */
	public void setClosingLine(int currentLine){
		this.closingLine = currentLine;
	}

	/**
	 * Adding a new kid aka a new scope (tree structure).
	 * @param child - the relevant new node.
     */
	public void addChild(LinkComplexNode child){
		children.add(child);
	}

	/**
	 * A getter function for the current node children
	 */
	public ArrayList<LinkComplexNode> getChildren(){
		return children;
	}

	/**
	 * A getter function for the scope variables.
	 * @return - the relevant scope variables (To this specific node).
     */
	public ArrayList<SjavacVariable> getScopeVars() {
		return scopeVars;
	}

	/**
	 * Adding a new variable to this scope.
	 * it takes into account shadowing.
	 * @param var - the new var to be added.
     */
	public void addVarToScope(SjavacVariable var){
		for (int i=0; i<scopeVars.size(); i++) {
			SjavacVariable temp = scopeVars.get(i);
			if (temp.getName().equals(var.getName())){
				scopeVars.remove(i);
			}
		}
		/* we do mix the order slightly, but it has no affect on the scope */
		scopeVars.add(var);
	}

	/**
	 * A useful tracing function for a variable with the target name.
	 * @param name - the requested variable.
	 * @return - the corresponding variable in the most relevant scope.
	 * @throws NonExistingVariableException - in case of a non existing variable.
     */
	public SjavacVariable getVariable(String name) throws NonExistingVariableException{
		for (int i=0; i<scopeVars.size(); i++){
			SjavacVariable temp = scopeVars.get(i);
			if (temp.getName().equals(name)){
				return temp;
			}
		}
		if (!isGenesisBlock()){
			return getFather().getVariable(name);
		}
		else{
			throw new NonExistingVariableException();
		}
	}

	/**
	 * A getter function checking if it has a father.
	 * In our case everything except the genesis block.
	 * @return true - iff it is the genesis block.
     */
	public boolean isGenesisBlock() {
		return !(hasAFather);
	}

	/**
	 * A getter function for the father node.
	 * @return - A complex link that is the father node.
     */
	public LinkComplexNode getFather() {
		return father;
	}

	/**
	 * A getter function for the closing line.
	 * @return - the line number of the closing line.
	 * In case of the genesis block its the number of lines in the code.
     */
	public int getClosingLine() {
		return closingLine;
	}

	/**
	 * A setter function for the block type.
	 * @param type - the relevant type.
     */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * A getter function for the block type.
	 * @return - the relevant type.
     */
	public String getType() {
		return type;
	}
}