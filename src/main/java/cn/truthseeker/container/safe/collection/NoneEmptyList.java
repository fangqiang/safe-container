package cn.truthseeker.container.safe.collection;

import cn.truthseeker.container.util.Assert;
import cn.truthseeker.container.util.Emptys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class NoneEmptyList<E> extends ArrayList<E> implements SafeList<E> {
    public NoneEmptyList() {
        super();
    }

    public NoneEmptyList(Collection<? extends E> c) {
        super();
        for (E e : c) {
            add(e);
        }
    }

    @Override
    public boolean add(E e) {
        Assert.isTrue(Emptys.isNotEmpty(e), "element is empty");
        return super.add(e);
    }

    @Override
    public void add(int index, E element) {
        Assert.isTrue(Emptys.isNotEmpty(element), "element is empty");
        super.add(index, element);
    }

    @Override
    public E set(int index, E element) {
        Assert.isTrue(Emptys.isNotEmpty(element), "element is empty");
        return super.set(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Assert.isTrue(Emptys.isNoneEmpty(c), "collection contains empty");
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Assert.isTrue(Emptys.isNoneEmpty(c), "collection contains empty");
        return super.addAll(index, c);
    }
}