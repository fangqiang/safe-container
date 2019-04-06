package cn.truthseeker.container.safe.collection;

import cn.truthseeker.container.safe.Safes;
import cn.truthseeker.container.safe.map.SafeMap;
import cn.truthseeker.container.util.Assert;

import java.util.Collection;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/21
 */
public interface SafeCollection<E> extends Collection<E> {

    default <V> SafeMap<E, V> toSafeMapIgnoreNull(Function<E, V> function) {
        SafeMap<E, V> ret = Safes.newSafeMap();
        for (E k : this) {
            V v = function.apply(k);
            if (v != null) {
                ret.put(k, v);
            }
        }
        return ret;
    }

    default boolean anySatisfied(Predicate<E> predicate) {
        return Collections2.anySatisfied(this, predicate);
    }

    default void assertTrue(Predicate<E> predicate, String errMsg){
        forEach((e)-> Assert.isTrue(predicate.test(e), errMsg));
    }
}
