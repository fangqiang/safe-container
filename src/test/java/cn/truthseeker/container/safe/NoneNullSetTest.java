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
public class NoneNullSetTest {

    @Test
    public void init() {
        NoneNullSet<Object> m1 = new NoneNullSet<>();
        NoneNullSet<Object> m2 = new NoneNullSet<>(Arrays.asList(1));
        NoneNullSet<Object> m3 = new NoneNullSet<>(1);
    }

    @Test
    public void add() {
        NoneNullSet<Object> m = new NoneNullSet<>();
        TestUtil.noException(()->m.add(1));
        TestUtil.noException(()->m.add(""));
        TestUtil.withException(()->m.add(null));
    }

    @Test
    public void addAll() {
        NoneNullSet<Object> m = new NoneNullSet<>();

        TestUtil.noException(()->m.addAll(Collections2.ofList(1)));
        TestUtil.noException(()->m.addAll(Collections2.ofList("",1)));
        TestUtil.withException(()->m.addAll(Collections2.ofList(null,1)));
    }

    @Test
    public void of() {
        Assert.assertEquals(NoneNullSet.of(1,2).size(),2);
    }


    @Test
    public void getCollector() {
        NoneNullSet<Integer> collect = NoneNullSet.of(1, 2).stream().collect(NoneNullSet.getCollector());
        Assert.assertEquals(collect.size(),2);

        NoneNullSet.of(1, 2).parallelStream().collect(NoneNullSet.getCollector());
    }

    @Test
    public void map() {
        NoneNullSet<String> map = NoneNullSet.of(1, 2).map(a -> a.toString());
        Assert.assertEquals(map.size(),2);
    }
}