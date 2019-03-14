package cn.truthseeker.container.safe;

import cn.truthseeker.container.Maps;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public final class SafeMaps{

    public static <K, V, RK> SafeMap<RK, V> transformKeyType(SafeMap<K, V> map, Function<K,RK> kFun) {
        Objects.requireNonNull(kFun);
        SafeMap<RK,V> ret = new SafeMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            ret.put(kFun.apply(entry.getKey()), entry.getValue());
        }
        return ret;
    }

    public static <K, V, RV> SafeMap<K, RV> transformValueType(SafeMap<K, V> map, Function<V,RV> vFun) {
        Objects.requireNonNull(vFun);
        SafeMap<K,RV> ret = new SafeMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            ret.put(entry.getKey(), vFun.apply(entry.getValue()));
        }
        return ret;
    }

    public static <K, V, RK, RV> SafeMap<RK, RV> transformKeyValueType(SafeMap<K, V> map, Function<K,RK> kFun, Function<V,RV> vFun) {
        Objects.requireNonNull(kFun);
        Objects.requireNonNull(vFun);
        SafeMap<RK,RV> ret = new SafeMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            ret.put(kFun.apply(entry.getKey()), vFun.apply(entry.getValue()));
        }
        return ret;
    }

    public static <K, V> SafeMap<K, V> clearNull(Map<K, V> map) {
        map.entrySet().removeIf(entry -> entry == null || entry.getKey() == null || entry.getValue() == null);
        return map instanceof SafeMap ? (SafeMap<K, V>) map : new SafeMap<>(map);
    }

    public static <K, V> SafeMap<K, V> clearEmpty(Map<K, V> map) {
        map.entrySet().removeIf(entry -> {
            K key = entry.getKey();
            V value = entry.getValue();
            return  key == null || entry.getValue() == null ||
                    (key instanceof String && "".equals(((String) key).trim())) ||
                    (value instanceof String && "".equals(((String) value).trim()));
        });
        return map instanceof SafeMap ? (SafeMap<K, V>) map : new SafeMap<>(map);
    }

    public static <K, V> SafeMap<K, V> getSubMap(SafeMap<K, V> map, Collection<K> keys) {
        SafeMap<K, V> ret = new SafeMap<>();
        for (K key : keys) {
            ret.put(key, map.get(key));
        }
        return ret;
    }
}
