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
        NoneNullSet<Object> m2 = new NoneNullSet<>(1);
    }

    @Test
    public void add() {
        NoneNullSet<Object> m = new NoneNullSet<>();
        TestUtil.noException(() -> m.add(1));
        TestUtil.noException(() -> m.add(""));
        TestUtil.withException(() -> m.add(null));
    }

    @Test
    public void addAll() {
        NoneNullSet<Object> m = new NoneNullSet<>();

        TestUtil.noException(() -> m.addAll(Collections2.ofList(1)));
        TestUtil.noException(() -> m.addAll(Collections2.ofList("", 1)));
        TestUtil.withException(() -> m.addAll(Collections2.ofList(null, 1)));
    }

    @Test
    public void map() {
        NoneNullSet<String> map = NoneNullSet.of(1, 2).map(a -> a.toString());
        Assert.assertEquals(map.size(), 2);

        TestUtil.withException(() -> NoneNullSet.of(1, 2).map(a -> a == 1 ? null : a).size());
        Assert.assertEquals(NoneNullSet.of(1, 2).mapOmitEmptyElement(a -> a == 1 ? null : a).size(), 1);
    }


    @Test
    public void of() {
        Assert.assertEquals(NoneNullSet.of(1, 1, 2).size(), 2);
        Assert.assertEquals(NoneNullSet.of(Arrays.asList(1, 1, 2)).size(), 2);
        Assert.assertEquals(NoneNullSet.extractNotNullFrom(null, "a").size(), 1);
        Assert.assertEquals(NoneNullSet.extractNotNullFrom(Arrays.asList(null, "a")).size(), 1);
    }


    @Test
    public void getCollector() {
        Assert.assertEquals(NoneNullSet.of(1, 2).stream().collect(NoneNullSet.toSet()).size(), 2);
        Assert.assertEquals(NoneNullSet.of(1, 2).parallelStream().collect(NoneNullSet.toSet()).size(), 2);

        TestUtil.withException(() -> Collections2.ofList("a", "b", null).stream().collect(NoneNullSet.toSet()).size());
    }
}