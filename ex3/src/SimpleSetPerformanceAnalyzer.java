import java.util.LinkedList;
import java.util.HashSet;
import java.util.TreeSet;
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

    public static void main(String[] args) {
        String[] file1 = Ex3Utils.file2array("/Users/shiri/IdeaProjects/OOP/ex3/src/data1.txt");
        String[] file2 = Ex3Utils.file2array("/Users/shiri/IdeaProjects/OOP/ex3/src/data2.txt");
    	SimpleSet[] megaArray = new SimpleSet[5];
	    megaArray[0] = new OpenHashSet();
	    megaArray[1] = new ClosedHashSet();
	    megaArray[2] = new CollectionFacadeSet(new TreeSet<String>());
	    megaArray[3] = new CollectionFacadeSet(new LinkedList<String>());
	    megaArray[4] = new CollectionFacadeSet(new HashSet<String>());
    }
}