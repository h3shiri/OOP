package filesprocessing;

public class Section{

	/** Stirng representation of the filter */
	private Filter filter;
	/** String representation of the order */
	private Order order;

	public Section(Filter filter, Order order){
		this.filter = filter;
		this.order = order;
	}

	/** A getter for the section's filter*/
	public Filter getFilter(){
		return filter;
	}

	/** A getter for the section's order */
	public Order getOrder(){
		return order;
	}

}