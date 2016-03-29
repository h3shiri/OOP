
public class ClosedHashSet extends SimpleHashSet{
    protected int capacityMinusOne;
    protected int INITIAL_CAPACITY = 16;
    private float DEFAULT_UPPER_BOUND = 0.75f;
    private float DEFAULT_LOWER_BOUND = 0.25f;
    protected int capacity;
    protected float lowerLoadFactor;
    protected float upperLoadFactor;
    protected int numOfElements;
    private String[] array;

    /**
     * the default empty constructor.
     */
    public ClosedHashSet() {
        defaultSetting();
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor - the upper limit for the load factor.
     * @param lowerLoadFactor -  the lower limit for the load factor.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        capacity = INITIAL_CAPACITY;
        capacityMinusOne = (capacity - 1);
        this.lowerLoadFactor = lowerLoadFactor;
        this.upperLoadFactor = upperLoadFactor;
        array = new String[capacity];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values would be ignored.
     * The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data - the data to construct the table from.
     */
    public ClosedHashSet(String[] data) {
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
        if (res)
            return false;
        float potentialLoadFactor = (float) ((numOfElements+1)/(double)capacity);
        if (potentialLoadFactor > upperLoadFactor){
            String[] modernArray = new String[(capacity*2)];
            totalReHashing(modernArray, (capacity*2));
        }
        numOfElements++;
        int index = calcHashCodeIndex(newValue, capacity, array);
        array[index] = newValue;
        return true;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for.
     * @return True iff searchVal is found in the set
     */
    public boolean contains(String searchVal) {
        boolean res = false;
        for (int i=0; i<capacity; i++){
            if (array[i] == null)
                continue;
            if (array[i].equals(searchVal))
                res = true;
        }
        return res;
    }

    /**
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted.
     */
    public boolean delete(String toDelete) {
        boolean res = contains(toDelete);
        /** in case we don't have the element*/
        if (!res)
            return false;
        float potentialLoadFactor = (float) ((numOfElements-1)/(double)capacity);
        if (potentialLoadFactor < lowerLoadFactor){
            String[] modernArray = new String[(capacity/2)];
            totalReHashing(modernArray, (capacity/2));
        }
        int index = calcHashCodeIndex(toDelete, capacity, array);
        array[index] = null;
        numOfElements--;
        return true;
    }

    /**
     * The number of elements currently in the set.
     * @return the current number of elements in the array.
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
     * The default setting for the constructors.
     */
    private void defaultSetting(){
        upperLoadFactor = DEFAULT_UPPER_BOUND;
        lowerLoadFactor = DEFAULT_LOWER_BOUND;
        capacity = INITIAL_CAPACITY;
        capacityMinusOne = capacity-1;
        array = new String[capacity];
    }

    private void totalReHashing(String[] target, int newCapacity){
        for (int i=0; i<capacity; i++){
            if (array[i] != null) {
                int index = calcHashCodeIndex(array[i], newCapacity, target);
                target[index] = array[i];
            }
        }
        array = target;
        capacity = newCapacity;
        capacityMinusOne = (capacity-1);
    }

    /**
     * calculating the valid hash code index with quadratic probing.
     * @param target - string that should be added to the table.
     * @param currentCapacity - the capacity of the table.
     * @return
     */
    private int calcHashCodeIndex(String target, int currentCapacity, String[] currentArray){
        int i = 0;
        /** dummy value pre calculation*/
        int res = -7;
        boolean success = false;
        while (!success) {
            int index = (target.hashCode() + ((i + (i * i)) / 2)) & (currentCapacity - 1);
            if(currentArray[index] == null){
                success = true;
                res = index;
            }
            else
                i++;
        }
        return res;
    }
}
