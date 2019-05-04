package cn.truthseeker.container.safe;

import cn.truthseeker.util.Emptys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class NoneNullList<E> extends ArrayList<E> implements CommonNoneNullOper<E> {
    public NoneNullList() {
        super();
    }

    public NoneNullList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean add(E element) {
        Objects.requireNonNull(element);
        return super.add(element);
    }

    @Override
    public void add(int index, E element) {
        Objects.requireNonNull(element);
        super.add(index, element);
    }

    @Override
    public E set(int index, E element) {
        Objects.requireNonNull(element);
        return super.set(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Emptys.assertNoneNull(c);
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Emptys.assertNoneNull(c);
        return super.addAll(index, c);
    }

    // 简化操作
    public <R> NoneNullList<R> map(Function<E, R> map) {
        NoneNullList<R> ret = new NoneNullList<>();
        this.forEach(e -> ret.add(map.apply(e)));
        return ret;
    }

    public <R> NoneNullList<R> mapIgnoreNull(Function<E, R> map) {
        NoneNullList<R> ret = new NoneNullList<>();
        this.forEach(e -> ret.addIgnoreNull(map.apply(e)));
        return ret;
    }

    // 构造工具
    public static <E> NoneNullList<E> of(E... e) {
        return Collections2.of(NoneNullList::new, e);
    }

    public static <E> NoneNullList<E> of(Iterable<E> e) {
        return Collections2.of(NoneNullList::new, e);
    }

    public static <E> NoneNullList<E> ofIgnoreNull(E... e) {
        NoneNullList<E> ret = new NoneNullList<>();
        ret.addAllIgnoreNull(e);
        return ret;
    }

    public static <E> NoneNullList<E> ofIgnoreNull(Iterable<E> e) {
        NoneNullList<E> ret = new NoneNullList<>();
        ret.addAllIgnoreNull(e);
        return ret;
    }

    public static <E> NoneNullList<E> ofIgnoreNull(Stream<E> stream) {
        NoneNullList<E> ret = new NoneNullList<>();
        stream.forEach(ret::addIgnoreNull);
        return ret;
    }
}