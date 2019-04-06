package cn.truthseeker.container.safe.collection;

import cn.truthseeker.TestUtil;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/4/5
 */
public class NoneEmptyListTest {

    @Test
    public void add() {
        new NoneEmptyList(Arrays.asList("a"));

        TestUtil.withException(()->new NoneEmptyList(Arrays.asList("")));

        NoneEmptyList list = new NoneEmptyList();
        TestUtil.noException(()->list.add("a"));
        TestUtil.withException(()->list.add(""));
        TestUtil.withException(()->list.add(new byte[0]));
        TestUtil.withException(()->list.add(0, ""));
        TestUtil.noException(()->list.add(0, "a"));

        TestUtil.withException(()->list.set(0,""));
        TestUtil.noException(()->list.set(0,"1"));

        TestUtil.noException(()->list.addAll(Arrays.asList("a","a")));
        TestUtil.withException(()->list.addAll(Arrays.asList("","a")));
        TestUtil.noException(()->list.addAll(0,Arrays.asList("a")));
        TestUtil.withException(()->list.addAll(0,Arrays.asList("","a")));
    }
}