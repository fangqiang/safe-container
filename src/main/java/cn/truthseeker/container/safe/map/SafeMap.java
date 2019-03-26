package cn.truthseeker.container.safe.map;

import cn.truthseeker.container.util.Emptys;
import cn.truthseeker.tags.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public interface SafeMap<K, V> extends Map<K, V> {

    <T> T newInstance();

    Optional<V> getNullable(K key);

    @Deprecated // use getNullable() instead
    @Nullable
    @Override
    V get(Object key);

    /**
     * 按key映射
     */
    default <RK> SafeMap<RK, V> mapKey(Function<K, RK> kFun) {
        return CommonMaps.mapKey(this, kFun, this::newInstance);
    }

    /**
     * 按value映射
     */
    default <RV> SafeMap<K, RV> mapValue(Function<V, RV> vFun) {
        return CommonMaps.mapValue(this, vFun, this::newInstance);
    }

    /**
     * 按key,value映射
     */
    default <RK, RV> SafeMap<RK, RV> mapKeyValue(Function<K, RK> kFun, Function<V, RV> vFun) {
        return CommonMaps.mapKeyValue(this, kFun, vFun, this::newInstance);
    }

    /**
     * 按key过滤
     */
    default SafeMap<K, V> filterByKey(Predicate<K> kFun) {
        return CommonMaps.filterByKey(this, kFun, this::newInstance);
    }

    /**
     * 按value过滤
     */
    default SafeMap<K, V> filterByValue(Predicate<V> vFun) {
        return CommonMaps.filterByValue(this, vFun, this::newInstance);
    }

    /**
     * 按key,value过滤
     */
    default SafeMap<K, V> filterByKeyValue(BiPredicate<K, V> predicate) {
        return CommonMaps.filterByKeyValue(this, predicate, this::newInstance);
    }

    /**
     * 按keys返回子map
     */
    default SafeMap<K, V> getSubMap(Collection<K> keys) {
        return CommonMaps.getSubMap(this, keys, this::newInstance);
    }

    /**
     * 是否包含子map，key,value必须要完全相等
     */
    default boolean containsMap(Map<K, V> part) {
        return Maps.containsMap(this, part);
    }

    /**
     * 是否包含这些key的集合
     */
    default boolean containsKeys(List<K> keys) {
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

    default SafeMap<K, V> removeEmpty() {
        entrySet().removeIf(entry -> Emptys.isAnyEmpty(entry.getKey(), entry.getValue()));
        return this;
    }

    default SafeMap<K, V> removeIf(BiPredicate<K, V> filter) {
        entrySet().removeIf(entry -> filter.test(entry.getKey(), entry.getValue()));
        return this;
    }

    default boolean anySatisfied(BiPredicate<K, V> filter) {
        for (Entry<K, V> entry : entrySet()) {
            if (filter.test(entry.getKey(), entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    default SafeMap<K, V> forEach2(BiConsumer<K, V> consumer) {
        forEach(consumer);
        return this;
    }
}
