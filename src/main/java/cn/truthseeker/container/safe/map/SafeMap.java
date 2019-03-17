package cn.truthseeker.container.safe.map;

import cn.truthseeker.container.util.Emptys;
import cn.truthseeker.tags.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    default void putIgnoreNull(K k, V v){
        if(Emptys.isNoneNull(k, v)){
            put(k,v);
        }
    }

    default void putIgnoreEmpty(K k, V v){
        if(Emptys.isNoneEmpty(k, v)){
            put(k,v);
        }
    }

    /**
     * 按key映射
     */
    default <RK> SafeMap<RK, V> mapKey(Function<K, RK> kFun) {
        return Maps.mapKey(this, kFun, this::newInstance);
    }

    /**
     * 按value映射
     */
    default <RV> SafeMap<K, RV> mapValue(Function<V, RV> vFun) {
        return Maps.mapValue(this, vFun, this::newInstance);
    }

    /**
     * 按key,value映射
     */
    default <RK, RV> SafeMap<RK, RV> mapKeyValue(Function<K, RK> kFun, Function<V, RV> vFun) {
        return Maps.mapKeyValue(this, kFun, vFun, this::newInstance);
    }

    /**
     * 按key过滤
     */
    default SafeMap<K, V> filterByKey(Predicate<K> kFun) {
        return Maps.filterByKey(this, kFun, this::newInstance);
    }

    /**
     * 按value过滤
     */
    default SafeMap<K, V> filterByValue(Predicate<V> vFun) {
        return Maps.filterByValue(this, vFun, this::newInstance);
    }

    /**
     * 按key,value过滤
     */
    default SafeMap<K, V> filterByKeyValue(BiPredicate<K, V> predicate) {
        return Maps.filterByKeyValue(this, predicate, this::newInstance);
    }

    /**
     * 按keys返回子map
     */
    default SafeMap<K, V> getSubMap(Collection<K> keys) {
        return Maps.getSubMap(this, keys, this::newInstance);
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
    default K getTheOnlyKey() {
        return Maps.getTheOnlyKey(this);
    }

    /**
     * 返回唯一的value，如果map.size != 1则抛出异常
     */
    default V getTheOnlyValue() {
        return Maps.getTheOnlyValue(this);
    }

    default SafeMap<K, V> removeEmpty(){
        entrySet().removeIf(entry -> Emptys.isAnyEmpty(entry.getKey(),entry.getValue()));
        return this;
    }

    default SafeMap<K, V> removeIf(BiPredicate<K,V> filter){
        entrySet().removeIf(entry -> filter.test(entry.getKey(),entry.getValue()));
        return this;
    }
}
