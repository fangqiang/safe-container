package cn.truthseeker.container;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/19
 */
public class SimpleCache<K,V>{
    ConcurrentHashMap<K,V> cache = new ConcurrentHashMap<>();

    public void put(K k, V v){
        cache.put(k, v);
    }

    public Optional<V> get(String k){
        return Optional.ofNullable(cache.get(k));
    }

    public V getOrCreate(K k, Supplier<V> supplier){
        V v = cache.get(k);
        if(v == null){
            v = supplier.get();
            cache.put(k, v);
        }
        return v;
    }
}
