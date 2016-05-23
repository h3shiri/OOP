package errors;

public class TypeTwoError extends Exception{

	public TypeTwoError(String errorMsg){
		super("ERROR: "+errorMsg+"\n");
	}
}