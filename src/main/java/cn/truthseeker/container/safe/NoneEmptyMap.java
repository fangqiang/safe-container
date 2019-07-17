package cn.truthseeker.container.safe;

import cn.truthseeker.util.Emptys;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 不能存放任何empty元素(key,value任意一个不能为empty)的Map
 * <p>
 * empty的定义如下：
 * 1. 不为null
 * 2. String类型的值必须包含非空白字符
 * 3. 集合类型至少包含一个元素
 * 3. 数组类型长度必须大于0
 *
 * @author: qiang.fang
 * @date: Created by on 19/4/7
 */
public class NoneEmptyMap<K, V> extends HashMap<K, V> implements CommonNoneEmptyMapOper<K, V> {
    public NoneEmptyMap() {
        super();
    }

    public NoneEmptyMap(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if (!(m instanceof NoneEmptyMap)) {
            m.forEach((k, v) -> Emptys.assertNoneEmpty(k, v));
        }

        super.putAll(m);
    }

    @Override
    public V put(K key, V value) {
        Emptys.assertNoneEmpty(key, value);
        return super.put(key, value);
    }

    /**
     * @Deprecated 推荐使用getNullable
     */
    @Override
    @Deprecated
    public V get(Object key) {
        return super.get(key);
    }

    // 构造工具

    /**
     * 快速构建方法
     */
    public static <K, V> NoneEmptyMap<K, V> of(K k1, V v1) {
        return Maps.of(k1, v1, NoneEmptyMap::new);
    }

    /**
     * 快速构建方法
     */
    public static <K, V> NoneEmptyMap<K, V> of(K k1, V v1, K k2, V v2) {
        return Maps.of(k1, v1, k2, v2, NoneEmptyMap::new);
    }

    /**
     * 快速构建方法
     */
    public static <K, V> NoneEmptyMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return Maps.of(k1, v1, k2, v2, k3, v3, NoneEmptyMap::new);
    }

    /**
     * 快速构建方法，从集合中抽取非empty元素
     */
    public static <K, V> NoneEmptyMap<K, V> extractFrom(Map<K, V> m) {
        NoneEmptyMap<K, V> ret = new NoneEmptyMap<>();
        ret.putAllOmitEmpty(m);
        return ret;
    }

    // 简化操作

    /**
     * Map<K,V> -> Map<RK,V>
     */
    public <RK> NoneEmptyMap<RK, V> mapByKey(Function<K, RK> kFun) {
        return Maps.mapByKey(this, kFun, NoneEmptyMap::new);
    }

    /**
     * Map<K,V> -> Map<K,RV>
     */
    public <RV> NoneEmptyMap<K, RV> mapByValue(Function<V, RV> vFun) {
        return Maps.mapByValue(this, vFun, NoneEmptyMap::new);
    }

    /**
     * Map<K,V> -> Map<RK,RV>
     */
    public <RK, RV> NoneEmptyMap<RK, RV> mapByKeyValue(Function<K, RK> kFun, Function<V, RV> vFun) {
        return Maps.mapByKeyValue(this, kFun, vFun, NoneEmptyMap::new);
    }

    /**
     * Map1 (按key过滤) -> Map2
     */
    public NoneEmptyMap<K, V> filterByKey(Predicate<K> kFun) {
        return Maps.filterByKey(this, kFun, NoneEmptyMap::new);
    }

    /**
     * Map1 (按value过滤) -> Map2
     */
    public NoneEmptyMap<K, V> filterByValue(Predicate<V> vFun) {
        return Maps.filterByValue(this, vFun, NoneEmptyMap::new);
    }

    /**
     * Map1 (按key和value过滤) -> Map2
     */
    public NoneEmptyMap<K, V> filterByKeyValue(BiPredicate<K, V> predicate) {
        return Maps.filterByKeyValue(this, predicate, NoneEmptyMap::new);
    }

    /**
     * 对keys中每个元素从map中提取出value，并放入一个新的map
     */
    public NoneEmptyMap<K, V> getSubMap(Collection<K> keys) {
        return Maps.getSubMap(this, keys, NoneEmptyMap::new);
    }
}
