package cn.truthseeker.container.safe;

import cn.truthseeker.util.Emptys;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;

/**
 * 不能存放任何null元素的Set
 * <p>
 *
 * @author qiang.fang
 * @date Created by on 19/3/14
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
        if (!(c instanceof NoneNullSet) && !(c instanceof NoneEmptySet)) {
            Emptys.assertNoneNull(c);
        }
        return super.addAll(c);
    }

    // 简化操作

    /**
     * NoneNullSet<E> -> NoneNullSet<R>
     */
    public <R> NoneNullSet<R> map(Function<E, R> map) {
        NoneNullSet<R> ret = new NoneNullSet<>();
        this.forEach(e -> ret.add(map.apply(e)));
        return ret;
    }

    /**
     * NoneNullSet<E> -> NoneNullSet<R> 跳过为null的元素
     */
    public <R> NoneNullSet<R> mapOmitEmptyElement(Function<E, R> map) {
        NoneNullSet<R> ret = new NoneNullSet<>();
        this.forEach(e -> ret.addIfNotNull(map.apply(e)));
        return ret;
    }

    // 构造工具

    /**
     * 快速构建方法
     */
    public static <E> NoneNullSet<E> of(E... e) {
        return Collections2.of(NoneNullSet::new, e);
    }

    /**
     * 快速构建方法
     */
    public static <E> NoneNullSet<E> of(Iterable<E> e) {
        return Collections2.of(NoneNullSet::new, e);
    }

    /**
     * 快速构建方法，从集合中抽取非null元素
     */
    public static <E> NoneNullSet<E> extractNotNullFrom(E... e) {
        NoneNullSet<E> ret = new NoneNullSet<>();
        ret.addAllOmitNull(e);
        return ret;
    }

    /**
     * 快速构建方法，从集合中抽取非null元素
     */
    public static <E> NoneNullSet<E> extractNotNullFrom(Iterable<E> e) {
        NoneNullSet<E> ret = new NoneNullSet<>();
        ret.addAllOmitNull(e);
        return ret;
    }

    /**
     * 快速构建方法，Stream<E> -> NoneNullSet<E>
     */
    public static <E> Collector<E, ?, NoneNullSet<E>> toSet() {
        return Collector.of(NoneNullSet::new, NoneNullSet::add, (left, right) -> {
            left.addAll(right);
            return left;
        }, Collector.Characteristics.IDENTITY_FINISH);
    }
}