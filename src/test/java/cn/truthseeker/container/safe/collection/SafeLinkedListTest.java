package cn.truthseeker.container.safe.collection;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class SafeLinkedListTest {

    @Test
    public void init() {
        List list = new ArrayList();
        list.add(null);
        list.add(null);
        Assert.assertTrue(TestUtil.throwException(() -> new SafeLinkedList(list)));
        list.clear();
        Assert.assertFalse(TestUtil.throwException(() -> new SafeLinkedList(list)));

        new SafeLinkedList(Arrays.asList(1));
    }

    @Test
    public void add() {
        SafeLinkedList list = new SafeLinkedList();
        Assert.assertTrue(TestUtil.throwException(() -> list.add(null)));
        Assert.assertTrue(TestUtil.throwException(() -> list.add(1, null)));

        Assert.assertFalse(TestUtil.throwException(() -> list.add(1)));
        Assert.assertFalse(TestUtil.throwException(() -> list.add(1, 1)));
    }

    @Test
    public void addAll() {
        SafeLinkedList list = new SafeLinkedList();
        Assert.assertTrue(TestUtil.throwException(() -> list.addAll(null)));
        Assert.assertTrue(TestUtil.throwException(() -> list.addAll(1, null)));

        Assert.assertFalse(TestUtil.throwException(() -> list.addAll(Arrays.asList(1))));
        Assert.assertFalse(TestUtil.throwException(() -> list.addAll(1, Arrays.asList(1))));
    }

    @Test
    public void set() {
        SafeLinkedList list = new SafeLinkedList();
        list.add(1);
        list.set(0, 1);
    }
}