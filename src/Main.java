import Data_Structures.BST;
import Data_Structures.MyHashTable;
import Testers.MyTestingClass;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the part that you want to test:");
        System.out.println("1. Part 1.2 (Hash Table)");
        System.out.println("2. Part 2.2 (BST)");
        System.out.print("Enter your choice (1 or 2): ");
        int n = scanner.nextInt();

        switch (n) {
            case 1:
                testHashTable();
                break;
            case 2:
                testBST();
                break;
            default:
                System.out.println("Invalid choice. Please select either 1 or 2.");
                break;
        }
    }

    private static void testHashTable() {
        MyHashTable<MyTestingClass, User> hashTable = new MyHashTable<>();
        Random random = new Random();

        for (int i = 1; i <= 10000; i++) {
            int id = random.nextInt(10000);
            String name = "User" + i;
            MyTestingClass key = new MyTestingClass(id, name);
            User user = new User(name, name + "@astanait.edu.kz");
            hashTable.put(key, user);
        }
        hashTable.printSizeOfBuckets();
    }

    private static void testBST() {
        BST<Integer, String> bst = new BST<>();
        bst.put(1, "Nursultan Khaimuldin");
        bst.put(3, "Askar Khaimuldin");
        bst.put(4, "Balembay Balembayev");
        bst.put(2, "Almata is the best city");

        for (BST.Pair<Integer, String> elem : bst.iterator()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}
