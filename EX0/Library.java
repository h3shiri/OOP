/**
* This class represents a library, which hold a collection of books. Patrons can register at the
* library to be able to check out books, if a copy of the requested book is available.
*/
public class Library{
	/** maximum number of books the library may contaign */
	int maxBookCapacity;
	/** maximum number of books this library allows a single patron to borrow at a given time */
	int maxBorrowedBooks;
	/** the maximum number of patrons a library can register */
	int maxPatronCapacity;

	/** a magical shelf to cotaign all the library's books */
	Book[] shelf;

	/** an array of the books IDs in the library */
	int[] booksIds;

	/** an array of patrons (full names) in the library */
	Patron[] patrons;

	/** an array of patrons IDs in the library */
	int[] patronsIds;

	/** an array of the number of books each patron borrowed */
	int[] numOfBorrowed;

	// TODO: set it outside of constructor
	/** magic number of failure in routine process*/
	int failure;
	/** magic number for empty int in the array */
	int emptyInt; 

	// TODO: set it outside of constructor
	/** current number of books in the library*/
	int numOfBooksInLib;

	/** current number of books in the library*/
	int numOfPatronsInLib;

	/** 
	* Creates a new library with the given parameters. 
	* @param maxBookCapacity - The maximal number of books this library can hold.
	* @param maxBorrowedBooks - The maximal number of books this library allows a single patron to
	* borrow at the same time.
	* @param maxPatronCapacity - The maximal number of registered patrons this library can handle.
	*/
	Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity){
		this.maxBookCapacity = maxBookCapacity;
		this.maxBorrowedBooks = maxBorrowedBooks;
		this.maxPatronCapacity = maxPatronCapacity;
		shelf = new Book[maxBookCapacity];
		booksIds = new int[maxBookCapacity];
		patronsIds = new int[maxPatronCapacity];
		patrons = new Patron[maxPatronCapacity];
		numOfBorrowed = new int[maxPatronCapacity];
		numOfBooksInLib = 0;
		numOfPatronsInLib = 0;
		failure = -1;
		emptyInt = -7;
		setAsEmptyIntArray(booksIds);
		setAsEmptyIntArray(numOfBorrowed);
		setAsEmptyIntArray(patronsIds);
	}

	/** a utility function to initialise the int array*/
	void setAsEmptyIntArray(int[] MyIntArray){
		int length = MyIntArray.length;
		for (int i=0; i<length; i++)
			MyIntArray[i] = emptyInt;
		}
		
	/** a utility function to search the current books in the library */
	boolean searchBooks(Book book){
		for(int i=0; i<shelf.length; i++){
			if (book.equals(shelf[i]))
				return true;
		}
		return false;
	}

	/** a utility function that locates the book's ID in the array of books */
	int locateBookId(Book book)
	{
		int index = 0;
		for(int i=0; i<shelf.length; i++){
			if (book.equals(shelf[i]))
				break;
			else
				index++;
		}
		return index;
	}

	/**
	 * Adds the given book to this library, if there is place available, and it isn't already in the library.
	 * @param book - The book to add to this library.
	 * @return a non-negative id number for the book if there was a spot and the book was successfully added,
	 * or if the book was already in the library; a negative number otherwise.
 	 */		
	int addBookToLibrary(Book book){
		/** firstly check if the book is in the library*/
		boolean present = searchBooks(book);
		if ((!present) && (numOfBooksInLib == maxBookCapacity))
			return failure;
		else if (!present)
		{
			shelf[numOfBooksInLib] = book;
			booksIds[numOfBooksInLib] = numOfBooksInLib;
			int temp = numOfBooksInLib;
			numOfBooksInLib++;
			return temp;
		}
		else
		{
			int res = locateBookId(book);
			return res;
		}
	}
	/**
	 * @param bookId - The id to check.
	 * @return true if the given number is an id of some book in the library, false otherwise. 
	 */
	boolean isBookIdValid(int bookId){
		if (bookId == emptyInt)
			return false;
		for (int i=0; i<maxBookCapacity; i++)
		{
			int temp = booksIds[i];
			if (bookId == temp)
				return true;
		}
		return false;
	}

	/**
	 * @param book - The book for which to find the id number.
	 * @return a non-negative id number of the given book if he is owned by this library, -1 otherwise. 
	 */
	int getBookId(Book book){
		boolean present = searchBooks(book);
		if (present)
			return locateBookId(book);
		else
			return failure;
	}
	/**
	 * @param bookId - The id number of the book to check.
	 * @return true if the book with the given id is available, false otherwise.
	 */
	boolean isBookAvailable(int bookId){
		int borrowerId = shelf[bookId].getCurrentBorrowerId();
		if (borrowerId == failure)
			return true;
		else
			return true;
	}

	/**
	 * @param patron - The patron for which to find the id number.
	 * @return a non-negative id number of the given patron if he is registered to this library,
	 * -1 otherwise.
	 */
	int registerPatronToLibrary(Patron patron){
		if (numOfBooksInLib > maxPatronCapacity)
			return failure;
		else{
			patronsIds[numOfPatronsInLib] = numOfPatronsInLib;
			int temp = numOfPatronsInLib;
			numOfPatronsInLib++;
			patrons[temp] = patron;
			return temp;
		}
	}

	/**
	 * @param patronId - the id to check
	 * @return true if the given number is an id of a patron in the library, false otherwise.
	 */
	boolean isPatronIdValid(int patronId){
		if (patronId == emptyInt)
				return false;
			for (int i=0; i<maxPatronCapacity; i++)
			{
				int temp = patronsIds[i];
				if (patronId == temp)
					return true;
			}
			return false;
	}

	/**
	 * @param patron - The patron for which to find the id number.
	 * @return a non-negative id number of the given patron if he is registered to this library,
	 * -1 otherwise.
	 */
	int getPatronId(Patron patron)
	{
		for(int i=0;i<maxPatronCapacity;i++){
			if (patron.equals(patrons[i]))
				return i;
		}
		return failure;
	}

	/**
	 * Marks the book with the given id number as borrowed by the patron with the given patron id,
	 * if this book is available, the given patron isn't already borrowing the maximal number of
	 * books allowed, and if the patron will enjoy this book.
	 * @param bookId - The id number of the book to borrow.
	 * @param patronId - The id number of the patron that will borrow the book.
	 * @return true if the book was borrowed successfully, false otherwise.
	 */
	boolean borrowBook(int bookId, int patronId){
		boolean available = isBookAvailable(bookId);
		boolean patronIsRegistered = isPatronIdValid(patronId);
		if ((!patronIsRegistered) || (!available))
			return false;
		boolean patronHasSpace = !(numOfBorrowed[patronId] >= maxBorrowedBooks);
		boolean patronShallEnjoyReading = patrons[patronId].willEnjoyBook(shelf[bookId]);
		if (patronHasSpace && patronShallEnjoyReading){
			numOfBorrowed[patronId]++;
			shelf[bookId].setBorrowerId(patronId);
			return true;
		}
		else
			return false;
	}

	/**
	 * Return the given book back to the library
	 * @param bookId - The id number of the book to return.
	 */	
	void returnBook(int bookId){
		if (isBookIdValid(bookId)){
			int borrowerId = shelf[bookId].getCurrentBorrowerId();
			numOfBorrowed[borrowerId]--;
			shelf[bookId].returnBook();
		}
	}

	/**
	 * Suggest the patron with the given id the book he will enjoy the most,
	 * patronId - The id number of the patron to suggest the book to.
	 * @param bookId - The id number of the book to return.
	 * @return The available book the patron with the given will enjoy the most.
	 * Null if no book is available.
	 */	
	Book suggestBookToPatron(int patronId){
		if ((numOfBooksInLib == 0) || !(isPatronIdValid(patronId)))
			return null;
		int[] valuesOfBooks;
		valuesOfBooks = new int[numOfBooksInLib];
		for(int i=0; i<numOfBooksInLib;i++){
			Book book = shelf[i];
			valuesOfBooks[i] = patrons[patronId].getBookScore(book);
		}
			int index = findMaxValue(valuesOfBooks);
			return shelf[index];
		}
	/** 
	 * A utillity function to find the index of the maximum element in a given array.
	 * @param myIntArray - An array of integers.
	 * @return An index of the element with the maximal value.
	 */
	int findMaxValue(int[] myIntArray){
		if (myIntArray.length == 1)
			return 0;
		int res = 0;
		int element = myIntArray[0];
		for(int i=0; i<myIntArray.length; i++){
			if (myIntArray[i] > element){
				res = i;
				element = myIntArray[i];
			}
		}
		return res;
	}
}