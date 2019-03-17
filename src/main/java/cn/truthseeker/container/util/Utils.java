package cn.truthseeker.container.util;

import java.util.*;
import java.util.stream.Collectors;

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

    public static <K, V> void mapAddList(Map<K, List<V>> map, K key, V item) {
        List<V> val = map.get(key);

        if (val != null) {
            val.add(item);
        } else {
            List<V> l = new ArrayList<>();
            l.add(item);
            map.put(key, l);
        }
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

    public static boolean oneNull(Object a, Object b) {
        return a == null ^ b == null;
    }

    public static boolean oneEmpty(String a, String b) {
        return isEmpty(a) ^ isEmpty(b);
    }

    public static List<String> splitOmitEmpty(String str, String regex) {
        return Arrays.stream(str.split(regex))
                .map(String::trim)
                .filter(Utils::isNotEmpty)
                .collect(Collectors.toList());
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return "".equals(((String) object).trim());
        }
        if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        }
        if (object instanceof Map) {
            return ((Map) object).isEmpty();
        }
        if (object instanceof Object[]) {
            return ((Object[]) object).length == 0;
        }

        if (object instanceof byte[]) {
            return ((byte[]) object).length == 0;
        } else if (object instanceof char[]) {
            return ((char[]) object).length == 0;
        } else if (object instanceof int[]) {
            return ((int[]) object).length == 0;
        } else if (object instanceof boolean[]) {
            return ((boolean[]) object).length == 0;
        } else if (object instanceof long[]) {
            return ((long[]) object).length == 0;
        } else if (object instanceof double[]) {
            return ((double[]) object).length == 0;
        } else if (object instanceof float[]) {
            return ((float[]) object).length == 0;
        } else if (object instanceof short[]) {
            return ((short[]) object).length == 0;
        }

        return false;
    }
}
