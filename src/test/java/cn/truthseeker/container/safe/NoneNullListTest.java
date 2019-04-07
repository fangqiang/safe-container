package cn.truthseeker.container.safe;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/4/7
 */
public class NoneNullListTest {

    @Test
    public void init() {
        NoneNullList<Object> m1 = new NoneNullList<>();
        NoneNullList<Object> m2 = new NoneNullList<>(Arrays.asList(1));
        NoneNullList<Object> m3 = new NoneNullList<>(1);
    }

    @Test
    public void add() {
        NoneNullList<Object> m = new NoneNullList<>();
        TestUtil.noException(()->m.add(1));
        TestUtil.noException(()->m.add(""));
        TestUtil.withException(()->m.add(null));

        TestUtil.noException(()->m.add(0,1));
        TestUtil.noException(()->m.add(0,""));
        TestUtil.withException(()->m.add(0,null));
    }

    @Test
    public void set() {
        NoneNullList<Object> m = new NoneNullList<>();
        m.add(1);
        TestUtil.noException(()->m.set(0,1));
        TestUtil.noException(()->m.set(0,""));
        TestUtil.withException(()->m.set(0,null));
    }

    @Test
    public void addAll() {
        NoneNullList<Object> m = new NoneNullList<>();

        TestUtil.noException(()->m.addAll(Collections2.ofList(1)));
        TestUtil.noException(()->m.addAll(Collections2.ofList("",1)));
        TestUtil.withException(()->m.addAll(Collections2.ofList(null,1)));

        TestUtil.noException(()->m.addAll(0, Collections2.ofList(1)));
        TestUtil.noException(()->m.addAll(0, Collections2.ofList("",1)));
        TestUtil.withException(()->m.addAll(0, Collections2.ofList(null,1)));
    }

    @Test
    public void of() {
        Assert.assertEquals(NoneNullList.of(1,2).size(),2);
    }


    @Test
    public void getCollector() {
        NoneNullList<Integer> collect = NoneNullList.of(1, 2).stream().collect(NoneNullList.getCollector());
        Assert.assertEquals(collect.size(),2);

        NoneNullList.of(1, 2).parallelStream().collect(NoneNullList.getCollector());
    }

    @Test
    public void map() {
        NoneNullList<String> map = NoneNullList.of(1, 2).map(a -> a.toString());
        Assert.assertEquals(map.size(),2);
    }
}