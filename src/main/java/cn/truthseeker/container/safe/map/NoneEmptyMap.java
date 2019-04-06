package cn.truthseeker.container.safe.map;

import cn.truthseeker.container.util.Assert;
import cn.truthseeker.container.util.Emptys;

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
public class NoneEmptyMap<K, V> extends HashMap<K, V> implements SafeMap<K, V> {
    public NoneEmptyMap() {
        super();
    }

    public NoneEmptyMap(Map<? extends K, ? extends V> m) {
        super();
        m.forEach(this::put);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V put(K key, V value) {
        Assert.isTrue(Emptys.isNotEmpty(key), "key is empty");
        Assert.isTrue(Emptys.isNotEmpty(value), "value is empty");
        return super.put(key, value);
    }

    @Override
    public Optional<V> getNullable(K key) {
        return Optional.ofNullable(super.get(key));
    }

    @Override
    public <T> T newInstance() {
        return (T) new NoneEmptyMap();
    }
}
