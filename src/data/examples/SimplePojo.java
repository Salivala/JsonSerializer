package data.examples;

import java.util.ArrayList;

public class SimplePojo {
    public String name;
    public int age;
    public int[] arr = {3,2,3};
    public ArrayList<String> hey = new ArrayList<>();
    public ArrayList<Event> events = new ArrayList<>();
    public thing n = new thing();

    public SimplePojo(String name, int age) {
        this.name = name;
        this.age = age;
        events.add(new Event());
        events.add(new Event());
        events.add(new Event());
        events.add(new Event());
        events.add(new Event());
        hey.add("bill");
        hey.add("william");
    }
}


