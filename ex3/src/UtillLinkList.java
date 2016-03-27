import java.util.LinkedList;
public class UtillLinkList extends CollectionFacadeSet{
	/**
	 * A constructor for a utillity link-list.
	 * We created this one in order to have an array of link-lists with strings.
	 */
	private CollectionFacadeSet object;
	Public UtillLinkList(){
		LinkedList<String> linkedList = new LinkedList<String>();
		object = CollectionFacadeSet(linkedList);
	}
	public static void main(String[] args) {
		UtillLinkList test = new UtillLinkList();
		// test.add("1");
		// test.add("2");
		// test.add("3");
		// for (String item : test) {
		// 	System.out.println(item);
		// }
	}
}