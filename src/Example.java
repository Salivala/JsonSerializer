import data.examples.SimplePojo;
import serializer.*;

public class Example {
    public static void main(String[] args) throws IllegalAccessException {
        System.out.println(Serializer.serializeObject(new SimplePojo("ok", 23)));
    }
}
