package logic.utils;

import models.mediaDB.Content;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/*
 * Inspirationsquelle: https://stackoverflow.com/questions/39411514/setting-value-without-a-setter-method-in-java-class
 */

public class MemberChanger {

    /*
     *
     *
     * WRITER FOR CONTENT
     *
     *
     */

    public static boolean writeBoolean(Content c, String member, boolean value) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        member_name.set(c, value);
        member_name.setAccessible(false);
        return true;
    }

    public static boolean writeByte(Content c, String member, byte value) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        member_name.set(c, value);
        member_name.setAccessible(false);
        return true;
    }

    public static boolean writeShort(Content c, String member, short value) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        member_name.set(c, value);
        member_name.setAccessible(false);
        return true;
    }

    public static boolean writeInteger(Content c, String member, int value) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        member_name.set(c, value);
        member_name.setAccessible(false);
        return true;
    }

    public static boolean writeLong(Content c, String member, long value) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        member_name.set(c, value);
        member_name.setAccessible(false);
        return true;
    }

    public static boolean writeFloat(Content c, String member, float value) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        member_name.set(c, value);
        member_name.setAccessible(false);
        return true;
    }

    public static boolean writeDouble(Content c, String member, double value) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        member_name.set(c, value);
        member_name.setAccessible(false);
        return true;
    }

    public static boolean writeBigDecimal(Content c, String member, BigDecimal value) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        member_name.set(c, value);
        member_name.setAccessible(false);
        return true;
    }

    public static boolean writeString(Content c, String member, String value) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        member_name.set(c, value);
        member_name.setAccessible(false);
        return true;
    }

    /*
     *
     *
     * READER FOR CONTENT
     *
     *
     */

    public static boolean readBoolean(Content c, String member) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        Boolean value = member_name.getBoolean(member);
        member_name.setAccessible(false);
        return value.booleanValue();
    }

    public static byte readByte(Content c, String member) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        Byte value = member_name.getByte(member);
        member_name.setAccessible(false);
        return value.byteValue();
    }

    public static short readShort(Content c, String member) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        Short value = member_name.getShort(member);
        member_name.setAccessible(false);
        return value.shortValue();
    }

    public static int readInteger(Content c, String member) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        Integer value = member_name.getInt(member);
        member_name.setAccessible(false);
        return value.intValue();
    }

    public static long readLong(Content c, String member) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        Long value = member_name.getLong(member);
        member_name.setAccessible(false);
        return value.longValue();
    }

    public static float readFloat(Content c, String member) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        Float value = member_name.getFloat(member);
        member_name.setAccessible(false);
        return value.floatValue();
    }

    public static double readDouble(Content c, String member) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        Double value = member_name.getDouble(member);
        member_name.setAccessible(false);
        return value.doubleValue();
    }

    public static BigDecimal readBigDecimal(Content c, String member) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        BigDecimal value = (BigDecimal) member_name.get(member);
        member_name.setAccessible(false);
        return value;
    }

    public static String readString(Content c, String member) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = c.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        String value = (String) member_name.get(member);
        member_name.setAccessible(false);
        return value;
    }
    /*
     *
     *
     * READER AND WRITER
     *
     *
     */

    public static boolean writeValue(Object o, String member, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = o.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        member_name.set(o, value);
        member_name.setAccessible(false);
        return true;
    }

    public static Object readValue(Object o, String member) throws NoSuchFieldException, IllegalAccessException {
        Field member_name = o.getClass().getDeclaredField(member);
        member_name.setAccessible(true);
        Object value = member_name.get(member);
        member_name.setAccessible(false);
        return value;
    }
}