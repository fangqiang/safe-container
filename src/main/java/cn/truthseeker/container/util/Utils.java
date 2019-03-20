package cn.truthseeker.container.util;

import java.util.*;
import java.util.function.Predicate;
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
        } catch (InterruptedException ignored) { }
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

    public static List<String> splitOmitEmpty(String str, String regex) {
        return Arrays.stream(str.split(regex))
                .map(String::trim)
                .filter(Emptys::isNotEmpty)
                .collect(Collectors.toList());
    }

    public static <T> boolean anySatisfied(Iterable<T> objects, Predicate<T> predicate){
        for (T object : objects) {
            if(predicate.test(object)){
                return true;
            }
        }
        return false;
    }

    public static <T> boolean anySatisfied(T[] objects, Predicate<T> predicate){
        for (T object : objects) {
            if(predicate.test(object)){
                return true;
            }
        }
        return false;
    }
}
