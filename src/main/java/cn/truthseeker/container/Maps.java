package cn.truthseeker.container;

import java.util.*;
import java.util.function.Function;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class Maps {

    public static <K, V, RK> Map<RK, V> transformKeyType(Map<K, V> map, Function<K,RK> kFun) {
        Objects.requireNonNull(kFun);
        Map<RK,V> ret = new HashMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            ret.put(kFun.apply(entry.getKey()), entry.getValue());
        }
        return ret;
    }

    public static <K, V, RV> Map<K, RV> transformValueType(Map<K, V> map, Function<V,RV> vFun) {
        Objects.requireNonNull(vFun);
        Map<K,RV> ret = new HashMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            ret.put(entry.getKey(), vFun.apply(entry.getValue()));
        }
        return ret;
    }

    public static <K, V, RK, RV> Map<RK, RV> transformKeyValueType(Map<K, V> map, Function<K,RK> kFun, Function<V,RV> vFun) {
        Objects.requireNonNull(kFun);
        Objects.requireNonNull(vFun);
        Map<RK,RV> ret = new HashMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            ret.put(kFun.apply(entry.getKey()), vFun.apply(entry.getValue()));
        }
        return ret;
    }

    public static <K, V> Map<K, V> clearEmpty(Map<K, V> map) {
        map.entrySet().removeIf(entry -> {
            K key = entry.getKey();
            V value = entry.getValue();
            return  key == null || entry.getValue() == null ||
                    (key instanceof String && "".equals(((String) key).trim())) ||
                    (value instanceof String && "".equals(((String) value).trim()));
        });
        return map;
    }

    public static <K, V> Map<K, V> clearNull(Map<K, V> map) {
        map.entrySet().removeIf(entry -> entry == null || entry.getKey() == null || entry.getValue() == null);
        return map;
    }

    public static <K, V> void checkSafe(Map<K, V> map) {
        Objects.requireNonNull(map);
        for (Map.Entry<K, V> kvEntry : map.entrySet()) {
            Objects.requireNonNull(kvEntry);
            Objects.requireNonNull(kvEntry.getKey());
            Objects.requireNonNull(kvEntry.getValue());
        }
    }

    public static <K, V> boolean containsMap(Map<K, V> all, Map<K, V> part) {
        for (Map.Entry<K, V> entry : part.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            V value2 = all.get(key);
            if (!Objects.equals(value, value2)) {
                return false;
            }
        }
        return true;
    }

    public static <K, V> Map<K, V> getSubMap(Map<K, V> map, Collection<K> keys) {
        Map<K, V> ret = new HashMap<>();
        for (K key : keys) {
            ret.put(key, map.get(key));
        }
        return ret;
    }
}
