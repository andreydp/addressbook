package com.levelup.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by java on 07.03.2017.
 */
public class XMLParser {

    public String toXML(Object obj) {
        StringBuilder builder = new StringBuilder();

        Class clazz = obj.getClass();

        builder.append(String.format("<%s>", clazz.getSimpleName()));
        builder.append("\r\n");

        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                String fieldName = field.getName();

                if (!field.isAccessible()) field.setAccessible(true);

                builder.append(String.format("\t<%s>", fieldName));
                builder.append(field.get(obj));
                builder.append(String.format("</%s>", fieldName));
                builder.append("\r\n");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        builder.append(String.format("</%s>", clazz.getSimpleName()));
        return builder.toString();
    }

    public <T> T parseXML(String xml, Class clazz) {
        try {
            Object obj = clazz.newInstance();

            String[] lines = xml.split("\r\n");

            for (String line : lines) {
                if (line.trim().equals(String.format("<%s>", clazz.getSimpleName()))
                        || line.trim().equals(String.format("</%s>", clazz.getSimpleName()))) continue;

                int startIndex = line.indexOf("<") + 1;
                int endIndex = line.indexOf(">");
                String fieldName = line.substring(startIndex, endIndex);

                int startValueIndex = line.indexOf(">") + 1;
                int endValueIndex = line.indexOf("</");
                String fieldValue = line.substring(startValueIndex, endValueIndex).trim();

                Field field;
                try {
                    field = clazz.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    continue;
                }

                Object value = getFieldValue(field, fieldValue);

                try {
                    Method method = clazz.getDeclaredMethod(composeSetterName(fieldName), field.getType());

                    method.invoke(obj, value);
                } catch (NoSuchMethodException e) {
                    if (field.isAccessible()) {
                        field.set(obj, value);
                    } else {
                        field.setAccessible(true);
                        field.set(obj, value);
                        field.setAccessible(false);
                    }
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            return (T) obj;

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String composeSetterName(String fieldName) {
        return "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
    }

    private Object getFieldValue(Field field, String fieldValue) {
        Class type = field.getType();
        if (type == int.class || type == Integer.class) {
            return Integer.parseInt(fieldValue);
        } else if (type == long.class || type == Long.class) {
            return Long.parseLong(fieldValue);
        } else if (type == double.class || type == Double.class) {
            return Double.parseDouble(fieldValue);
        } else if (type == boolean.class || type == Boolean.class) {
            return Boolean.parseBoolean(fieldValue);
        }
        return fieldValue;
    }
}
