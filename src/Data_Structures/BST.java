package Data_Structures;

import java.util.ArrayList;
import java.util.List;

public class BST <K extends Comparable<K>, V>{
    private Node root;

    private class Node{
        private K key;
        private V value;
        private Node left, right;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value){
        root = put(root, key, value);
    }

    private Node put(Node h, K key, V value){
        if(h == null){
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

    public V get(K key){
        Node h = get(root, key);
        if(h == null){
            return null;
        }
        return h.value;
    }

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

    public void delete(K key){
        root = delete(root, key);
    }

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
                return h.right;
            }
            if(h.right == null){
                return h.left;
            }
            h.key = minValue(h.right);
            h.right = delete(h.right, h.key);
        }
        return h;
    }

    private K minValue(Node h){
        K minValue = h.key;
        while(h.left != null){
            minValue = h.left.key;
            h = h.left;
        }
        return minValue;
    }

    public Iterable<K> iterator(){
        List<K> keys = new ArrayList<>();
        inOrderTraversal(root, keys);
        return keys;
    }

    private void inOrderTraversal(Node h, List<K> keys){
        if(h == null){
            return;
        }
        inOrderTraversal(h.left, keys);
        keys.add(h.key);
        inOrderTraversal(h.right, keys);
    }
}
