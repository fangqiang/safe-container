package cn.truthseeker.container;

import cn.truthseeker.container.safe.map.CommonMaps;
import cn.truthseeker.tags.Nullable;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/19
 */
public class SimpleCache<K, V> extends ConcurrentHashMap<K, V> {

    @Override
    @Deprecated
    @Nullable
    public V get(Object k) {
        return super.get(k);
    }

    public Optional<V> getNullable(K k) {
        return Optional.ofNullable(get(k));
    }

    public V getOrCreate(K k, Supplier<V> supplier) {
        return CommonMaps.getOrCreate(this, k, supplier);
    }
}
