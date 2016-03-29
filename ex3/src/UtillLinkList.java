import java.util.Collection;
import java.util.LinkedList;
public class UtillLinkList{

	private LinkedList<String> list;
	/**
	 * A constructor for a utillity link-list.
	 * We created this one in order to have an array of link-lists with strings.
	 */
	public UtillLinkList() {
		list = new LinkedList<String>();
	}

	/**
	 * adding a new element into the array (if its already there it wouldn't be added)
	 * @param element - given element to add into the array
     */
	public void add(String element){
		if(!contains(element)){
			list.addLast(element);
		}
	}
	/**
	 * addidng a valid member (no need to check for contaignment)
	 * @param element -  the target string that should be added to the list.
	 */
	public void validAdd(String element){
		list.addLast(element);
	}

	/**
	 * deleting an element
	 * @param element - to remove from list
	 * @return false iff the element isn't in the list.
     */
	public boolean delete(String element){
		boolean res = list.remove(element);
		return res;
	}

	/**
	 * checking whether the target element is within the array.
	 * @param target
	 * @return true iff the element is in the array
     */
	public boolean contains(String target){
		return list.contains(target);
	}

	/**
	 * calculate the number of elements in the array.
	 * @return size of the array
     */
	public int size(){
		return list.size();
	}

	/**
	 * A useful method to access a spesific element in the array.
	 * @param index - a valid index within the array range (raises exception otherwise)
	 * @return - the element in the specific index (for a valid index).
     */
	public String get(int index){
		return list.get(index);
	}
}