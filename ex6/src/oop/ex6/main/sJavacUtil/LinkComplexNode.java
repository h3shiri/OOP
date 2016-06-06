package oop.ex6.main.sJavacUtil;
import java.util.ArrayList;
import oop.ex6.main.line.*;

public class LinkComplexNode{
	/* data members */

	/** An array holding all the children */
	private ArrayList<LinkComplexNode> children;

	/** A single pointer to the father */
	private LinkComplexNode father;

	/** boolean indicating if the node has a father similar to non-empty */
	private final boolean hasAFather;

	/** holding the line data */
	private ArrayList<SjavacLine> dataSet = new ArrayList<>();

	/** important strng holding the cluse type (method Decleration/if/while) */
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
	}

	public LinkComplexNode(int currentLine){
		this.hasAFather = false;
		this.openningLine = currentLine;
	}
	public boolean checkClosure(){
		return (clausesCounter == 0);
	}

	protected void counterIncrease(){
		clausesCounter++;
		if (hasAFather) {
			father.counterIncrease();
		}
	}

	protected void counterDecrease(){
		clausesCounter--;
		if (hasAFather) {
			father.counterDecrease();
		}
	}

	private void setClosingLine(int currentLine){
		this.closingLine = currentLine;
	}

	protected void addChild(LinkComplexNode child){
		children.add(child);
	}

	/**
	 * A getter function for the current node children
	 */
	private ArrayList<LinkComplexNode> getChildren(){
		return children;
	}

}