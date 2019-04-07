package cn.truthseeker.container.safe;

import cn.truthseeker.container.util.Emptys;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collector;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class NoneNullSet<E> extends HashSet<E> implements CommonCollectionOper<E> {
    public NoneNullSet() {
        super();
    }

    public NoneNullSet(Collection<? extends E> c) {
        super();
        addAll(c);
    }

    public NoneNullSet(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean add(E e) {
        Emptys.assertNotNull(e);
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Emptys.assertNoneNull(c);
        return super.addAll(c);
    }


    // 构造工具
    public static <E> NoneNullSet<E> of(E... e) {
        return Collections2.of(NoneNullSet::new, e);
    }


    public static <E> Collector<E, ?, NoneNullSet<E>> getCollector() {
        return Collector.of(
                NoneNullSet::new,
                NoneNullSet::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                Collector.Characteristics.IDENTITY_FINISH);
    }

    // 简化操作
    public <R> NoneNullSet<R> map(Function<E, R> function) {
        return stream().map(function).collect(getCollector());
    }
}