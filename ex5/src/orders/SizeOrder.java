package orders;

import java.lang.*;
import java.io.*;
import java.util.*;

public class SizeOrder extends Order{
	/** the order's type*/
	String name;

	/** Magic numbers for the comparator in the compare method*/
	int POSITIVE = 1;
	int NEGATIVE = -1;

	/** The magic string for this spesific order*/
	String TYPE = "size";

	public SizeOrder(){
		name = TYPE;
	}

	public String getName(){
		return name;
	}

	@Override
	public int compare(File file1, File file2){
		long size1 = file1.getTotalSpace();
		long size2 = file2. getTotalSpace();
		long res = (size1 - size2);
		if (res != 0) {
			if (res > 0) {
				return POSITIVE;
			}
			else{
				return NEGATIVE;
			}
		}
		/** in case they have equal size */
		else{
			return file1.getName().compareTo(file2.getName());
		}
	}

}