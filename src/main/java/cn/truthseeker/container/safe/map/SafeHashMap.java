package cn.truthseeker.container.safe.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class SafeHashMap<K, V> extends HashMap<K, V> implements SafeMap<K, V> {
    public SafeHashMap() {
        super();
    }

    public SafeHashMap(Map<? extends K, ? extends V> m) {
        super(m);
        if (!(m instanceof SafeMap)) {
            Maps.checkSafe(m);
        }
    }

    public SafeHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public SafeHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
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
    public Optional<V> getNullable(K key) {
        return Optional.ofNullable(super.get(key));
    }

    @Override
    public <T> T newInstance() {
        return (T) new SafeHashMap();
    }
}
