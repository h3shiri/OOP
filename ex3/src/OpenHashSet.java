import java.util.LinkedList;

public class OpenHashSet extends SimpleHashSet {
    //TODO: check inheritance issue and visibility..
    protected int capacityMinusOne;
    protected int INITIAL_CAPACITY = 16;
    private float DEFAULT_UPPER_BOUND = 0.75f;
    private float DEFAULT_LOWER_BOUND = 0.25f;
    protected int capacity;
    protected float lowerLoadFactor;
    protected float upperLoadFactor;
    protected int numOfElements;
    protected UtillLinkList[] cells;

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor - the upper limit for the load factor.
     * @param lowerLoadFactor -  the lower limit for the load factor.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        capacity = INITIAL_CAPACITY;
        capacityMinusOne = (capacity - 1);
        this.lowerLoadFactor = lowerLoadFactor;
        this.upperLoadFactor = upperLoadFactor;
        rowsAllocation(capacity, cells);
    }

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet() {
        defaultSetting();
    }

    public OpenHashSet(String[] data) {
        defaultSetting();
        for (String item: data) {
            add(item);
        }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue - New value to add to the set.
     * @return False iff newValue already exists in the set
     */
    public boolean add(String newValue) {
        return false;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for.
     * @return True iff searchVal is found in the set
     */
    public boolean contains(String searchVal) {
        int index = calcHashCodeIndex(searchVal);
        return cells[index].contains(searchVal);
    }

    /**
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted.
     */
    public boolean delete(String toDelete) {
        boolean res = contains(toDelete);
        float potentialCapacity = (float) (numOfElements-1/capacity);
        if(potentialCapacity < lowerLoadFactor){
            int index = calcHashCodeIndex(toDelete);
            cells[index].delete(toDelete);
            numOfElements--;
            /** capacity is power of 2*/
            UtillLinkList[] modernCells = new UtillLinkList[(capacity/2)];
            //TODO: solve this init mess I wnt linkedLists of Strings...
            rowsAllocation((capacity/2), modernCells);
            totalReHashing(cells, modernCells, (capacity/2));
        }
        else{
            int index = calcHashCodeIndex(toDelete);
            cells[index].delete(toDelete);
            numOfElements--;
        }
        return res;
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * A Utility function for setting the rows on empty string linked lists.
     */
    private void rowsAllocation(int numOfRows, UtillLinkList[] cells){
        cells = new UtillLinkList[numOfRows];
        for(int i=0; i<numOfRows; i++){
            cells[i] = new UtillLinkList();
        }
    }

    /**
     * The default setting for the constructors.
     */
    private void defaultSetting(){
        upperLoadFactor = DEFAULT_UPPER_BOUND;
        lowerLoadFactor = DEFAULT_LOWER_BOUND;
        capacity = INITIAL_CAPACITY;
        capacityMinusOne = capacity-1;
        rowsAllocation(capacity, cells);
    }

    /**
     * calculating the has code given the table and the string.
     * @param target - the target string
     * @return index - a valid int within the table range.
     */
    private int calcHashCodeIndex(String target){
        return (target.hashCode())&capacityMinusOne;
    }

    private void totalReHashing(UtillLinkList[] source, UtillLinkList[] target, int newCapacity){
        capacity = newCapacity;
        capacityMinusOne = capacity-1;
        for(int i=0; i<capacity; i++){
            for(int j=0; j<source[i].size();j++){
                String temp = source[i].get(j);
                int index = calcHashCodeIndex(temp);
                target[index].add(temp);
            }
        }
        /** moving the cell's pointer to the new cells arrangement*/
        cells = target;
    }
}
