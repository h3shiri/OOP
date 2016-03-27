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

    @Override
    public boolean add(String newValue) {
        if(collection.contains(newValue))
            return false;
        else{
            collection.add(newValue);
            return true;
        }
    }

    @Override
    public boolean contains(String searchVal) {
        return collection.contains(searchVal);
    }

    @Override
    public boolean delete(String toDelete) {
        if(collection.contains(toDelete)){
            collection.remove(toDelete);
            return true;
        }
        else
            return false;
    }

    @Override
    public int size() {
        return collection.size();
    }
}
