package serializer;

import java.lang.reflect.Field;

public class Serializer {
    public static String serializeObject(Object obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n\t\"").append(objClass.getSimpleName()).append("\": {\n");
        for (Field field : objClass.getDeclaredFields()) {
            if (!field.getType().isArray()) {
                jsonBuilder.append("\t\t\"").append(field.getName()).append("\":").append(field.get(obj));
                jsonBuilder.append("\n");
            }
            else {
                jsonBuilder.append("\t\t\"").append(field.getName()).append("\": {\n");
            }
        }
        jsonBuilder.append("\t}\n");
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
}
