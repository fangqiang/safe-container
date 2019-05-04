package cn.truthseeker.container.safe;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/4/7
 */
public class NoneEmptySetTest {

    @Test
    public void init() {
        NoneEmptySet<Object> m1 = new NoneEmptySet<>();
        NoneEmptySet<Object> m3 = new NoneEmptySet<>(1);
    }

    @Test
    public void add() {
        NoneEmptySet<Object> m = new NoneEmptySet<>();
        TestUtil.noException(()->m.add(1));
        TestUtil.withException(()->m.add(""));
        TestUtil.withException(()->m.add(null));
    }

    @Test
    public void addAll() {
        NoneEmptySet<Object> m = new NoneEmptySet<>();

        TestUtil.noException(()->m.addAll(Collections2.ofList(1)));
        TestUtil.withException(()->m.addAll(Collections2.ofList("",1)));
        TestUtil.withException(()->m.addAll(Collections2.ofList(null,1)));
    }

    @Test
    public void map() {
        NoneEmptySet<String> map = NoneEmptySet.of(1, 2).map(a -> a.toString());
        Assert.assertEquals(map.size(),2);

        TestUtil.withException(()->NoneEmptySet.of(1, 2).map(a -> a==1? null:a).size());
        Assert.assertEquals(NoneEmptySet.of(1, 2).mapIgnoreEmpty(a -> a==1? null:a).size(), 1);
    }


    @Test
    public void of() {
        Assert.assertEquals(NoneEmptySet.of(1,1,2).size(),2);
        Assert.assertEquals(NoneEmptySet.of(Arrays.asList(1,1,2)).size(),2);
        Assert.assertEquals(NoneEmptySet.ofIgnoreEmpty("","a").size(),1);
        Assert.assertEquals(NoneEmptySet.ofIgnoreEmpty(Arrays.asList("","a")).size(),1);
        Assert.assertEquals(NoneEmptySet.ofIgnoreEmpty(Stream.of("","a")).size(),1);
    }
}