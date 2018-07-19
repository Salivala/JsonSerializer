package serializer;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class Serializer {
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
        else if (obj instanceof Integer) { // if the object is an integer
            quoteSurround(jsonBuilder, (String.valueOf(obj)));
            return jsonBuilder.toString();
        }
        else if (!obj.getClass().isPrimitive())  { // if the object is a class
            if (obj instanceof String) { // if the object is a string
                quoteSurround(jsonBuilder, ((String) obj));
                return jsonBuilder.toString();
            }
            if (obj instanceof ArrayList<?>) {
                jsonBuilder.append("[");
                for (Object arrListItem : ((ArrayList) obj)) {
                    jsonBuilder.append(serialize(arrListItem));
                    jsonBuilder.append(",");
                }
                jsonBuilder.delete(jsonBuilder.length() - 1, jsonBuilder.length());
                jsonBuilder.append("]");
                return jsonBuilder.toString();
            }
            else { // Any non-collection object ( preferably POJO )
                jsonBuilder.append("{");
                for (Field field : obj.getClass().getFields()) {
                    quoteSurround(jsonBuilder, field.getName());
                    jsonBuilder.append(":");
                    jsonBuilder.append(serialize(field.get(obj)));
                    jsonBuilder.append(",");
                }
                jsonBuilder.delete(jsonBuilder.length() - 1, jsonBuilder.length());
                jsonBuilder.append("}");
                return jsonBuilder.toString();
            }
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

    /**
     * WARNING: This method has sideeffect : modify StringBuilder object strBuild points to
     * @param strBuild Stringbuilder to modify
     * @param surroundee string to surround with quotes
     */
    private static void quoteSurround(StringBuilder strBuild, String surroundee) {
        strBuild.append("\"");
        strBuild.append(surroundee);
        strBuild.append("\"");
    }
}
