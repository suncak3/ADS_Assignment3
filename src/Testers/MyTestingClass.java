package Testers;

public class MyTestingClass {
    private int id;
    private String name;

    public MyTestingClass(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 31 * hash + id;
        hash = 31 * hash + (name != null ? name.hashCode() : 0);
        return hash;
    }
}
