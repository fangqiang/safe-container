package cn.truthseeker.container.safe.map;

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
public interface SafeMap<K,V> extends Map<K,V>{

    <T> T newInstance();

    Optional<V> getNullable(Object key);

    default <RK> SafeMap<RK, V> mapKey(Function<K,RK> kFun) {
        return Maps.mapKey(this, kFun, this::newInstance);
    }

    default <RV> SafeMap<K, RV> mapValue(Function<V,RV> vFun) {
        return Maps.mapValue(this, vFun, this::newInstance);
    }

    default <RK, RV> SafeMap<RK, RV> mapKeyValue(Function<K,RK> kFun, Function<V,RV> vFun) {
        return Maps.mapKeyValue(this, kFun, vFun, this::newInstance);
    }

    default SafeMap<K, V> filterByKey(Predicate<K> kFun) {
        return Maps.filterByKey(this, kFun, this::newInstance);
    }

    default SafeMap<K, V> filterByValue(Predicate<V> vFun) {
        return Maps.filterByValue(this, vFun, this::newInstance);
    }

    default SafeMap<K, V> filterByKeyValue(BiPredicate<K,V> predicate) {
        return Maps.filterByKeyValue(this, predicate, this::newInstance);
    }

    default SafeMap<K, V> getSubMap(Collection<K> keys) {
        return Maps.getSubMap(this, keys, this::newInstance);
    }

    default boolean containsMap(Map<K, V> part){
        return Maps.containsMap(this, part);
    }

    default boolean containsKeys(List<K> keys){
        for (K key : keys) {
            if(!this.containsKey(key)){
                return false;
            }
        }
        return true;
    }
}
