import java.lang.Math;
/** the essential link */

public class TreeLink {
	/** data members */

	/** internal data */
	private int data;
	/** potential children */
	private TreeLink leftSon;
	private TreeLink rightSon;
	/** height an important internal field for the AVL */
	private int height;

	/**
	 * A constructor for the TreeLink.
	 * @param data -  the internal value for the data
	 */
	public TreeLink(int data){
		this.data = data;
		leftSon = null;
		rightSon = null;
		height = 0;
	}

	/**
	 * default constructor
	 * notice hieght equals to minus 1 symbolising no data was inserted yet.
	 */
	public TreeLink(){
		data = 0;
		leftSon = null;
		rightSon = null;
		height = -1;
	}

	/**
	 * A getter function for the data
	 * @return - the integer stored in the link.
     */
	public int getData(){
		return data;
	}

	/**
	 * A setter function for the data.
	 * @param - the spesific data that should be set.
	 */
	public void setData(int data){
		this.data = data;
	}

	/**
	 * A setter function for the left son
	 */
	public void setLeftSon(TreeLink link){
		leftSon = link;
	}

	/**
	 * A setter function for the right son
	 */
	public void setRightSon(TreeLink link){
		rightSon = link;
	}

	/**
	 * getting local height
	 * @return - the height of the current node.
     */
	public int getHeight(){
		return height;
	}

	/**
	 * A getter method for fetching the son on the left.
	 * @return - The child on the left if there is one, else a new empty link.
	 */
	public TreeLink getLeftSon(){
		if (leftSon != null) {
			return leftSon;
		}
		else{
			//TODO: check whether I should throw an exception here..
			TreeLink emptyLink = new TreeLink();
			return emptyLink;
		}
	}

	/**
	 * A getter method for fetching the son on the left.
	 * @return - The child on the right if there is one, else a new empty link.
	 */
	public TreeLink getRightSon(){
		if (rightSon != null) {
			return rightSon;
		}
		else{
			//TODO: check whether I should throw an exception here..
			TreeLink emptyLink = new TreeLink();
			return emptyLink;
		}
	}

	/**
	 * checking whether the link is empty.
	 * @return true iff the link is empty.
     */
	public boolean isEmpty(){
		return ((data == 0) && (height == -1));
	}
	/**
	 * Updating the local height according the immediate sub-trees.
	 */
	public void heightCorrection(){

		int leftHeight = -1;
		int rightHeight = -1;
		TreeLink right = getRightSon();
		if (!right.isEmpty()) {
			rightHeight = right.getHeight();
		}
		TreeLink left = getLeftSon();
		if (!left.isEmpty()) {
			leftHeight = left.getHeight();
		}
		height = (Math.max(leftHeight, rightHeight)+1);
	}

	public int balanceFactor(){
		return (getRightSon().getHeight() - getLeftSon().getHeight());
	}

}
