package serializer;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

public class Serializer {
    public static String serializeObject(Object obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n\t\"").append(objClass.getSimpleName()).append("\": {\n");
        if (!objClass.isPrimitive()) {
            for (Field field : objClass.getDeclaredFields()) {
                if (!field.getType().isArray()) {
                    if (field.get(obj) instanceof String) {
                        jsonBuilder.append("\t\t\"").append(field.getName()).append("\":").append(field.get(obj));
                        jsonBuilder.append("\n");
                    } else if (field.getType().isPrimitive()) {
                        System.out.println(field.get(obj));
                    }
                } else {
                    jsonBuilder.append("\t\t\"").append(field.getName()).append("\": {\n");
                    for (Object obje : arrayify(field.get(obj))) {
                        //jsonBuilder.append(serializeObject(obje));
                        //jsonBuilder.append(serializeObject(obje));
                    }
                }
            }
        }
        else
        {

        }
        jsonBuilder.append("\t}\n");
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    public static String serialize(Object obj) throws IllegalAccessException {
        StringBuilder jsonBuilder = new StringBuilder();
        if (obj.getClass().isArray()) {
            jsonBuilder.append("[");
            for (Object arrObj : arrayify(obj)) {
                jsonBuilder.append(serialize(arrObj));
                jsonBuilder.append(",");
            }
            jsonBuilder.delete(jsonBuilder.length() - 1, jsonBuilder.length());
            jsonBuilder.append("]");
            return jsonBuilder.toString();
        }
        else if (obj instanceof String) {
            jsonBuilder.append("\"");
            jsonBuilder.append(obj);
            jsonBuilder.append("\"");
            return jsonBuilder.toString();
        }
        else if (obj instanceof Integer) {
            jsonBuilder.append("\"");
            jsonBuilder.append(obj);
            jsonBuilder.append("\"");
            return jsonBuilder.toString();
        }
        else if (!obj.getClass().isPrimitive())  {
            jsonBuilder.append("{");
            for (Field field : obj.getClass().getFields()) {
                jsonBuilder.append(field.getName());
                jsonBuilder.append(":");
                jsonBuilder.append(serialize(field.get(obj)));
                jsonBuilder.append(",");
            }
            jsonBuilder.delete(jsonBuilder.length() - 1, jsonBuilder.length());
            jsonBuilder.append("}");
            return jsonBuilder.toString();
        }
        return jsonBuilder.toString();
    }

    /**
     * PRECONDITION: obj param needs to be categorized as an array in some way
     * @param obj an object that can be divided into indices
     * @return the original obj recognized as an array
     */
    private static Object[] arrayify(Object obj) {
        Object[] tempArr = new Object[Array.getLength(obj)];
        for (int i = 0; i < tempArr.length; i++) {
            tempArr[i] = Array.get(obj, i);
        }
        return tempArr;
    }
}
