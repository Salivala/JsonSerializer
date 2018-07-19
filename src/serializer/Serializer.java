package serializer;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class Serializer {
    public static String serialize(Object obj) throws IllegalAccessException {
        StringBuilder jsonBuilder = new StringBuilder();
        if (obj.getClass().isArray()) {
            return serializeArray(obj, jsonBuilder);
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
                return serializeArrayList(obj, jsonBuilder);
            }
            else { // Any non-collection object ( preferably POJO )
                return serializePojo(obj, jsonBuilder);
            }
        }
        return jsonBuilder.toString();
    }

    private static String serializePojo(Object obj, StringBuilder strBuild) throws IllegalAccessException {
        strBuild.append("{");
        for (Field field : obj.getClass().getFields()) {
            quoteSurround(strBuild, field.getName());
            strBuild.append(":");
            strBuild.append(serialize(field.get(obj)));
            strBuild.append(",");
        }
        strBuild.delete(strBuild.length() - 1, strBuild.length());
        strBuild.append("}");
        return strBuild.toString();
    }

    private static String serializeArray(Object obj, StringBuilder strBuild) throws IllegalAccessException {
        strBuild.append("[");
        for (Object arrObj : arrayify(obj)) {
            strBuild.append(serialize(arrObj));
            strBuild.append(",");
        }
        strBuild.delete(strBuild.length() - 1, strBuild.length());
        strBuild.append("]");
        return strBuild.toString();
    }

    private static String serializeArrayList(Object obj, StringBuilder strBuild) throws IllegalAccessException {
        strBuild.append("[");
        for (Object arrListItem : ((ArrayList) obj)) {
            strBuild.append(serialize(arrListItem));
            strBuild.append(",");
        }
        strBuild.delete(strBuild.length() - 1, strBuild.length());
        strBuild.append("]");
        return strBuild.toString();
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
