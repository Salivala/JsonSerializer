import data.examples.Event;
import data.examples.SimplePojo;
import data.examples.thing;
import serializer.*;

public class Example {
    public static void main(String[] args) throws IllegalAccessException {
        System.out.println(Serializer.serialize(new thing()));
    }
}
