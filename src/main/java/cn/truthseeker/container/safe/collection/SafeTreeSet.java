package cn.truthseeker.container.safe.collection;

import cn.truthseeker.container.util.Assert;
import cn.truthseeker.container.util.Emptys;

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
            Assert.isTrue(Emptys.isNoneNull(c), "collection contains null");
        }
    }

    public SafeTreeSet(SortedSet<E> s) {
        super(s);
        Assert.isTrue(Emptys.isNoneNull(s), "collection contains null");
    }


    @Override
    public boolean add(E e) {
        Objects.requireNonNull(e);
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Assert.isTrue(Emptys.isNoneNull(c), "collection contains null");
        return super.addAll(c);
    }

}