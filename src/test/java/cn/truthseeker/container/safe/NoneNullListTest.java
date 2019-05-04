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
        NoneNullList<Object> m2 = new NoneNullList<>(1);
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
        TestUtil.noException(()->m.addAll(Collections2.ofList("")));
        TestUtil.withException(()->m.addAll(Collections2.ofList(null,1)));

        TestUtil.noException(()->m.addAll(0, Collections2.ofList(1)));
        TestUtil.noException(()->m.addAll(0, Collections2.ofList("")));
        TestUtil.withException(()->m.addAll(0, Collections2.ofList(null,1)));
    }

    @Test
    public void map() {
        NoneNullList<String> map = NoneNullList.of(1, 2).map(a -> a.toString());
        Assert.assertEquals(map.size(),2);

        TestUtil.withException(()->NoneNullList.of(1, 2).map(a -> a==1? null:a).size());
        Assert.assertEquals(NoneNullList.of(1, 2).mapIgnoreNull(a -> a==1? null:a).size(), 1);
    }

    @Test
    public void of() {
        Assert.assertEquals(NoneNullList.of(1,2).size(),2);
        Assert.assertEquals(NoneNullList.of(Arrays.asList(1,2)).size(),2);
        Assert.assertEquals(NoneNullList.ofIgnoreNull(null,"a").size(),1);
        Assert.assertEquals(NoneNullList.ofIgnoreNull(Arrays.asList(null,"a")).size(),1);
    }

    @Test
    public void collector() {
        Assert.assertEquals(NoneNullList.of(1,2).stream().collect(NoneNullList.toList()).size(),2);
        Assert.assertEquals(NoneNullList.of(1,2).parallelStream().collect(NoneNullList.toList()).size(),2);

        TestUtil.withException(()->Collections2.ofList("a","b",null).stream().collect(NoneNullList.toList()).size());
    }
}