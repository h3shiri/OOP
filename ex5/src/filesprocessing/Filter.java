package filesprocessing;
import java.io.*;
import java.util.function.Predicate;

/** the basic structure for any given filter */
public interface Filter extends Predicate<File>{

	/**
	 * A getter function for the type of the Filter
	 * @return - depends on the class type.
	 */
	public String getName();
}