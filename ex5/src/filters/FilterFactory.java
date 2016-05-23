package filters;
import errors.*;
import java.lang.*;
import java.util.ArrayList;

public abstract class FilterFactory{

	/** The data set of all the various filters */
	private String[] types = new String[]{"greater_than", "between", "smaller_than", "file",
	 "contains", "prefix", "suffix", "writable", "executable", "hidden", "all"};

	/**
	 * The factory method for building the various filter classes.
	 * @param rawData - the given filter's raw data that shall be created.
	 * @return - A Filter object accordingly.
     */
	public static Filter build(String rawData, int lineNumber) throws TypeOneError {
		String NEGATE = "NOT";
		Filter res = new AiFilter();
		String[] data = rawData.split("#");
		String filterType = data[0];
		boolean negation = false;
		if (data[data.length-1].equals(NEGATE)){
			negation = true;
		}
		switch (filterType) {
			case "greater_than":
				double value = Double.parseDouble(data[1]);
				res = greaterThanBuilder(value, negation, lineNumber);
				break;
			case "between":
				double value1 = Double.parseDouble(data[1]);
				double value2 = Double.parseDouble(data[2]);
				res = betweenBuilder(value1, value2, lineNumber, negation);
				break;
			case "smaller_than":
				double value3 = Double.parseDouble(data[1]);
				res = smallerBuilder(value3, lineNumber, negation);
				break;
			case "file":
				String name = data[1];
				res = new FiFilter(name, negation);
				break;
			case "contains":
				String part = data[1];
				res = new CoFilter(part, negation);
				break;
			case "prefix":
				String pre = data[1];
				res = new PrFilter(pre, negation);
				break;
			case "suffix":
				String suf = data[1];
				res = new SuFilter(suf, negation);
				break;
			case "writable":
				String affirmation = data[1];
				res = writableBuilder(affirmation, negation, lineNumber);
				break;
			case "executable":
				String affirmation2 = data[1];
				res = executableBuilder(affirmation2, negation, lineNumber);
				break;
			case "hidden":
				String affirmation3 = data[1];
				res = hiddenBuilder(affirmation3, negation, lineNumber);
				break;
			case "all":
				res = new AiFilter(negation);
				break;
			default:
				/** In case of a non-valid filter's name */
				throw new BadFilterName(lineNumber);
		}
		return res;
	}

	/**
	 * The builder for the greater_than error processing.
	 * @param floorValue - the double value.
	 * @param negation - the negation flag.
     * @return - the appropriate Filter if no crucial errors have been met.
     */
	private static Filter greaterThanBuilder(double floorValue, boolean negation, int lineNumber)
			throws NegativeDoubleException{
		checkDoubleNotNegative(floorValue, lineNumber);
		return new GrFilter(floorValue, negation);
	}

	/**
	 * The builder for the between filter, process potential errors.
	 * @param floor - lower size boundary.
	 * @param ceiling - upper size boundary.
	 * @param lineNumber - the current line number in the command file.
	 * @param negation - the negation flag.
	 * @return - A valid "between" filter in case of no errors, else an "all" filter
	 * @throws TypeOneError - A typical error in case of non logical values for the doubles, such as
	 * value1 > value 2.
	 * @throws FilterNumberException - In case of non valid input for the doubles, such as negative numbers.
     */
	private static Filter betweenBuilder(double floor, double ceiling, int lineNumber, boolean negation)
			throws TypeOneError{
		checkDoubleNotNegative(floor, lineNumber);
		checkDoubleNotNegative(ceiling, lineNumber);
		if (floor > ceiling) {
			throw new InternalFilterLogicError(lineNumber);
		}
		return new BeFilter(floor, ceiling, negation);
	}

	private static Filter smallerBuilder(double ceiling, int lineNumber, boolean negation)
			throws TypeOneError{
		checkDoubleNotNegative(ceiling, lineNumber);
		return new SmFilter(ceiling, negation);
	}

	/**
	 * The builder function for the executable filter, processes potential errors.
	 * @param literal - A literal value YES/NO (String).
	 * @param negation - the negation flag
	 * @param lineNumber - the line number in the command file.
	 * @return - A valid "executable" filter, otherwise the appropriate error.
	 * @throws LiteralNonValid - The literal is not in the valid set of expressions, Type I error.
     */
	private static Filter executableBuilder(String literal, boolean negation, int lineNumber) throws LiteralNonValid{
		checkLiteralValue(literal, lineNumber);
		return new ExFilter(literal, negation);
	}

	/**
	 * The builder for the writable filter, tests against non valid literals.
	 * @param literal - the given literal (YES/NO).
	 * @param negation - the negation flag.
	 * @param lineNumber - the line number in the command file.
	 * @return - A "writable" filter in case we have no errors, otherwise throws Type I error.
	 * @throws LiteralNonValid - An error indicating the literal is not valid.
     */
	private static Filter writableBuilder(String literal, boolean negation, int lineNumber) throws LiteralNonValid{
		checkLiteralValue(literal, lineNumber);
		return new WrFilter(literal, negation);
	}

	/**
	 * The hiddden filter constructor checks for the validity of the literal.
	 * @param literal - the YES/NO value for the filter.
	 * @param negation - the negation flag.
	 * @param lineNumber - the line number of the current command file.
	 * @return - A valid "hidden" in case of no errors.
	 * @throws LiteralNonValid
     */
	private static Filter hiddenBuilder(String literal, boolean negation, int lineNumber) throws LiteralNonValid{
		checkLiteralValue(literal, lineNumber);
		return new HiFilter(literal, negation);
	}

	/**
	 * checks a given double argument isn't negative.
	 * @param value - the double value to be tested.
	 * @throws NegativeDoubleException - A unique exception for negative doubles.
     */
	private static void checkDoubleNotNegative(double value, int lineNumber) throws NegativeDoubleException {
		if (value < 0) {
			throw new NegativeDoubleException(lineNumber);
		}
	}

	/**
	 * The literal testing for string values which are not in the valid set.
	 * @param literal - A YES/NO value (string).
	 * @param lineNumber - The line number in the Commands file.
	 * @throws LiteralNonValid - A type I error that indicates this literal is non valid.
     */
	private static void checkLiteralValue(String literal, int lineNumber) throws LiteralNonValid{
		ArrayList<String> validLiterals = new ArrayList<String>();
			validLiterals.add("YES");
			validLiterals.add("NO");
		if (!validLiterals.contains(literal)){
			throw new LiteralNonValid(lineNumber);
		}
	}
}