import Data_Structures.MyHashTable;
import Testers.MyTestingClass;

import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, User> hashTable = new MyHashTable<>();
        Random random = new Random();

        for (int i = 1; i <= 10000 ; i++) {
            int id = random.nextInt(10000);
            String name = "User" + i;
            MyTestingClass key = new MyTestingClass(id, name);
            User user = new User(name, name + "@astanait.edu.kz");
            hashTable.put(key, user);
        }
        hashTable.printSizeOfBuckets();
    }
}