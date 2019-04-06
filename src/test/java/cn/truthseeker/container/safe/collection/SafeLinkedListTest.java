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
        TestUtil.withException(() -> new SafeLinkedList(list));
        list.clear();
        TestUtil.noException(() -> new SafeLinkedList(list));

        new SafeLinkedList(Arrays.asList(1));
    }

    @Test
    public void add() {
        SafeLinkedList list = new SafeLinkedList();
        TestUtil.withException(() -> list.add(null));
        TestUtil.withException(() -> list.add(1, null));

        TestUtil.noException(() -> list.add(1));
        TestUtil.noException(() -> list.add(1, 1));
    }

    @Test
    public void addAll() {
        SafeLinkedList list = new SafeLinkedList();
        TestUtil.withException(() -> list.addAll(null));
        TestUtil.withException(() -> list.addAll(1, null));

        TestUtil.noException(() -> list.addAll(Arrays.asList(1)));
        TestUtil.noException(() -> list.addAll(1, Arrays.asList(1)));
    }

    @Test
    public void set() {
        SafeLinkedList list = new SafeLinkedList();
        list.add(1);
        list.set(0, 1);
    }
}