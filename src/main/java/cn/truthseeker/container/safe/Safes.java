package cn.truthseeker.container.safe;

import cn.truthseeker.container.safe.collection.*;
import cn.truthseeker.container.safe.map.CommonMaps;
import cn.truthseeker.container.safe.map.SafeHashMap;
import cn.truthseeker.container.safe.map.SafeMap;
import cn.truthseeker.container.safe.map.SafeTreeMap;
import cn.truthseeker.container.util.Emptys;

import java.util.Arrays;
import java.util.Collection;
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

    public static <K, V> SafeMap<K, V> newSafeMap() {
        return new SafeHashMap();
    }

    public static <K, V> SafeMap<K, V> newSafeMap(K k1, V v1) {
        return CommonMaps.of(k1, v1, SafeHashMap::new);
    }

    public static <K, V> SafeMap<K, V> newSafeMap(K k1, V v1, K k2, V v2) {
        return CommonMaps.of(k1, v1, k2, v2, SafeHashMap::new);
    }

    public static <K, V> SafeMap<K, V> newSafeMap(K k1, V v1, K k2, V v2, K k3, V v3) {
        return CommonMaps.of(k1, v1, k2, v2, k3, v3, SafeHashMap::new);
    }

    public static <K, V> SafeMap<K, V> newSafeSortMap() {
        return new SafeTreeMap();
    }

    public static <K, V> SafeMap<K, V> newSafeSortMap(K k1, V v1) {
        return CommonMaps.of(k1, v1, SafeTreeMap::new);
    }

    public static <K, V> SafeMap<K, V> newSafeSortMap(K k1, V v1, K k2, V v2) {
        return CommonMaps.of(k1, v1, k2, v2, SafeTreeMap::new);
    }

    public static <K, V> SafeMap<K, V> newSafeSortMap(K k1, V v1, K k2, V v2, K k3, V v3) {
        return CommonMaps.of(k1, v1, k2, v2, k3, v3, SafeTreeMap::new);
    }

    /**
     * 包装成SafeMap，并将其中的null值（key或value为null）删除
     */
    public static <K, V> SafeMap<K, V> newSafeMapIgnoreNull(Map<K, V> map) {
        return CommonMaps.filterByKeyValue(map, (k, v) -> Emptys.isNoneNull(k, v), SafeHashMap::new);
    }

    /**
     * 包装成SafeMap，并将其中的null值（key或value为null）删除
     */
    public static <K, V> SafeMap<K, V> newSafeSortMapIgnoreNull(Map<K, V> map) {
        return CommonMaps.filterByKeyValue(map, (k, v) -> Emptys.isNoneNull(k, v), SafeTreeMap::new);
    }

    public static <E> SafeList<E> newSafeList() {
        return new SafeArrayList<>();
    }

    public static <E> SafeList<E> newSafeLinkedList() {
        return new SafeLinkedList<>();
    }

    public static <E> SafeList<E> newSafeListIgnoreNull(E[] list) {
        return newSafeListIgnoreNull(Arrays.asList(list));
    }

    /**
     * 包装成SafeList，并将其中的null值（key或value为null）删除
     */
    public static <E> SafeList<E> newSafeListIgnoreNull(Collection<E> list) {
        return list.stream().filter(Objects::nonNull).collect(Collectors.toCollection(SafeArrayList::new));
    }

    public static <E> SafeList<E> newSafeLinkedListIgnoreNull(E[] list) {
        return newSafeLinkedListIgnoreNull(Arrays.asList(list));
    }

    /**
     * 包装成SafeList，并将其中的null值（key或value为null）删除
     */
    public static <E> SafeList<E> newSafeLinkedListIgnoreNull(Collection<E> list) {
        return list.stream().filter(Objects::nonNull).collect(Collectors.toCollection(SafeLinkedList::new));
    }

    public static <E> SafeSet<E> newSafeSet() {
        return new SafeHashSet<>();
    }

    public static <E> SafeSet<E> newSafeSortSet() {
        return new SafeTreeSet<>();
    }

    public static <E> SafeSet<E> newSafeSetIgnoreNull(E[] list) {
        return newSafeSetIgnoreNull(Arrays.asList(list));
    }

    /**
     * 包装成SafeSet，并将其中的null值（key或value为null）删除
     */
    public static <E> SafeSet<E> newSafeSetIgnoreNull(Collection<E> list) {
        return list.stream().filter(Objects::nonNull).collect(Collectors.toCollection(SafeHashSet::new));
    }

    public static <E> SafeSet<E> newSafeSortSetIgnoreNull(E[] list) {
        return newSafeSortSetIgnoreNull(Arrays.asList(list));
    }

    /**
     * 包装成SafeSet，并将其中的null值（key或value为null）删除
     */
    public static <E> SafeSet<E> newSafeSortSetIgnoreNull(Collection<E> list) {
        return list.stream().filter(Objects::nonNull).collect(Collectors.toCollection(SafeTreeSet::new));
    }
}
