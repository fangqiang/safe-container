package cn.truthseeker.container.safe.collection;

import cn.truthseeker.container.util.Assert;
import cn.truthseeker.container.util.Emptys;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/21
 */
public class Collections2 {

    public static <K, V> Map<K, V> toMap(Iterable<K> objects, Function<K, V> function) {
        Map<K, V> ret = new HashMap<>();
        for (K k : objects) {
            ret.put(k, function.apply(k));
        }
        return ret;
    }

    public static <K, V> Map<K, V> toMap(K[] objects, Function<K, V> function) {
        Map<K, V> ret = new HashMap<>();
        for (K k : objects) {
            ret.put(k, function.apply(k));
        }
        return ret;
    }

    public static <T> boolean anySatisfied(Iterable<T> objects, Predicate<T> predicate) {
        for (T object : objects) {
            if (predicate.test(object)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean anySatisfied(T[] objects, Predicate<T> predicate) {
        for (T object : objects) {
            if (predicate.test(object)) {
                return true;
            }
        }
        return false;
    }
}
