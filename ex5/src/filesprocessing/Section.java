package filesprocessing;
import orders.*;
import filters.*;

import java.util.ArrayList;

public class Section{

	/** String representation of the filter */
	private Filter filter;
	/** String representation of the order */
	private Order order;

	/** A boolean indicating if we store an error */
	private boolean errorCondition = false;

	/** A string holding the potential error message */
	private ArrayList<String> errorMesagges = new ArrayList<>();

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
	 * Setting the error flag in case of reaching an error.
     */
	public void setError(){
		errorCondition = true;
	}

	/**
	 * Adding a new error message.
	 * @param errorMsg - the new error.
     */
	public void addError(String errorMsg){
		errorMesagges.add(errorMsg);
	}

	/** A getter function for checking the error condition */
	public boolean errorCheck(){
		return errorCondition;
	}

	/**
	 * A printer function for the error messages.
	 * Prints the relevant errors.
     */
	public void printErrorMessages(){
		for (String error : errorMesagges){
			System.err.println(error);
		}
	}


}