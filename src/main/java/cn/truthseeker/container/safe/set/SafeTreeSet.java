package cn.truthseeker.container.safe.set;

import cn.truthseeker.container.util.Assert;

import java.util.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class SafeTreeSet<E> extends TreeSet<E> implements SafeSet<E> {

    public SafeTreeSet() {
        super();
    }

    public SafeTreeSet(Comparator<? super E> comparator) {
        super(comparator);
    }

    public SafeTreeSet(Collection<? extends E> c) {
        super(c);
        if (!(c instanceof SafeSet)) {
            Assert.checkSafe(c);
        }
    }

    public SafeTreeSet(SortedSet<E> s) {
        super(s);
        Assert.checkSafe(s);
    }


    @Override
    public boolean add(E e) {
        Objects.requireNonNull(e);
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Assert.checkSafe(c);
        return super.addAll(c);
    }

}