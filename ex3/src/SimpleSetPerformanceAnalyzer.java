import java.util.LinkedList;
import java.util.HashSet;
import java.util.TreeSet;
/** we import this module for the repr function*/
import java.util.Arrays;
/**
 * Performance Analysis date members
 * 1) OpenHashSet
 * 2) ClosedHashSet
 * 3) Java’s TreeSet
 * 4) Java’s LinkedList
 * 5) Java’s HashSet
 */
public class SimpleSetPerformanceAnalyzer{
	/** magic numbers*/
	private final static int WARM_UP = 70000;
	private final static int ARRAY_SIZE = 5;
	private final static int CONVERSION_TO_MILLI_SEC = 1000000;

    public static void main(String[] args) {
    	/** basic set up */
        String[] file1 = Ex3Utils.file2array("/Users/shiri/IdeaProjects/OOP/ex3/src/data1.txt");
        String[] file2 = Ex3Utils.file2array("/Users/shiri/IdeaProjects/OOP/ex3/src/data2.txt");
        /** setting up the major array for all the various tests */
    	SimpleSet[] megaArray = new SimpleSet[ARRAY_SIZE];
	    megaArray[0] = new OpenHashSet();
	    megaArray[1] = new ClosedHashSet();
	    megaArray[2] = new CollectionFacadeSet(new TreeSet<String>());
	    megaArray[3] = new CollectionFacadeSet(new LinkedList<String>());
	    megaArray[4] = new CollectionFacadeSet(new HashSet<String>());

	    /** Setting which tests should run*/
	    /** init all data structures with data1. */
		boolean test1 = false;
		/** init all data structures with data2. */
	    boolean test2 = false;
		/** test for searching time of "hi" given data1. */
	    boolean test3 = false;
		/** test for searching time of "-13170890158" given data1. */
	    boolean test4 = false;
		/** test for searching time of "23" given data2. */
	    boolean test5 = false;
		/** test for searching times of "hi given data2. */
	    boolean test6 = false;

	    if (test1) {
	    	/** Controls the print flow */
	    	boolean printOn = false;
	    	totalInitTest(megaArray, file1, ARRAY_SIZE, printOn);
	    }
	    if (test2) {
	    	/** Controls the print flow */
	    	boolean printOn = false;
			totalInitTest(megaArray, file2, ARRAY_SIZE, printOn);
	    }
	    if (test3 && test1) {
			totalContainmentTest(megaArray, "hi", ARRAY_SIZE);
	    }
	    if (test4 && test1){
			totalContainmentTest(megaArray, "-13170890158", ARRAY_SIZE);
	    }
	    if (test2 && test5) {
			totalContainmentTest(megaArray, "23", ARRAY_SIZE);
	    }
	    if (test2 && test6) {
			totalContainmentTest(megaArray, "hi", ARRAY_SIZE);
		}
    }
    /**
     * Testing for initiallising time for the various data structures
     * @param set - the data structure
     * @param words - the words given to init the data structure with.
   	 * @retun The time it took in ms.
     */
    private static long testInitSet(SimpleSet set, String[] words){
    	long timeBefore = System.nanoTime();
    	for (String word : words) {
    		set.add(word);
    	}
    	long difference = ((System.nanoTime() - timeBefore)/CONVERSION_TO_MILLI_SEC);
    	return difference;
    }

	/**
	 * testing for the containment of a specific value.
	 * @param set - the given data structure.
	 * @param target - the string to be searched
     * @return The time it took to find it in ns(approximated given the warm up time).
     */
    private static long testContains(SimpleSet set, String target){
    	/** warm up the JVM */
    	for (int i=0;i<WARM_UP ;i++) {
    		set.contains(target);
    	}
    	long timeBefore = System.nanoTime();
    	for (int i=0;i<WARM_UP ;i++) {
    		set.contains(target);
    	}
    	long difference = ((System.nanoTime() - timeBefore)/70000);
    	return difference;
    }

	/**
	 * testing for containment using linkList there is no need for JVM warm up.
	 * @param set - in our case the specific link list.
	 * @param target - the string that should be searched
     * @return The time it took to find it in ns.
     */
    private static long testContainsLinkList(SimpleSet set, String target){
    	long timeBefore = System.nanoTime();
    	set.contains(target);
    	long difference = (System.nanoTime() - timeBefore);
    	return difference;
    }

	/**
	 * testing the initialising for all the various data structures.
	 * @param megaArray - A SimpleSet array that contains all the data structures.
	 * @param file - the file that contain all the strings (aka the actual data).
	 * @param arraySize - the SimpleSet array size
     * @param printOn - A boolean controlling whether we should print the results.
     */
	private static void totalInitTest(SimpleSet[] megaArray, String[] file, int arraySize, boolean printOn){
		long[] results = new long[arraySize];
		for(int i=0; i<arraySize; i++){
			SimpleSet set = megaArray[i];
			results[i] = testInitSet(set, file);
		}
		/** The boolean for controlling the print flow */
		if (printOn) {
			System.out.println(Arrays.toString(results));
		}
	}

	/**
	 * Tests all the data structures searching times for a given target, then prints the results.
	 * @param megaArray - the SimpleSet array contains all the data structures.
	 * @param target - The String that should be searched
	 * @param arraySize - the SimpleSet array's size.
     */
	private static void totalContainmentTest(SimpleSet[] megaArray, String target, int arraySize){
		int linkListLocation = 3;
		long[] results = new long[arraySize];
		for (int i=0; i<arraySize; i++) {
			if (i==linkListLocation){
				continue;
			}
			SimpleSet set = megaArray[i];
			results[i] = testContains(set, target);
		}
		results[linkListLocation] = testContainsLinkList(megaArray[linkListLocation], target);
		System.out.println(Arrays.toString(results));
	}

	/** 
	 * bonus utillity function for measuring the warm up and the affect on the measurment of contains.
	 * @param megaArray - the simpleSet array containning all the various data structures.
	 * @param target - the string to be searched in each contains operation.
	 * @param arraySize - the megaArray size.
	 */
	private static void bonusTest(SimpleSet[] megaArray, String target, int arraySize) {
		/** warm up time the JVM with various Data structures. */
		long[] warmUpTime = new long[arraySize];
		/** the time for the contain operation to perform. */
		long[] actualSearching = new long[arraySize];

		for (int i = 0; i < arraySize; i++) {
			SimpleSet set = megaArray[i];
			long timeBeforeWarmUp = System.nanoTime();
			for (int j = 0; j < WARM_UP; j++) {
				set.contains(target);
			}
			long warmUp = ((System.nanoTime() - timeBeforeWarmUp) / CONVERSION_TO_MILLI_SEC);
			warmUpTime[i] = warmUp;
			long timeBefore = System.nanoTime();
			for (int j = 0; j < WARM_UP; j++) {
				set.contains(target);
			}
			long difference = ((System.nanoTime() - timeBefore) / WARM_UP);
			actualSearching[i] = difference;
		}
		System.out.println("Warm-Up time consumption:");
		System.out.println(Arrays.toString(warmUpTime));
		System.out.println("Actual searching time:");
		System.out.println(Arrays.toString(actualSearching));
	}
}