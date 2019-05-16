package cn.truthseeker.container.safe;

import cn.truthseeker.util.Emptys;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collector;

/**
 * 不能存放任何empty元素的Set
 * <p>
 * empty的定义如下：
 * 1. 不为null
 * 2. String类型的值必须包含非空白字符
 * 3. 集合类型至少包含一个元素
 * 3. 数组类型长度必须大于0
 *
 * @author: qiang.fang
 * @date: Created by on 19/4/7
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
        if (!(c instanceof NoneEmptySet)) {
            Emptys.assertNoneEmpty(c);
        }
        return super.addAll(c);
    }

    // 简化操作

    /**
     * NoneEmptySet<E> -> NoneEmptySet<R>
     */
    public <R> NoneEmptySet<R> map(Function<E, R> map) {
        NoneEmptySet<R> ret = new NoneEmptySet<>();
        this.forEach(e -> ret.add(map.apply(e)));
        return ret;
    }

    /**
     * NoneEmptySet<E> -> NoneEmptySet<R> 跳过为empty的元素
     */
    public <R> NoneEmptySet<R> mapOmitEmptyElement(Function<E, R> map) {
        NoneEmptySet<R> ret = new NoneEmptySet<>();
        this.forEach(e -> ret.addIfNotEmpty(map.apply(e)));
        return ret;
    }

    // 构造工具

    /**
     * 快速构建方法
     */
    public static <E> NoneEmptySet<E> of(E... e) {
        return Collections2.of(NoneEmptySet::new, e);
    }

    /**
     * 快速构建方法
     */
    public static <E> NoneEmptySet<E> of(Iterable<E> e) {
        return Collections2.of(NoneEmptySet::new, e);
    }

    /**
     * 快速构建方法，从集合中抽取非empty元素
     */
    public static <E> NoneEmptySet<E> extractNotEmptyFrom(E... e) {
        NoneEmptySet<E> ret = new NoneEmptySet<>();
        ret.addAllOmitEmpty(e);
        return ret;
    }

    /**
     * 快速构建方法，从集合中抽取非empty元素
     */
    public static <E> NoneEmptySet<E> extractNotEmptyFrom(Iterable<E> e) {
        NoneEmptySet<E> ret = new NoneEmptySet<>();
        ret.addAllOmitEmpty(e);
        return ret;
    }

    /**
     * 快速构建方法，Stream<E> -> NoneEmptySet<E>
     */
    public static <E> Collector<E, ?, NoneEmptySet<E>> toSet() {
        return Collector.of(NoneEmptySet::new, NoneEmptySet::add, (left, right) -> {
            left.addAll(right);
            return left;
        }, Collector.Characteristics.IDENTITY_FINISH);
    }
}