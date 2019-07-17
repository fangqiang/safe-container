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
public class NoneEmptyListTest {

    @Test
    public void init() {
        NoneEmptyList<Object> m1 = new NoneEmptyList<>();
        NoneEmptyList<Object> m2 = new NoneEmptyList<>(1);
    }

    @Test
    public void add() {
        NoneEmptyList<Object> m = new NoneEmptyList<>();
        TestUtil.noException(() -> m.add(1));
        TestUtil.withException(() -> m.add(""));
        TestUtil.withException(() -> m.add(null));

        TestUtil.noException(() -> m.add(0, 1));
        TestUtil.withException(() -> m.add(0, ""));
        TestUtil.withException(() -> m.add(0, null));
    }

    @Test
    public void set() {
        NoneEmptyList<Object> m = new NoneEmptyList<>();
        m.add(1);
        TestUtil.noException(() -> m.set(0, 1));
        TestUtil.withException(() -> m.set(0, ""));
        TestUtil.withException(() -> m.set(0, null));
    }

    @Test
    public void addAll() {
        NoneEmptyList<Object> m = new NoneEmptyList<>();

        TestUtil.noException(() -> m.addAll(Collections2.ofList(1)));
        TestUtil.withException(() -> m.addAll(Collections2.ofList("")));
        TestUtil.withException(() -> m.addAll(Collections2.ofList(null, 1)));

        TestUtil.noException(() -> m.addAll(0, Collections2.ofList(1)));
        TestUtil.withException(() -> m.addAll(0, Collections2.ofList("")));
        TestUtil.withException(() -> m.addAll(0, Collections2.ofList(null, 1)));
    }

    @Test
    public void map() {
        NoneEmptyList<String> map = NoneEmptyList.of(1, 2).map(a -> a.toString());
        Assert.assertEquals(map.size(), 2);

        TestUtil.withException(() -> NoneEmptyList.of(1, 2).map(a -> a == 1 ? null : a).size());
        Assert.assertEquals(NoneEmptyList.of(1, 2).mapOmitEmptyElement(a -> a == 1 ? null : a).size(), 1);
    }

    @Test
    public void of() {
        Assert.assertEquals(NoneEmptyList.of("1, 1, 2".split(",")).size(), 3);

        Assert.assertEquals(NoneEmptyList.of(1, 1, 2).size(), 3);
        Assert.assertEquals(NoneEmptyList.of(Arrays.asList(1, 1, 2)).size(), 3);
        Assert.assertEquals(NoneEmptyList.extractFrom("", "a").size(), 1);
        Assert.assertEquals(NoneEmptyList.extractFrom(Arrays.asList("", "a")).size(), 1);
    }

    @Test
    public void collector() {
        Assert.assertEquals(NoneEmptyList.of(1, 2).stream().collect(NoneEmptyList.toList()).size(), 2);
        Assert.assertEquals(NoneEmptyList.of(1, 2).parallelStream().collect(NoneEmptyList.toList()).size(), 2);

        TestUtil.withException(() -> Collections2.ofList("a", "b", "").stream().collect(NoneEmptyList.toList()).size());
    }
}