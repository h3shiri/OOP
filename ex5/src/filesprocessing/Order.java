package filesprocessing;
import java.lang.*;
import java.io.*;
import java.util.*;

public abstract class Order implements Comparator{

	public abstract int compare(File file1, File file2);
} 