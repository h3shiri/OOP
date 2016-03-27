
public abstract class SimpleHashSet implements SimpleSet{
    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set.
     * @return true if it managed to add the new element, false otherwise.
     */
    public abstract boolean add(String newValue);

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for.
     * @return True iff searchVal is found in the set.
     */
    public abstract boolean contains(String searchVal);

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted.
     */
    public abstract boolean delete(String toDelete);

    /**
     * tracks the current number of elements in the set.
     * @return The number of elements currently in the set
     */
    public abstract int size();
}
