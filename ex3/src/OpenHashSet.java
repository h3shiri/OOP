import java.util.LinkedList;

public class OpenHashSet extends SimpleHashSet {
    //TODO: check inheritance issue and visibility..
    protected int capacityMinusOne;
    private final int INITIAL_CAPACITY = 16;
    private final float DEFAULT_UPPER_BOUND = 0.75f;
    private final float DEFAULT_LOWER_BOUND = 0.25f;
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
        cells = new UtillLinkList[capacity];
        rowsAllocation(capacity, cells);
    }

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet() {
        defaultSetting();
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values would be ignored.
     * The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data - the data to construct the table from.
     */
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
        boolean res = contains(newValue);
        /** in case we already have the element*/
        if (res){
            return false;
        }
        float potentialLoadFactor = (float) ((numOfElements+1)/(double)capacity);
        if (potentialLoadFactor > upperLoadFactor){
            UtillLinkList[] modernCells = new UtillLinkList[(capacity*2)];
            rowsAllocation((capacity*2), modernCells);
            totalReHashing(cells, modernCells, (capacity*2));
        }
        int index = calcHashCodeIndex(newValue);
        cells[index].add(newValue);
        numOfElements++;
        return (!res);
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
        float potentialLoadFactor = (float) ((numOfElements-1)/(double)capacity);
        if(potentialLoadFactor < lowerLoadFactor){
            /** capacity is power of 2*/
            UtillLinkList[] modernCells = new UtillLinkList[(capacity/2)];
            rowsAllocation((capacity/2), modernCells);
            totalReHashing(cells, modernCells, (capacity/2));
        }
        int index = calcHashCodeIndex(toDelete);
        cells[index].delete(toDelete);
        numOfElements--;
        return res;
    }

    /**
     * The number of elements currently in the set.
     * @return
     */
    public int size() {
        return numOfElements;
    }
    //TODO: check the capacity thing for the SimpleHashSet..etc
    /**
     * The capacity of the table.
     * @return - The current capacity (number of cells) of the table.
     */
    public int capacity(){
        return capacity;
    }

    /**
     * A Utility function for setting the rows on empty string linked lists.
     */
    private void rowsAllocation(int numOfRows, UtillLinkList[] temp){
        for(int i=0; i<numOfRows; i++){
            temp[i] = new UtillLinkList();
        }
    }

    //TODO: check for repetitions such as this one in open/closed hash classes
    /**
     * The default setting for the constructors.
     */
    private void defaultSetting(){
        upperLoadFactor = DEFAULT_UPPER_BOUND;
        lowerLoadFactor = DEFAULT_LOWER_BOUND;
        capacity = INITIAL_CAPACITY;
        capacityMinusOne = capacity-1;
        cells = new UtillLinkList[capacity];
        rowsAllocation(capacity, cells);
    }

    /**
     * calculating the has code given the table and the string.
     * @param target - the target string.
     * @return index - a valid int within the table range.
     */
    private int calcHashCodeIndex(String target){
        return (target.hashCode())&capacityMinusOne;
    }

    /**
     * Used for re-hashing the table into a smaller/larger one.
     * @param source - original table to be re allocated.
     * @param target - the new table.
     * @param newCapacity - the new capacity.
     */
    private void totalReHashing(UtillLinkList[] source, UtillLinkList[] target, int newCapacity){
        for(int i=0; i<capacity; i++){
            for(int j=0; j<source[i].size();j++){
                String temp = source[i].get(j);
                int index = calcHashCodeIndex(temp);
                /** Uses a special add that doesn't check for validity. */
                target[index].validAdd(temp);
            }
        }
        capacity = newCapacity;
        capacityMinusOne = capacity-1;
        /** moving the cell's pointer to the new cells arrangement*/
        cells = target;
    }
}
