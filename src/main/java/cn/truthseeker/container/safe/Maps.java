package cn.truthseeker.container.safe;

import cn.truthseeker.util.Assert;
import cn.truthseeker.util.Emptys;

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

    /**
     * @param map      操作的map
     * @param keys     获取的字段集合
     * @param supplier 提供一个容器，数据将放到这个容器中并返回
     * @param <K>      参数的key类型
     * @param <V>      参数的value类型
     * @param <T>      返回值类型
     * @return supplier函数的返回值
     */
    public static <K, V, T extends Map<K, V>> T getSubMap(Map<K, V> map, Collection<K> keys, Supplier<T> supplier) {
        T ret = supplier.get();
        for (K key : keys) {
            if (map.containsKey(key)) {
                V value = map.get(key);
                ret.put(key, value);
            }
        }
        return ret;
    }

    public static <K, V> Map<K, V> getSubMap(Map<K, V> map, Collection<K> keys) {
        return getSubMap(map, keys, HashMap::new);
    }

    /**
     * @param map      操作的map
     * @param kFun     key的映射方法
     * @param supplier 提供一个容器，数据将放到这个容器中并返回
     * @param <K>      参数的key类型
     * @param <V>      参数的value类型
     * @param <RK>     返回值的key类型
     * @param <T>      返回值类型
     * @return supplier函数的返回值
     */
    public static <K, V, RK, T extends Map<RK, V>> T mapKey(Map<K, V> map, Function<K, RK> kFun, Supplier<T> supplier) {
        T ret = supplier.get();
        map.forEach((k, v) -> ret.put(kFun.apply(k), v));
        return ret;
    }

    public static <K, V, RK> Map<RK, V> mapKey(Map<K, V> map, Function<K, RK> kFun) {
        return mapKey(map, kFun, HashMap::new);
    }

    /**
     * @param map      操作的map
     * @param vFun     value的映射方法
     * @param supplier 提供一个容器，数据将放到这个容器中并返回
     * @param <K>      参数的key类型
     * @param <V>      参数的value类型
     * @param <RV>     返回值的value类型
     * @param <T>      返回值类型
     * @return supplier函数的返回值
     */
    public static <K, V, RV, T extends Map<K, RV>> T mapValue(Map<K, V> map, Function<V, RV> vFun, Supplier<T> supplier) {
        T ret = supplier.get();
        map.forEach((k, v) -> ret.put(k, vFun.apply(v)));
        return ret;
    }

    public static <K, V, RV> Map<K, RV> mapValue(Map<K, V> map, Function<V, RV> vFun) {
        return mapValue(map, vFun, HashMap::new);
    }

    /**
     * @param map      操作的map
     * @param kFun     key的映射方法
     * @param vFun     value的映射方法
     * @param supplier 提供一个容器，数据将放到这个容器中并返回
     * @param <K>      参数的key类型
     * @param <V>      参数的value类型
     * @param <RK>     返回值的key类型
     * @param <RV>     返回值的value类型
     * @param <T>      返回值类型
     * @return supplier函数的返回值
     */
    public static <K, V, RK, RV, T extends Map<RK, RV>> T mapKeyValue(Map<K, V> map, Function<K, RK> kFun, Function<V, RV> vFun, Supplier<T> supplier) {
        T ret = supplier.get();
        map.forEach((k, v) -> ret.put(kFun.apply(k), vFun.apply(v)));
        return ret;
    }

    public static <K, V, RK, RV> Map<RK, RV> mapKeyValue(Map<K, V> map, Function<K, RK> kFun, Function<V, RV> vFun) {
        return mapKeyValue(map, kFun, vFun, HashMap::new);
    }

    /**
     * @param map      操作的map
     * @param kFun     key的过滤条件
     * @param supplier 提供一个容器，数据将放到这个容器中并返回
     * @param <K>      参数的key类型
     * @param <V>      参数的value类型
     * @param <T>      返回值类型
     * @return supplier函数的返回值
     */
    public static <K, V, T extends Map<K, V>> T filterByKey(Map<K, V> map, Predicate<K> kFun, Supplier<T> supplier) {
        T ret = supplier.get();
        map.forEach((key, value) -> {
            if (kFun.test(key)) {
                ret.put(key, value);
            }
        });
        return ret;
    }

    public static <K, V> Map<K, V> filterByKey(Map<K, V> map, Predicate<K> kFun) {
        return filterByKey(map, kFun, HashMap::new);
    }

    /**
     * @param map      操作的map
     * @param vFun     value的过滤条件
     * @param supplier 提供一个容器，数据将放到这个容器中并返回
     * @param <K>      参数的key类型
     * @param <V>      参数的value类型
     * @param <T>      返回值类型
     * @return supplier函数的返回值
     */
    public static <K, V, T extends Map<K, V>> T filterByValue(Map<K, V> map, Predicate<V> vFun, Supplier<T> supplier) {
        T ret = supplier.get();
        map.forEach((key, value) -> {
            if (vFun.test(value)) {
                ret.put(key, value);
            }
        });
        return ret;
    }

    public static <K, V> Map<K, V> filterByValue(Map<K, V> map, Predicate<V> vFun) {
        return filterByValue(map, vFun, HashMap::new);
    }

    /**
     * @param map       操作的map
     * @param predicate key,value的过滤条件
     * @param supplier  提供一个容器，数据将放到这个容器中并返回
     * @param <K>       参数的key类型
     * @param <V>       参数的value类型
     * @param <T>       返回值类型
     * @return supplier函数的返回值
     */
    public static <K, V, T extends Map<K, V>> T filterByKeyValue(Map<K, V> map, BiPredicate<K, V> predicate, Supplier<T> supplier) {
        T ret = supplier.get();
        map.forEach((key, value) -> {
            if (predicate.test(key, value)) {
                ret.put(key, value);
            }
        });
        return ret;
    }

    public static <K, V> Map<K, V> filterByKeyValue(Map<K, V> map, BiPredicate<K, V> predicate) {
        return filterByKeyValue(map, predicate, HashMap::new);
    }

    public static <K, V> Map<K, V> of(K k1, V v1) {
        return of(k1, v1, HashMap::new);
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
        return of(k1, v1, k2, v2, HashMap::new);
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return of(k1, v1, k2, v2, k3, v3, HashMap::new);
    }

    public static <K, V, T extends Map<K, V>> T of(K k1, V v1, Supplier<T> supplier) {
        T ret = supplier.get();
        ret.put(k1, v1);
        return ret;
    }

    public static <K, V, T extends Map<K, V>> T of(K k1, V v1, K k2, V v2, Supplier<T> supplier) {
        T ret = supplier.get();
        ret.put(k1, v1);
        ret.put(k2, v2);
        return ret;
    }

    public static <K, V, T extends Map<K, V>> T of(K k1, V v1, K k2, V v2, K k3, V v3, Supplier<T> supplier) {
        T ret = supplier.get();
        ret.put(k1, v1);
        ret.put(k2, v2);
        ret.put(k3, v3);
        return ret;
    }

    /**
     * 删除key或者value为空的键值对
     */
    public static <K, V> Map<K, V> clearEmpty(Map<K, V> map) {
        map.entrySet().removeIf(entry -> Emptys.isAnyEmpty(entry.getKey(), entry.getValue()));
        return map;
    }

    public static <K, V> Map<K, V> clearNull(Map<K, V> map) {
        map.entrySet().removeIf(entry -> Emptys.isAnyNull(entry.getKey(), entry.getValue()));
        return map;
    }

    public static <K, V, T extends Map<K, V>> T listToMap(List<K> list, Function<K, V> function, Supplier<T> supplier) {
        T t = supplier.get();
        for (K k : list) {
            t.put(k, function.apply(k));
        }
        return t;
    }

    public static <K, V> Map<K, V> listToMap(List<K> list, Function<K, V> function) {
        return listToMap(list, function, HashMap::new);
    }

    /**
     * 将key的集合，value的集合映射成一个map
     *
     * @param keys     key集合
     * @param values   value集合
     * @param supplier 提供一个容器，数据将放到这个容器中并返回
     * @param <K>      参数的key类型
     * @param <V>      参数的value类型
     * @param <T>      返回值类型
     * @return supplier函数的返回值
     */
    public static <K, V, T extends Map<K, V>> T zip(List<K> keys, List<V> values, Supplier<T> supplier) {
        Assert.isTrue(keys.size() == values.size(), "key,value size not match");
        T ret = supplier.get();
        for (int i = 0; i < keys.size(); i++) {
            ret.put(keys.get(i), values.get(i));
        }
        return ret;
    }

    public static <K, V> Map<K, V> zip(List<K> keys, List<V> values) {
        return zip(keys, values, HashMap::new);
    }

    public static <K, V> V getOrCreate(Map<K, V> map, K k, Supplier<V> supplier) {
        V v = map.get(k);
        if (v == null) {
            v = supplier.get();
            map.put(k, v);
        }
        return v;
    }

    public static <K, V> void assertTrue(Map<K, V> map, BiPredicate<K, V> biPredicate, String errMsg) {
        map.forEach((k, v) -> Assert.isTrue(biPredicate.test(k, v), errMsg));
    }
}