import java.util.ArrayList;
import java.util.Iterator;
/** An AVL Tree */
public class AvlTree implements Iterable<Integer> {

	public class SimpleIterator implements Iterator<Integer>{
			private Iterator<Integer> iter;
			public SimpleIterator(ArrayList<Integer> array){
				iter = array.iterator();
			}
			public boolean hasNext(){
				return iter.hasNext();
			}
			public Integer next(){
				return iter.next();
			}
			public void remove() {
				throw new UnsupportedOperationException("remove");
			}
		}

	/** data members */
	private TreeLink root;
	private int size = 0;

	/**
	 * default constructor.
	 */
	public AvlTree(){
		root = new TreeLink();
	}

	/**
	 * Specified by: iterator in interface Iterable<Integer>.
	 * @return - an iterator for the Avl Tree. The returned iterator iterates over the tree nodes in an ascending order,
	 * and does NOT implement the remove() method.
     */
	public java.util.Iterator<Integer> iterator(){
		ArrayList<Integer> array = new ArrayList<Integer>();
		traverse(root, array);
		return new SimpleIterator(array);
	}

	private void traverse(TreeLink head, ArrayList<Integer> array){
		if (!head.getLeftSon().isEmpty()) {
			traverse(head.getLeftSon(), array);
		}
		array.add(head.getData());
		if (!head.getRightSon().isEmpty()){
			traverse(head.getRightSon(), array);
		}
	}

	/**
	 * Rotation to the right around the target link
	 * @param target - the link we wish to rotate right around
	 * @return - A new Link that shall represent the new head locally.
	 */
	private TreeLink rotateRight(TreeLink target){
		TreeLink subLeft = target.getLeftSon();
		if (subLeft.getRightSon().isEmpty()){
			target.setLeftSon(null);
		}
		else {
			target.setLeftSon(subLeft.getRightSon());
		}
		subLeft.setRightSon(target);
		target.heightCorrection();
		subLeft.heightCorrection();
		return subLeft;
	}

	/**
	 * Rotation to the left around the target link
	 * @param target - the link we wish to rotate right around
	 * @return - A new Link that shall represent the new head locally.
	 */
	private TreeLink rotateLeft(TreeLink target){
		TreeLink subRight = target.getRightSon();
		if (subRight.getLeftSon().isEmpty()){
			target.setRightSon(null);
		}
		else {
			target.setRightSon(subRight.getLeftSon());
		}
		subRight.setLeftSon(target);
		target.heightCorrection();
		subRight.heightCorrection();
		return subRight;
	}

	/**
	 * leveling the AVL in case of imbalance either to the right or to the left.
	 * @param target -  the target node to check for imbalance.
	 * @return - A pointer to the new local link root (if any changes were made).
     */
	private TreeLink levelTree(TreeLink target){
		target.heightCorrection();
		if (target.balanceFactor() == 2){
			if (target.getRightSon().balanceFactor() < 0){
				target.setRightSon(rotateRight(target.getRightSon()));
				target.getRightSon().heightCorrection();
			}
			return rotateLeft(target);
		}
		if (target.balanceFactor() == -2){
			if (target.getLeftSon().balanceFactor() > 0){
				target.setLeftSon(rotateLeft(target.getLeftSon()));
				target.getLeftSon().heightCorrection();
			}
			return rotateRight(target);
		}
		target.heightCorrection();
		return target;
	}

	/**
	 * Finding the minimal element in a subtree.
	 * @param head -  the local root of the subtree.
	 * @return - The minimal element in the local subtree.
	 */
	private TreeLink findMin(TreeLink head){
		if (head.getLeftSon().isEmpty()) {
			return head;
		}
		else{
			return findMin(head.getLeftSon());
		}
	}

	/**
	 * remove the minimal element from a subtree
	 * @param head -  the subtree root.
	 * @return - A new link without the local minimum.
     */
	private TreeLink removeMin(TreeLink head){
		if (head.getLeftSon().isEmpty()){
			if (head.getRightSon().isEmpty()) {
				return null;
			}
			else{
				return head.getRightSon();
			}	
		}
		else{
			head.setLeftSon(removeMin(head.getLeftSon()));
			return levelTree(head);
		}
	}

