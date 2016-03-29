import Ex3Utils;

/** Performance Analysis*/

//TODO: remove garbage
/** date members 
1) OpenHashSet
2) ClosedHashSet
3) Java’s TreeSet
4) Java’s LinkedList
5) Java’s HashSet
*/
file1 =  file2array(data1.txt);
file2 =  file2array(data2.txt);

CollectionFacadeSet[] megaArray = new CollectionFacadeSet[5];
megaArray[0] = new OpenHashSet();
megaArray[1] = new ClosedHashSet();
megaArray[2] = new TreeSet<String>();
megaArray[3] = new LinkedList<String>();
megaArray[4] = new HashSet<String>();