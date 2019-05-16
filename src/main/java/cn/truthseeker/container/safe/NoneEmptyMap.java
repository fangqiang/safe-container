package cn.truthseeker.container.safe;

import cn.truthseeker.util.Emptys;

import javax.annotation.CheckForNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
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
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            Emptys.assertNotEmpty(entry.getKey());
            Emptys.assertNotEmpty(entry.getValue());
        }

        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V put(K key, V value) {
        Emptys.assertNotEmpty(key);
        Emptys.assertNotEmpty(value);
        return super.put(key, value);
    }

    @Override
    @CheckForNull
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
     * 快速构建方法，忽略empty元素
     */
    public static <K, V> NoneEmptyMap<K, V> ofIgnoreEmpty(Map<K, V> m) {
        NoneEmptyMap<K, V> ret = new NoneEmptyMap<>();
        ret.putAllIfNotEmpty(m);
        return ret;
    }

    // 简化操作

    /**
     * Map<K,V> -> Map<RK,V>
     */
    public <RK> NoneEmptyMap<RK, V> mapKey(Function<K, RK> kFun) {
        return Maps.mapKey(this, kFun, NoneEmptyMap::new);
    }

    /**
     * Map<K,V> -> Map<K,RV>
     */
    public <RV> NoneEmptyMap<K, RV> mapValue(Function<V, RV> vFun) {
        return Maps.mapValue(this, vFun, NoneEmptyMap::new);
    }

    /**
     * Map<K,V> -> Map<RK,RV>
     */
    public <RK, RV> NoneEmptyMap<RK, RV> mapKeyValue(Function<K, RK> kFun, Function<V, RV> vFun) {
        return Maps.mapKeyValue(this, kFun, vFun, NoneEmptyMap::new);
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
