import java.util.LinkedList;
import java.util.HashSet;
import java.util.TreeSet;
/** we import this module for the repr function*/
//TODO: check you are allowed to import this module.
import java.util.Arrays;
//TODO: Wtf?!
/**
 * Performance Analysis date members
 * 1) OpenHashSet
 * 2) ClosedHashSet
 * 3) Java’s TreeSet
 * 4) Java’s LinkedList
 * 5) Java’s HashSet
 */
//TODO: remember to remove garbage
public class SimpleSetPerformanceAnalyzer{
	/** magic numbers*/
	private static final int WARM_UP = 70,000;
	private static final int ARRAY_SIZE = 5;

    public static void main(String[] args) {
    	/** basic set up */
        String[] file1 = Ex3Utils.file2array("/cs/stud/h3shiri/safe/OOP/ex3/src/data1.txt");
        String[] file2 = Ex3Utils.file2array("/cs/stud/h3shiri/safe/OOP/ex3/src/data2.txt");
    	SimpleSet[] megaArray = new SimpleSet[ARRAY_SIZE];
	    megaArray[0] = new OpenHashSet();
	    megaArray[1] = new ClosedHashSet();
	    megaArray[2] = new CollectionFacadeSet(new TreeSet<String>());
	    megaArray[3] = new CollectionFacadeSet(new LinkedList<String>());
	    megaArray[4] = new CollectionFacadeSet(new HashSet<String>());

	    boolean test1 = true;
	    boolean test2 = false;
	    boolean test3 = true;

	    if (test1) {
	    	long[] results = new long[ARRAY_SIZE];
	    	for(int i=0; i<ARRAY_SIZE; i++){
	    		SimpleSet set = megaArray[i];
	    		results[i] = testInitSet(set, file1);
	    	}
	    	System.out.println(Arrays.toString(results));
	    }
	    if (test2) {
	    	long[] results = new long[ARRAY_SIZE];
	    	for(int i=0; i<ARRAY_SIZE; i++){
	    		SimpleSet set = megaArray[i];
	    		results[i] = testInitSet(set, file2);
	    	}
	    	System.out.println(Arrays.toString(results));
	    }
	    if (test3 && test1) {
	    	String TARGET = "hi";
	    	long[] results = new long[ARRAY_SIZE];
	    	for (int i=0; i<ARRAY_SIZE; i++) {
	    		if (i==4){
	    			continue;
	    		}
	       		SimpleSet set = megaArray[i];
	       		results[i] = testContians(set, TARGET);
	    	}
	    	results[3] = testContainsLinkList(megaArray[4], TARGET);
	    }
    }
    private static long testInitSet(SimpleSet set, String[] words){
    	long timeBefore = System.nanoTime();
    	for (String word : words) {
    		set.add(word);
    	}
    	long difference = ((System.nanoTime() - timeBefore)/1000000);
    	return difference;
    }
    private static long testContians(SimpleSet set, String target){
    	/** warm up the JVM */
    	for (int i=0;i<WARM_UP ;i++) {
    		set.contains(target);
    	}
    	long timeBefore = System.nanoTime();
    	for (int i=0;i<WARM_UP ;i++) {
    		set.contains(target);
    	}
    	long difference = ((System.nanoTime() - timeBefore)/70000);
    }
    private static long testContainsLinkList(SimpleSet set, String target){
    	long timeBefore = System.nanoTime();
    	set.contains(target);
    	long difference = (System.nanoTime() - timeBefore);
    	return difference;
    }
}