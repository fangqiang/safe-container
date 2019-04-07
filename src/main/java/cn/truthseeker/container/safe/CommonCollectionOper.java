package cn.truthseeker.container.safe;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/21
 */
public interface CommonCollectionOper<E> extends Collection<E> {

    default boolean anySatisfied(Predicate<E> predicate) {
        return Collections2.anySatisfied(this, predicate);
    }

    default void assertTrue(Predicate<E> predicate, String errMsg) {
        Collections2.assertTrue(this, predicate, errMsg);
    }

    default <V, T extends Map<E, V>> T toMap(Function<E, V> function, Supplier<T> supplier) {
        return Collections2.toMap(this, function, supplier);
    }
}
