package cn.truthseeker.container.safe;

import cn.truthseeker.util.Emptys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collector;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class NoneEmptyList<E> extends ArrayList<E> implements CommonNoneEmptyOper<E> {
    public NoneEmptyList() {
        super();
    }

    public NoneEmptyList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean add(E element) {
        Emptys.assertNotEmpty(element);
        return super.add(element);
    }

    @Override
    public void add(int index, E element) {
        Emptys.assertNotEmpty(element);
        super.add(index, element);
    }

    @Override
    public E set(int index, E element) {
        Emptys.assertNotEmpty(element);
        return super.set(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Emptys.assertNoneEmpty(c);
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Emptys.assertNoneEmpty(c);
        return super.addAll(index, c);
    }

    // 简化操作
    /**
     * NoneEmptyList<E> -> NoneEmptyList<R>
     */
    public <R> NoneEmptyList<R> map(Function<E, R> map) {
        NoneEmptyList<R> ret = new NoneEmptyList<>();
        this.forEach(e -> ret.add(map.apply(e)));
        return ret;
    }

    /**
     * NoneEmptyList<E> -> NoneEmptyList<R> 跳过为empty的元素
     */
    public <R> NoneEmptyList<R> mapIgnoreEmpty(Function<E, R> map) {
        NoneEmptyList<R> ret = new NoneEmptyList<>();
        this.forEach(e -> ret.addIgnoreEmpty(map.apply(e)));
        return ret;
    }

    // 构造工具
    /**
     * 快速构建方法
     */
    public static <E> NoneEmptyList<E> of(E... e) {
        return Collections2.of(NoneEmptyList::new, e);
    }

    /**
     * 快速构建方法
     */
    public static <E> NoneEmptyList<E> of(Iterable<E> e) {
        return Collections2.of(NoneEmptyList::new, e);
    }

    /**
     * 快速构建方法，忽略empty元素
     */
    public static <E> NoneEmptyList<E> ofIgnoreEmpty(E... e) {
        NoneEmptyList<E> ret = new NoneEmptyList<>();
        ret.addAllIgnoreEmpty(e);
        return ret;
    }

    /**
     * 快速构建方法，忽略empty元素
     */
    public static <E> NoneEmptyList<E> ofIgnoreEmpty(Iterable<E> e) {
        NoneEmptyList<E> ret = new NoneEmptyList<>();
        ret.addAllIgnoreEmpty(e);
        return ret;
    }

    /**
     * 快速构建方法，Stream<E> -> NoneEmptyList<E>
     */
    public static <E> Collector<E, ?, NoneEmptyList<E>> toList() {
        return Collector.of(
                NoneEmptyList::new,
                NoneEmptyList::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                Collector.Characteristics.IDENTITY_FINISH
        );
    }
}