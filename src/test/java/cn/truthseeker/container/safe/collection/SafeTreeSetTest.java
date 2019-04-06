package cn.truthseeker.container.safe.collection;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/15
 */
public class SafeTreeSetTest {
    @Test
    public void init() {
        new SafeTreeSet();
        new SafeTreeSet(Arrays.asList(1));
        new SafeTreeSet(new TreeSet());
        new SafeTreeSet((a, b) -> 0);
    }

    @Test
    public void add() {
        SafeTreeSet set = new SafeTreeSet();
        TestUtil.withException(() -> set.add(null));
    }

    @Test
    public void addAll() {
        SafeTreeSet set = new SafeTreeSet();
        TestUtil.withException(() -> set.addAll(Arrays.asList(null)));
    }
}