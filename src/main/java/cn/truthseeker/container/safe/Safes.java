package cn.truthseeker.container.safe;

import cn.truthseeker.container.safe.list.SafeArrayList;
import cn.truthseeker.container.safe.list.SafeLinkedList;
import cn.truthseeker.container.safe.list.SafeList;
import cn.truthseeker.container.safe.map.Maps;
import cn.truthseeker.container.safe.map.SafeHashMap;
import cn.truthseeker.container.safe.map.SafeMap;
import cn.truthseeker.container.safe.map.SafeTreeMap;
import cn.truthseeker.container.safe.set.SafeHashSet;
import cn.truthseeker.container.safe.set.SafeSet;
import cn.truthseeker.container.safe.set.SafeTreeSet;
import cn.truthseeker.container.util.Emptys;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/15
 */
public final class Safes {

    public static <K,V> SafeMap<K,V> newSafeMap() {
        return new SafeHashMap();
    }

    public static <K, V> SafeMap<K, V> newSafeMap(K k1, V v1) {
        return Maps.of(k1,v1,SafeHashMap::new);
    }

    public static <K, V> SafeMap<K, V> newSafeMap(K k1, V v1, K k2, V v2) {
        return Maps.of(k1,v1,k2,v2,SafeHashMap::new);
    }

    public static <K, V> SafeMap<K, V> newSafeMap(K k1, V v1,K k2, V v2,K k3, V v3) {
        return Maps.of(k1,v1,k2,v2,k3,v3,SafeHashMap::new);
    }

    public static <K,V> SafeMap<K,V> newSafeSortMap() {
        return new SafeTreeMap();
    }

    public static <K, V> SafeMap<K, V> newSafeSortMap(K k1, V v1) {
        return Maps.of(k1,v1,SafeTreeMap::new);
    }

    public static <K, V> SafeMap<K, V> newSafeSortMap(K k1, V v1, K k2, V v2) {
        return Maps.of(k1,v1,k2,v2,SafeTreeMap::new);
    }

    public static <K, V> SafeMap<K, V> newSafeSortMap(K k1, V v1,K k2, V v2,K k3, V v3) {
        return Maps.of(k1,v1,k2,v2,k3,v3,SafeTreeMap::new);
    }

    /**
     * 包装成SafeMap，并将其中的null值（key或value为null）删除
     */
    public static <K, V> SafeMap<K, V> newSafeMapOmitNull(Map<K, V> map) {
        return Maps.filterByKeyValue(map, (k, v) -> Emptys.isNoneNull(k, v), SafeHashMap::new);
    }

    /**
     * 包装成SafeMap，并将其中的null值（key或value为null）删除
     */
    public static <K, V> SafeMap<K, V> newSafeSortMapOmitNull(Map<K, V> map) {
        return Maps.filterByKeyValue(map, (k, v) -> Emptys.isNoneNull(k, v), SafeTreeMap::new);
    }

    public static <E> SafeList<E> newSafeList() {
        return new SafeArrayList<>();
    }

    public static <E> SafeList<E> newSafeLinkedList() {
        return new SafeLinkedList<>();
    }

    /**
     * 包装成SafeList，并将其中的null值（key或value为null）删除
     */
    public static <E> SafeList<E> newSafeListOmitNull(List<E> list) {
        return list.stream().filter(Objects::nonNull).collect(Collectors.toCollection(SafeArrayList::new));
    }

    /**
     * 包装成SafeList，并将其中的null值（key或value为null）删除
     */
    public static <E> SafeList<E> newSafeLinkedListOmitNull(List<E> list) {
        return list.stream().filter(Objects::nonNull).collect(Collectors.toCollection(SafeLinkedList::new));
    }

    public static <E> SafeSet<E> newSafeSet() {
        return new SafeHashSet<>();
    }

    public static <E> SafeSet<E> newSafeSortSet() {
        return new SafeTreeSet<>();
    }

    /**
     * 包装成SafeSet，并将其中的null值（key或value为null）删除
     */
    public static <E> SafeSet<E> newSafeSetOmitNull(List<E> list) {
        return list.stream().filter(Objects::nonNull).collect(Collectors.toCollection(SafeHashSet::new));
    }

    /**
     * 包装成SafeSet，并将其中的null值（key或value为null）删除
     */
    public static <E> SafeSet<E> newSafeSortSetOmitNull(List<E> list) {
        return list.stream().filter(Objects::nonNull).collect(Collectors.toCollection(SafeTreeSet::new));
    }
}
