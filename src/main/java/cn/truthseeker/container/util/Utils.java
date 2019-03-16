package cn.truthseeker.container.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/15
 */
public class Utils {

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    public static <K,V> void mapAddList(Map<K, List<V>> map, K key, V item) {
        List<V> val = map.get(key);

        if (val != null) {
            val.add(item);
        } else {
            List<V> l = new ArrayList<>();
            l.add(item);
            map.put(key, l);
        }
    }

    public static boolean isNotEmpty(String v) {
        return !isEmpty(v);
    }

    public static boolean isEmpty(String v) {
        return v == null || "".equals(v.trim());
    }

    public static boolean isNotEmpty(byte[] v) {
        return !isEmpty(v);
    }

    public static boolean isEmpty(byte[] v) {
        return v == null || v.length == 0;
    }

    public static boolean isNotEmpty(short[] v) {
        return !isEmpty(v);
    }

    public static boolean isEmpty(short[] v) {
        return v == null || v.length == 0;
    }

    public static boolean isNotEmpty(int[] v) {
        return !isEmpty(v);
    }

    public static boolean isEmpty(int[] v) {
        return v == null || v.length == 0;
    }

    public static boolean isNotEmpty(long[] v) {
        return !isEmpty(v);
    }

    public static boolean isEmpty(long[] v) {
        return v == null || v.length == 0;
    }

    public static boolean isNotEmpty(double[] v) {
        return !isEmpty(v);
    }

    public static boolean isEmpty(double[] v) {
        return v == null || v.length == 0;
    }

    public static boolean isNotEmpty(float[] v) {
        return !isEmpty(v);
    }

    public static boolean isEmpty(float[] v) {
        return v == null || v.length == 0;
    }

    public static boolean isNotEmpty(boolean[] v) {
        return !isEmpty(v);
    }

    public static boolean isEmpty(boolean[] v) {
        return v == null || v.length == 0;
    }

    public static boolean isNotEmpty(char[] v) {
        return !isEmpty(v);
    }

    public static boolean isEmpty(char[] v) {
        return v == null || v.length == 0;
    }

    public static boolean isNotEmpty(Object[] v) {
        return !isEmpty(v);
    }

    public static boolean isEmpty(Object[] v) {
        return v == null || v.length == 0;
    }

    public static boolean isNotEmpty(Collection v) {
        return !isEmpty(v);
    }

    public static boolean isEmpty(Collection v) {
        return v == null || v.isEmpty();
    }

    public static boolean isNotEmpty(Map v) {
        return !isEmpty(v);
    }

    public static boolean isEmpty(Map v) {
        return v == null || v.isEmpty();
    }

    public static boolean isAnyNull(Object... objects) {
        return !isNoneNull(objects);
    }

    public static boolean isNoneNull(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnyEmpty(Object... objects) {
        return !isNoneEmpty(objects);
    }

    public static boolean isNoneEmpty(Object... objects) {
        for (Object object : objects) {
            if (isEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    public static boolean isEmpty(Object object) {
        return object == null || (object instanceof String && "".equals(((String) object).trim()));
    }
}
