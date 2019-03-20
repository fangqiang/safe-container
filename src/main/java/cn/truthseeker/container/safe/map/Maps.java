package cn.truthseeker.container.safe.map;

import cn.truthseeker.container.util.Assert;
import cn.truthseeker.container.util.Emptys;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public final class Maps {

    public static <K, V, RK> Map<RK, V> mapKey(Map<K, V> map, Function<K, RK> kFun) {
        return CommonMaps.mapKey(map, kFun, HashMap::new);
    }

    public static <K, V, RV> Map<K, RV> mapValue(Map<K, V> map, Function<V, RV> vFun) {
        return CommonMaps.mapValue(map, vFun, HashMap::new);
    }

    public static <K, V, RK, RV> Map<RK, RV> mapKeyValue(Map<K, V> map, Function<K, RK> kFun, Function<V, RV> vFun) {
        return CommonMaps.mapKeyValue(map, kFun, vFun, HashMap::new);
    }

    public static <K, V> Map<K, V> filterByKey(Map<K, V> map, Predicate<K> predicate) {
        return CommonMaps.filterByKey(map, predicate, HashMap::new);
    }

    public static <K, V> Map<K, V> filterByValue(Map<K, V> map, Predicate<V> predicate) {
        return CommonMaps.filterByValue(map, predicate, HashMap::new);
    }

    public static <K, V> Map<K, V> filterByKeyValue(Map<K, V> map, BiPredicate<K, V> predicate) {
        return CommonMaps.filterByKeyValue(map, predicate, HashMap::new);
    }

    public static <K, V> Map<K, V> getSubMap(Map<K, V> map, Collection<K> keys) {
        return CommonMaps.getSubMap(map, keys, HashMap::new);
    }

    /**
     * 校验map是否包含null
     */
    public static <K, V> void checkSafe(Map<K, V> map) {
        Objects.requireNonNull(map);
        for (Map.Entry<K, V> kvEntry : map.entrySet()) {
            Objects.requireNonNull(kvEntry);
            Objects.requireNonNull(kvEntry.getKey());
            Objects.requireNonNull(kvEntry.getValue());
        }
    }

    /**
     * 校验一个mapA是否完全包含mapB
     *
     * @param all  mapA
     * @param part mapB
     * @param <K>  参数的key类型
     * @param <V>  参数的value类型
     * @return 完全包含，返回true,否则返回false
     */
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

    public static <K, V> Map<K, V> of(K k1, V v1) {
        return CommonMaps.of(k1, v1, HashMap::new);
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
        return CommonMaps.of(k1, v1, k2, v2, HashMap::new);
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return CommonMaps.of(k1, v1, k2, v2, k3, v3, HashMap::new);
    }

    public static <K, V> Map<K, V> clearEmpty(Map<K, V> map) {
        map.entrySet().removeIf(entry -> ! Emptys.isNoneEmpty(entry.getKey(),entry.getValue()));
        return map;
    }

    public static <K, V> Map<K, V> clearNull(Map<K, V> map) {
        map.entrySet().removeIf(entry -> ! Emptys.isNoneNull(entry.getKey(),entry.getValue()));
        return map;
    }

    public static <K,V> Map<K,V>listToMap(List<K> list, Function<K,V> function){
        HashMap ret = new HashMap();
        for (K k : list) {
            ret.put(k, function.apply(k));
        }
        return ret;
    }
}