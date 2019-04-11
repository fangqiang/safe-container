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
public class NoneEmptySet<E> extends HashSet<E> implements CommonNoneEmptyOper<E> {
    public NoneEmptySet() {
        super();
    }

    public NoneEmptySet(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean add(E e) {
        Emptys.assertNotEmpty(e);
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Emptys.assertNoneEmpty(c);
        return super.addAll(c);
    }

    // 简化操作
    public <R> NoneEmptySet<R> map(Function<E, R> function) {
        return stream().map(function).collect(collector());
    }

    public <R> NoneEmptySet<R> mapIgnoreEmpty(Function<E, R> function) {
        return stream().map(function).collect(collectorIgnoreEmpty());
    }

    // 构造工具
    public static <E> NoneEmptySet<E> of(E... e) {
        return Collections2.of(NoneEmptySet::new, e);
    }

    public static <E> NoneEmptySet<E> of(Iterable<E> e) {
        return Collections2.of(NoneEmptySet::new, e);
    }

    public static <E> NoneEmptySet<E> ofIgnoreEmpty(E... e) {
        NoneEmptySet<E> ret = new NoneEmptySet<>();
        ret.addAllIgnoreEmpty(e);
        return ret;
    }

    public static <E> NoneEmptySet<E> ofIgnoreEmpty(Iterable<E> e) {
        NoneEmptySet<E> ret = new NoneEmptySet<>();
        ret.addAllIgnoreEmpty(e);
        return ret;
    }

    public static <E> Collector<E, ?, NoneEmptySet<E>> collector() {
        return Collector.of(NoneEmptySet::new, NoneEmptySet::add, (left, right) -> {
            left.addAll(right);
            return left;
        }, Collector.Characteristics.IDENTITY_FINISH);
    }

    public static <E> Collector<E, ?, NoneEmptySet<E>> collectorIgnoreEmpty() {
        return Collector.of(NoneEmptySet::new,
                NoneEmptySet::addIgnoreEmpty,
                (left, right) -> {
                    left.addAllIgnoreEmpty(right);
                    return left;
                }, Collector.Characteristics.IDENTITY_FINISH);
    }

}