	/**
	 * deleting an element using recursive search according to the current root's data.
	 * @param head - the current root of a given subtree
	 * @param target - the value that should be deleted.
     * @return - A new modified root that doesn't contain the deletion target any longer.
     */
	private TreeLink recursiveDelete(TreeLink head, int target){
		if (head.isEmpty()) {
			return null;
		}
		if (head.getData() > target) {
			head.setLeftSon(recursiveDelete(head.getLeftSon(), target));
		}
		else if (head.getData() < target) {
			head.setRightSon(recursiveDelete(head.getRightSon(), target));
		}
		/** in case we hit the right value */
		else{
			TreeLink left = head.getLeftSon();
			TreeLink right = head.getRightSon();
			if (left.isEmpty() && right.isEmpty()) {
				return null;
			}
			else if (left.isEmpty()) {
				return right;
			}
			else if (right.isEmpty()) {
				return left;
			}
			else{
				TreeLink temp = new TreeLink(findMin(right).getData());
				if (right.getHeight() == 0) {
					head.setRightSon(null);
				}
				else{
					 head.setRightSon(removeMin(right));
				}
				head.setData(temp.getData());
			}
		}
		return levelTree(head);
	}

	/**
	 * Removes the node with the given value from the tree, if it exists.
	 * @param toDelete - the value to remove from the tree.
	 * @return - true if the given value was found and deleted, false otherwise.
     */
	public boolean delete(int toDelete){	
		if (contains(toDelete) == -1) {
			return false;
		}
		root = recursiveDelete(root, toDelete);
		size--;
		return true;
	}

	/**
	 * Add a new node with the given key to the tree.
	 * @param newValue - the value of the new node to add.
	 * @return true if the value to add is not already in the tree and it was successfully added,
	 * false otherwise.
	 */
	public boolean add(int newValue){
		/** In case the tree is empty */
		if (root.isEmpty()) {
			root = new TreeLink(newValue);
			size++;
			return true;
		}
		/** in case we already have this value */
		if (contains(newValue) != -1){
			return false;
		}
		root = recursiveAdd(root, newValue);
		size++;
		return true;
	}

	/**
	 * A recursive method for adding a new element,
	 * @param target - the root of the current subtree
	 * @param newVal - the new value that should be added into the tree.
     * @return - A new modified root that contains the new value.
     */
	private TreeLink recursiveAdd(TreeLink target, int newVal){
		if(target.isEmpty()){
			return new TreeLink(newVal);
		}
		if (target.getData() > newVal){
			target.setLeftSon(recursiveAdd(target.getLeftSon(), newVal));
			target.getLeftSon().heightCorrection();
			target.heightCorrection();
		}
		else{
			target.setRightSon(recursiveAdd(target.getRightSon(), newVal));
			target.getRightSon().heightCorrection();
			target.heightCorrection();
		}
		return levelTree(target);
	}

	/**
	 * The number of nodes in the current AVL.
	 * @return - The number of nodes in the tree.
     */
	public int size(){
		return size;
	}

	/**
	 * Check whether the tree contains the given input value.
	 * @param searchVal - value to search for.
	 * @return - if val is found in the tree,
	 * return the depth of the node (0 for the root) with the given value if it was found in the tree, -1 otherwise.
     */
	public int contains(int searchVal){
		int NOT_FOUND = -1;
		if(root.isEmpty()){
			return NOT_FOUND;
		}
		int res = 0;
		TreeLink tempRoot = root;
		// As long as we have some extra depth to dig into, iterate.
		while (!tempRoot.isEmpty()){
			int tempVal = tempRoot.getData();
			if(tempVal == searchVal){
				break;
			}
			else if(tempVal < searchVal){
				if (tempRoot.getRightSon().isEmpty()){
					res = NOT_FOUND;
					break;
				}
				else{
					tempRoot = tempRoot.getRightSon();
					res++;
					continue;
				}
			}
			else{
				if (tempRoot.getLeftSon().isEmpty()){
					res = NOT_FOUND;
					break;
				}
				else{
					tempRoot = tempRoot.getLeftSon();
					res++;
				}
			}
		}
		return res;
	}

	//TODO: remember to remove garbage main
	public static void main(String[] args) {
		AvlTree tree = new AvlTree();
		int[] exampleArray =  {2,3,5,4,6,-4};
		for (int i: exampleArray){
			tree.add(i);
		}
		System.out.println(tree.delete(3));
		System.out.println(tree.contains(4));
		System.out.println(tree.size());
		Iterator<Integer> iter = tree.iterator();
		while (iter.hasNext()){
			System.out.print(iter.next()+",");
		}
	}
}