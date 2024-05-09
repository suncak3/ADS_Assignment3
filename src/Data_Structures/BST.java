package Data_Structures;

import java.util.ArrayList;
import java.util.List;

public class BST <K extends Comparable<K>, V>{
    private Node root;
    private int size;

    /**
     * Represents a node of the binary tree.
     */
    private class Node{
        private K key;
        private V value;
        private Node left, right;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Adds or updates a value on the given key.
     * @param key key to insert or update
     * @param value value to be inserted
     */
    public void put(K key, V value){
        root = put(root, key, value);
    }

    /**
     * Helper method to put the value in the recursive form.
     * @param h the node
     * @param key key to insert or update
     * @param value value to be inserted
     * @return updated node after insertion or update
     */
    private Node put(Node h, K key, V value){
        if(h == null){
            size += 1;
            return new Node(key, value);
        }
        int temp = key.compareTo(h.key);
        if(temp < 0){
            h.left = put(h.left, key, value);
        }
        else if(temp > 0){
            h.right = put(h.right, key, value);
        }
        else{
            h.value = value;
        }
        return h;
    }

    /**
     * Gives the value of specific key.
     * @param key key which value is to be given.
     * @return value of the key or null
     */
    public V get(K key){
        Node h = get(root, key);
        if(h == null){
            return null;
        }
        return h.value;
    }

    /**
     * Helper method tp get the value of the given key.
     * @param h the node
     * @param key key which value is to be given.
     * @return Node with specific key
     */
    private Node get(Node h, K key){
        if(h == null){
            return null;
        }
        int temp = key.compareTo(h.key);
        if(temp < 0){
            return get(h.left, key);
        }
        else if(temp > 0){
            return get(h.right, key);
        }
        else{
            return h;
        }
    }

    /**
     * Deletes a pair key-value.
     * @param key key to be deleted
     */
    public void delete(K key){
        root = delete(root, key);
    }

    /**
     * Helper method to delete the key-value pair.
     * @param h the node
     * @param key key to be deleted
     * @return updated node after deletion
     */
    private Node delete(Node h, K key){
        if(h == null){
            return null;
        }
        int temp = key.compareTo(h.key);
        if(temp < 0){
            return delete(h.left, key);
        }
        else if(temp > 0){
            return delete(h.right, key);
        }
        else{
            if(h.left == null){
                size -= 1;
                return h.right;
            }
            if(h.right == null){
                size -= 1;
                return h.left;
            }
            h.key = minValue(h.right);
            h.right = delete(h.right, h.key);
        }
        return h;
    }

    /**
     * Finds the minimum key in the right subtree.
     * @param h root of subtree
     * @return minimum key
     */
    private K minValue(Node h){
        K minValue = h.key;
        while(h.left != null){
            minValue = h.left.key;
            h = h.left;
        }
        return minValue;
    }

    /**
     * Returns number of elements
     * @return the size of BST
     */
    public int size(){
        return size;
    }

    /**
     * An additional class to provide key-value pair access.
     * @param <K>
     * @param <V>
     */
    public static class Pair<K, V>{
        private final K key;
        private final V value;

        public Pair(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    /**
     * Provides an iterator over the key-value pairs
     * @return collection of pairs
     */
    public Iterable<Pair<K, V>> iterator(){
        List<Pair<K, V>> elements = new ArrayList<>();
        inOrderTraversal(root, elements);
        return elements;
    }

    /**
     * helper method to make in-order traversal of the binary tree.
     * @param h the node
     * @param elements collection of data
     */
    private void inOrderTraversal(Node h, List<Pair<K, V>> elements){
        if(h == null){
            return;
        }
        inOrderTraversal(h.left, elements);
        elements.add(new Pair<>(h.key, h.value));
        inOrderTraversal(h.right, elements);
    }
}
