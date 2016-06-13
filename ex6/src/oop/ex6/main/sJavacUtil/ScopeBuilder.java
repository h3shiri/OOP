package oop.ex6.main.sJavacUtil;


import oop.ex6.main.line.MethodDefinitionLine;
import oop.ex6.main.line.SimpleLine;
import oop.ex6.main.line.SjavacLine;
import oop.ex6.main.variable.SjavacVariable;

import java.util.ArrayList;

public class ScopeBuilder {
    /* The genesis node reference */
    private LinkComplexNode genesisNode;

    /**
     * A basic constructor.
     * @param genesisNode - the node representing the whole file.
     */
    public ScopeBuilder(LinkComplexNode genesisNode, ArrayList<SjavacLine> classifiedLines) throws SjavaFormatException{
        this.genesisNode = genesisNode;
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
                }
            }
            else if (type == "literalOpening"){
                if (currentNode.getType() == "GenesisBlock"){
                    /* In case of a literal declared outside of a function block*/
                    throw new SjavaFormatException();
                }
                currentNode = new LinkComplexNode(currentNode, lineNumber);
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
                if (currentNode.getType() != "function"){
                    /* In case we reached a return within an inner scope */
                    throw new SjavaFormatException();
                }
            }
            else if (type == "VarDeclare"){
                ArrayList<SjavacVariable> scopeVars = currentNode.getScopeVars();
//                TODO: get relevant factory within this context.
                //TODO: add the variable into the scope vars in a nice orderly fashion.
            }
            else if (type == "MethodCall"){
//                TODO: check operations order for design purpose.
            }
            else if (type == "Substitution"){
//                TODO: similar reference.
            }
            else {
                /* In case of a non identified type */
                throw new SjavaFormatException();
            }


        }

    }
}
