package cn.truthseeker.container.safe.map;

import java.util.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class SafeTreeMap<K, V> extends TreeMap<K, V> implements SafeMap<K, V> {

    public SafeTreeMap() {
        super();
    }

    public SafeTreeMap(Comparator<? super K> comparator) {
        super(comparator);
    }

    public SafeTreeMap(Map<? extends K, ? extends V> m) {
        super(m);
        if (!(m instanceof SafeMap)) {
            Maps.checkSafe(m);
        }
    }

    public SafeTreeMap(SortedMap<K, ? extends V> m) {
        super(m);
        if (!(m instanceof SafeMap)) {
            Maps.checkSafe(m);
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        return super.put(key, value);
    }

    @Override
    public Optional<V> getNullable(Object key) {
        return Optional.ofNullable(super.get(key));
    }

    @Override
    @Deprecated
    public V get(Object key) {
        return super.get(key);
    }

    @Override
    public <T> T newInstance() {
        return (T) new SafeTreeMap();
    }
}
