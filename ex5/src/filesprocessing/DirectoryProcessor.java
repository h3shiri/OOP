package filesprocessing;
/** Utility packages */
// TODO: clean redundant importations..
import java.util.*;
import java.util.function.*;
import java.io.*;
import java.lang.*;
import java.text.*;

public class DirectoryProcessor {
	public static void main(String[] args){
		for (String arg : args){
			System.out.println(arg);
		}
		Parser parser = new Parser(args[0], args[1]);
		System.out.println(parser.getNames());
	}
}
