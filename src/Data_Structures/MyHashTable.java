package Data_Structures;

public class MyHashTable<K, V> {

    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + ", " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;


    public MyHashTable() {
        chainArray = new HashNode[M];
        size = 0;
    }

    /**
     * Constructor that initialise hash node with specific number of buckets.
     * @param M
     */
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    /**
     * Function that returns the index for key in the chainArray.
     * @param key key to give the index for.
     * @return index in the chainArray.
     */
    private int hash(K key) {
         return Math.abs(key.hashCode() % M);
    }

    /**
     * Adds the new key-value pair int the chainArray.
     * @param key key to be added
     * @param value value to be added
     */
    public void put(K key, V value) {
         int index = hash(key);
         HashNode<K, V> h = chainArray[index];

         while(h != null){
             if(h.key.equals(key)){
                 h.value = value;
                 return;
             }
             h = h.next;
         }

         size++;
         HashNode<K, V> newNode = new HashNode<>(key, value);
         newNode.next = chainArray[index];
         chainArray[index] = newNode;

         //Resizing when load factor more than 0.7
         if((1.0 * size) / M > 0.7){
             M *= 2;
             HashNode<K, V>[] newChainArray = new HashNode[M];

             for (int i = 0; i < chainArray.length; i++) {
                 HashNode<K, V> head = chainArray[i];
                 while(head != null){
                     int newIndex = hash(head.key);
                     HashNode<K, V> next = head.next;
                     head.next = newChainArray[newIndex];
                     newChainArray[newIndex] = head;
                     head = next;
                 }
             }
             chainArray = newChainArray;
         }
    }

    /**
     * Returns the value of the given key.
     * @param key key of which value to be fined.
     * @return the value of key, or null of there is no such key.
     */
    public V get (K key){
         int index = hash(key);
         HashNode<K, V> h = chainArray[index];

         while(h != null){
             if(h.key.equals(key)){
                 return h.value;
             }
             h = h.next;
         }
         return null;
    }

    /**
     * Removes and returns the value of given key.
     * @param key key of which value is to be removed.
     * @return the removed value.
     */
    public V remove(K key){
         int index = hash(key);
         HashNode<K, V> h = chainArray[index];
         HashNode<K, V> prev = null;

         while(h != null){
             if(h.key.equals(key)){
                 break;
             }
             prev = h;
             h = h.next;
         }
         if(h == null){
             return null;
         }
         if(prev != null){
            prev.next = h.next;
         }
         else{
             chainArray[index] = h.next;
         }
         size--;
         return h.value;
    }

    /**
     * Determines the existence of the given value.
     * @param value value to be checked.
     * @return true if exists, or false otherwise.
     */
    public boolean contains(V value){
        K res = getKey(value);
        return res != null;
    }

    /**
     * Returns the key of given value.
     * @param value value of which key is to be fined.
     * @return the key of a given value.
     */
    public K getKey(V value){
        for (int i = 0; i < M; i++) {
            HashNode<K, V> h = chainArray[i];
            while(h != null){
                if(h.value == null ? value == null : h.value.equals(value)){
                    return h.key;
                }
                h = h.next;
            }
        }
        return null;
    }

    public void printSizeOfBuckets(){
        for (int i = 0; i < chainArray.length; i++) {
            int count = 0;
            HashNode<K, V> h = chainArray[i];
            while(h != null){
                count += 1;
                h = h.next;
            }
            System.out.println("Bucket " + i + ": " + count);
        }
    }
}
