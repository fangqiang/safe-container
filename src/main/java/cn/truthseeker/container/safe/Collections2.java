package cn.truthseeker.container.safe;

import cn.truthseeker.util.Assert;
import cn.truthseeker.util.Emptys;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/21
 */
public class Collections2 {

    /**
     * 删除集合中empty的元素
     */
    public static <E> void clearEmpty(Collection<E> e) {
        e.removeIf(Emptys::isEmpty);
    }

    /**
     * 删除集合中null元素
     */
    public static <E> void clearNull(Collection<E> e) {
        e.removeIf(Objects::isNull);
    }

    /**
     * 快速构建一个list
     */
    public static <E> List<E> ofList(E... e) {
        return of(ArrayList::new, e);
    }

    /**
     * 快速构建一个set
     */
    public static <E> Set<E> ofSet(E... e) {
        return of(HashSet::new, e);
    }

    /**
     * 快速构建一个集合
     */
    public static <E, T extends Collection<E>> T of(Supplier<T> supplier, E... e) {
        T t = supplier.get();
        for (E e1 : e) {
            t.add(e1);
        }
        return t;
    }

    /**
     * 快速构建一个集合
     */
    public static <E, T extends Collection<E>> T of(Supplier<T> supplier, Iterable<E> e) {
        T t = supplier.get();
        for (E e1 : e) {
            t.add(e1);
        }
        return t;
    }

    /**
     * 集合1 -> 集合2
     */
    public static <E, V> List<V> map(Function<E, V> mapFun, E... e) {
        List<V> list = new ArrayList<>(e.length);
        for (E e1 : e) {
            list.add(mapFun.apply(e1));
        }
        return list;
    }

    /**
     * 集合1 -> 集合2
     */
    public static <E, V> List<V> map(Function<E, V> mapFun, Collection<E> e) {
        List<V> list = new ArrayList<>(e.size());
        for (E e1 : e) {
            list.add(mapFun.apply(e1));
        }
        return list;
    }

    /**
     * Iterable<K> -> HashMap<K, V>
     *
     * @param objects 数据源
     * @param vFun 根据K成V
     */
    public static <K, V> Map<K, V> toHashMap(Iterable<K> objects, Function<K, V> vFun) {
        return toMap(objects, vFun, HashMap::new);
    }

    /**
     * Iterable<K> -> Map<K, V>
     *
     * @param objects 数据源
     * @param vFun 根据K成V
     * @param supplier 目标map
     */
    public static <K, V, T extends Map<K, V>> T toMap(Iterable<K> objects, Function<K, V> vFun, Supplier<T> supplier) {
        T t = supplier.get();
        for (K k : objects) {
            t.put(k, vFun.apply(k));
        }
        return t;
    }


    /**
     * Iterable<K> -> HashMap<RK, RV>
     *
     * @param objects 数据源
     * @param kFun 根据K生成RK
     * @param vFun 根据K生成RV
     */
    public static <K, RK, RV> Map<RK, RV> toHashMap(Iterable<K> objects, Function<K, RK> kFun, Function<K, RV> vFun) {
        return toMap(objects, kFun, vFun, HashMap::new);
    }

    /**
     * Iterable<K> -> Map<RK, RV>
     *
     * @param objects 数据源
     * @param kFun 根据K生成RK
     * @param vFun 根据K生成RV
     * @param supplier 目标map
     */
    public static <K, RK, RV, T extends Map<RK, RV>> T toMap(Iterable<K> objects, Function<K, RK> kFun, Function<K, RV> vFun, Supplier<T> supplier) {
        T t = supplier.get();
        for (K k : objects) {
            t.put(kFun.apply(k), vFun.apply(k));
        }
        return t;
    }

    /**
     * K[] -> HashMap<K, V>
     *
     * @param objects 数据源
     * @param vFun 根据K成V
     */
    public static <K, V> Map<K, V> toHashMap(K[] objects, Function<K, V> vFun) {
        return toMap(objects, vFun, HashMap::new);
    }

    /**
     * K[] -> Map<K, V>
     *
     * @param objects 数据源
     * @param vFun 根据K成V
     * @param supplier 目标map
     */
    public static <K, V, T extends Map<K, V>> T toMap(K[] objects, Function<K, V> vFun, Supplier<T> supplier) {
        T t = supplier.get();
        for (K k : objects) {
            t.put(k, vFun.apply(k));
        }
        return t;
    }

    /**
     * K[] -> HashMap<RK, RV>
     *
     * @param objects 数据源
     * @param kFun 根据K生成RK
     * @param vFun 根据K生成RV
     */
    public static <K, RK, RV> Map<RK, RV> toHashMap(K[] objects, Function<K, RK> kFun, Function<K, RV> vFun) {
        return toMap(objects, kFun, vFun, HashMap::new);
    }

    /**
     * K[] -> Map<RK, RV>
     *
     * @param objects 数据源
     * @param kFun 根据K生成RK
     * @param vFun 根据K生成RV
     * @param supplier 目标map
     */
    public static <K, RK, RV, T extends Map<RK, RV>> T toMap(K[] objects, Function<K, RK> kFun, Function<K, RV> vFun, Supplier<T> supplier) {
        T t = supplier.get();
        for (K k : objects) {
            t.put(kFun.apply(k), vFun.apply(k));
        }
        return t;
    }

    /**
     * 集合中至少有一个元素满足条件则返回true，否则返回false
     */
    public static <T> boolean anySatisfied(Iterable<T> objects, Predicate<T> predicate) {
        for (T object : objects) {
            if (predicate.test(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 集合中至少有一个元素满足条件则返回true，否则返回false
     */
    public static <T> boolean anySatisfied(T[] objects, Predicate<T> predicate) {
        for (T object : objects) {
            if (predicate.test(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验集合中每个元素是否满足条件，否则抛出异常
     */
    public static <E> void assertTrue(Iterable<E> objects, Predicate<E> predicate, String errMsg) {
        for (E e : objects) {
            Assert.isTrue(predicate.test(e), errMsg);
        }
    }

    /**
     * 校验集合中每个元素是否满足条件，否则抛出异常
     */
    public static <E> void assertTrue(E[] objects, Predicate<E> predicate, String errMsg) {
        for (E e : objects) {
            Assert.isTrue(predicate.test(e), errMsg);
        }
    }
}