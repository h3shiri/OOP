package filesprocessing;

import java.lang.*;
import java.io.*;
import java.util.*;

public class TypeOrder extends Order{
	/** the order's type*/
	String name;

	/** The magic string for this spesific order*/
	String TYPE = "type";

	/**
	 * The constructor method.
	 */
	public TypeOrder(){
		name = TYPE; 
	}

	/**
	 * A getter function for the Order's type/name.
	 * @return - The given name.
     */
	public String getName(){
		return name;
	}

	/**
	 * An overriding implementation of compare.
	 * @param file1 - A given file to be compared.
	 * @param file2 - A second file to be compared.
     * @return - An integer value according to the
	 * lexical order of the two files types. Positive in case file1 type is larger, negative
	 * otherwise and in case of zero compare according to the files names.
     */
	@Override
	public int compare(File file1, File file2){
		String type1 = findType(file1);
		String type2 = findType(file2);
		int res;
		res = type1.compareTo(type2);
		if(res != 0){
			return res;
		}
		/** in case they have equal type*/
		else{
			return file1.getName().compareTo(file2.getName());
		}
	}

	/**
	 * A simple parser for the given file.
	 * @param file - The target file.
	 * @return - The file's type.
     */
	private String findType(File file){
		String name = file.getName();
		String[] arr = name.split(".");
		String type = arr[arr.length-1];
		return type;
	}
}