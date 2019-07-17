package cn.truthseeker.container.safe;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public interface CommonMapOper<K, V> extends Map<K, V> {

    /**
     * @param key
     * @return
     */
    default Optional<V> getNullable(K key) {
        return Optional.ofNullable(get(key));
    }

    /**
     * 返回key对应的value，如果value不存在则通过supplier生成value
     */
    default V getOrCreate(K k, Supplier<V> supplier) {
        return Maps.getOrCreate(this, k, supplier);
    }

    /**
     * 是否包含子map，key,value必须要完全相等
     */
    default boolean containsMap(Map<K, V> part) {
        return Maps.containsMap(this, part);
    }

    /**
     * 是否包含集合中所有key
     */
    default boolean containsKeys(Iterable<K> keys) {
        for (K key : keys) {
            if (!this.containsKey(key)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回唯一的key，如果map.size != 1则抛出异常
     */
    default Optional<K> getTheOnlyKey() {
        if (size() != 1) {
            return Optional.empty();
        } else {
            return Optional.of(keySet().iterator().next());
        }
    }

    /**
     * 返回唯一的value，如果map.size != 1则抛出异常
     */
    default Optional<V> getTheOnlyValue() {
        if (size() != 1) {
            return Optional.empty();
        } else {
            return Optional.of(values().iterator().next());
        }
    }

    /**
     * 集合中至少有一个元素满足条件则返回true，否则返回false
     */
    default boolean anySatisfied(BiPredicate<K, V> filter) {
        for (Entry<K, V> entry : entrySet()) {
            if (filter.test(entry.getKey(), entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验集合中每个元素是否满足条件，否则抛出异常
     */
    default void assertTrue(BiPredicate<K, V> biPredicate, String errMsg) {
        Maps.assertTrue(this, biPredicate, errMsg);
    }
}
