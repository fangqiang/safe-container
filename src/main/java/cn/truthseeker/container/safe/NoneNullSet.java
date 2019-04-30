package cn.truthseeker.container.safe;

import cn.truthseeker.util.Emptys;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class NoneNullSet<E> extends HashSet<E> implements CommonNoneNullOper<E> {
    public NoneNullSet() {
        super();
    }

    public NoneNullSet(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean add(E e) {
        Objects.requireNonNull(e);
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Emptys.assertNoneNull(c);
        return super.addAll(c);
    }

    // 简化操作
    public <R> NoneNullSet<R> map(Function<E, R> function) {
        return stream().map(function).collect(collector());
    }

    public <R> NoneNullSet<R> mapIgnoreEmpty(Function<E, R> function) {
        return stream().map(function).collect(collectorIgnoreEmpty());
    }

    // 构造工具
    public static <E> NoneNullSet<E> of(E... e) {
        return Collections2.of(NoneNullSet::new, e);
    }

    public static <E> NoneNullSet<E> of(Iterable<E> e) {
        return Collections2.of(NoneNullSet::new, e);
    }

    public static <E> NoneNullSet<E> ofIgnoreNull(E... e) {
        NoneNullSet<E> ret = new NoneNullSet<>();
        ret.addAllIgnoreNull(e);
        return ret;
    }

    public static <E> NoneNullSet<E> ofIgnoreNull(Iterable<E> e) {
        NoneNullSet<E> ret = new NoneNullSet<>();
        ret.addAllIgnoreNull(e);
        return ret;
    }

    public static <E> Collector<E, ?, NoneNullSet<E>> collector() {
        return Collector.of(NoneNullSet::new, NoneNullSet::add, (left, right) -> {
            left.addAll(right);
            return left;
        }, Collector.Characteristics.IDENTITY_FINISH);
    }

    public static <E> Collector<E, ?, NoneNullSet<E>> collectorIgnoreEmpty() {
        return Collector.of(NoneNullSet::new,
                NoneNullSet::addIgnoreNull,
                (left, right) -> {
                    left.addAllIgnoreNull(right);
                    return left;
                }, Collector.Characteristics.IDENTITY_FINISH);
    }
}