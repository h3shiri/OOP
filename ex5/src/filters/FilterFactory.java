package filters;
import java.io.*;
import java.lang.*;

public abstract class FilterFactory{

	/** The data set of all the various filters */
	private String[] types = new String[]{"greater_than", "between", "smaller_than", "file", "contains", "prefix", "suffix",
								  "writable", "executable", "hidden", "all"};

	// TODO: add exceptions to the factory make up.

	/**
	 * The factory method for building the various filter classes.
	 * @param rawData - the given filter's raw data that shall be created.
	 * @return - A Filter object accordingly.
     */
	public static Filter build(String rawData) {
		String NEGATE = "NOT";
		Filter res = new AlFilter();
		String[] data = rawData.split("#");
		String filterType = data[0];
//		TODO: add appropriate errors.
		boolean negation = false;
		if (data[data.length-1].equals(NEGATE)){
			negation = true;
		}
		switch (filterType) {
			case "greater_than":
				double value = Double.parseDouble(data[1]);
				if (value < 0) {
//					TODO: insert potential error here.
					break;
				}
				if (negation){
					res = new GrFilter(value, negation);
				}
				else{
					res = new GrFilter(value);
				}
				break;
			case "between":
				double value1 = Double.parseDouble(data[1]);
				double value2 = Double.parseDouble(data[2]);
				if (value1 > value2) {
//					TODO: insert appropriate error.
					break;
				}
				if (negation){
					res = new BeFilter(value1, value2, negation);
				}
				else {
					res = new BeFilter(value1, value2);
				}
				break;
			case "smaller_than":
				double value3 = Double.parseDouble(data[1]);
				if (value3 < 0) {
//					TODO: insert potential error here.
					break;
				}
				if (negation){
					res = new SmFilter(value3, negation);
				}
				else{
					res = new SmFilter(value3);
				}
				break;
			case "file":
				String name = data[1];
				if (negation) {
					res = new FiFilter(name, negation);
				}
				else{
					res = new FiFilter(name);
				}
				break;
			case "contains":
				String part = data[1];
				if (negation){
					res = new CoFilter(part, negation);
				}
				else{
					res = new CoFilter(part);
				}
				break;
			case "prefix":
				String pre = data[1];
				if (negation){
					res = new PrFilter(pre, negation);
				}
				else {
					res = new PrFilter(pre);
				}
				break;
			case "suffix":
				String suf = data[1];
				if (negation){
					res = new SuFilter(suf, negation);
				}
				else{
					res = new SuFilter(suf);
				}
				break;
			case "writable":
				String affirmation = data[1];
				if (negation){
					res = new WrFilter(affirmation, negation);
				}
				else{
					res = new WrFilter(affirmation);
				}
				break;
			case "executable":
				String affirmation2 = data[1];
				if (negation){
					res = new ExFilter(affirmation2, negation);
				}
				else {
					res = new ExFilter(affirmation2);
				}
				break;
			case "hidden":
				String affirmation3 = data[1];
				if (negation){
					res = new HiFilter(affirmation3, negation);
				}
				else{
					res = new HiFilter(affirmation3);
				}
				break;
			case "all":
				if (negation){
					res = new AlFilter(negation);
				}
				else {
					res = new AlFilter();
				}
				break;
			default:
//				TODO: insert a beautiful error appropriately.
				System.out.println("Non-existing filter type");
		}
		return res;
	}
}