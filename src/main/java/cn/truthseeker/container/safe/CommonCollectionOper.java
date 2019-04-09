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
     * 是否有任何一个元素满足条件
     *
     * @param predicate
     * @return
     */
    default boolean anySatisfied(Predicate<E> predicate) {
        return Collections2.anySatisfied(this, predicate);
    }

    /**
     * 校验每个元素是否满足条件
     *
     * @param predicate
     * @param errMsg
     */
    default void assertTrue(Predicate<E> predicate, String errMsg) {
        Collections2.assertTrue(this, predicate, errMsg);
    }

    /**
     * 将list中的元素做为key，将函数结果作为value，构建成一个map
     *
     * @param function 将元素映射成map的key
     * @param supplier 生成map的类型
     * @param <V>      map的value类型
     * @param <T>      返回值类型
     * @return
     */
    default <V, T extends Map<E, V>> T toMap(Function<E, V> function, Supplier<T> supplier) {
        return Collections2.toMap(this, function, supplier);
    }

    /**
     * 每个元素通过kFun，vFun分别映射成key,value
     *
     * @param kFun     将元素映射成map的key
     * @param vFun     将元素映射成map的value
     * @param supplier 生成map的类型
     * @param <K>      map的key类型
     * @param <V>      map的value类型
     * @param <T>      返回值类型
     * @return
     */
    default <K, V, T extends Map<K, V>> T toMap(Function<E, K> kFun, Function<E, V> vFun, Supplier<T> supplier) {
        return Collections2.toMap(this, kFun, vFun, supplier);
    }
}
