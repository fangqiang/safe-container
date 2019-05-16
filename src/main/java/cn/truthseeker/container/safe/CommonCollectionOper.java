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

    /**
     * 校验集合中每个元素是否满足条件，否则抛出异常
     */
    default boolean anySatisfied(Predicate<E> predicate) {
        return Collections2.anySatisfied(this, predicate);
    }

    /**
     * 校验集合中每个元素是否满足条件，否则抛出异常
     */
    default void assertTrue(Predicate<E> predicate, String errMsg) {
        Collections2.assertTrue(this, predicate, errMsg);
    }

    /**
     * 将list中的元素做为key，将vFun函数的结果作为value，构建成一个map
     */
    default <V, T extends Map<E, V>> T toMap(Function<E, V> vFun, Supplier<T> supplier) {
        return Collections2.toMap(this, vFun, supplier);
    }

    /**
     * 对list中每个元素将kFun的返回值作为key，vFun函数结果作为value，构建成一个map
     */
    default <K, V, T extends Map<K, V>> T toMap(Function<E, K> kFun, Function<E, V> vFun, Supplier<T> supplier) {
        return Collections2.toMap(this, kFun, vFun, supplier);
    }
}
