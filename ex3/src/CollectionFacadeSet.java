import java.util.Collection;
import java.util.Collections;


public class CollectionFacadeSet extends java.lang.Object implements SimpleSet {
    protected Collection<String> collection;

    /**
     * Creates a new facade wrapping the specified collection.
     * @param collection - One of the various collections that supports SimpleSet.
     */
    public CollectionFacadeSet(Collection<String> collection) {
        this.collection = collection;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue - New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(String newValue) {
        if(collection.contains(newValue))
            return false;
        else{
            collection.add(newValue);
            return true;
        }
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public boolean contains(String searchVal) {
        return collection.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(String toDelete) {
        if(collection.contains(toDelete)){
            collection.remove(toDelete);
            return true;
        }
        else
            return false;
    }

    /**
     * @return The number of elements currently in the set
     */
    public int size() {
        return collection.size();
    }
}
