package filesprocessing;
import orders.*;
import filters.*;
public class Section{

	/** String representation of the filter */
	private Filter filter;
	/** String representation of the order */
	private Order order;

	/** A boolean indicating if we store an error */
	private boolean errorCondition = false;

	/** A string holding the potential error message */
	private String errorMsg = "empty";

	/**
	 * The constructor function.
	 * @param filter - A given filter to be selected.
	 * @param order - A given order to sort the files by primarily.
     */
	public Section(Filter filter, Order order){
		this.filter = filter;
		this.order = order;
	}

	/** A getter for the section's filter
	 * @return - the relevant filter.
	 */
	public Filter getFilter(){
		return filter;
	}

	/** A getter for the section's order
	 *@return - the relevant Order.
	 */
	public Order getOrder(){
		return order;
	}

	/**
	 * Setting the error message in case of reaching an error.
	 * @param errorMsg - the message that should be printed along the error.
     */
	public void setError(String errorMsg){
		errorCondition = true;
		this.errorMsg = errorMsg;
	}

	/** A getter function for checking the error condition */
	public boolean errorCheck(){
		return errorCondition;
	}

	/**
	 * A getter function for the error message.
	 * @return - the relevant error, if none exist returns "empty".
     */
	public String getErrorMsg(){
		return errorMsg;
	}


}