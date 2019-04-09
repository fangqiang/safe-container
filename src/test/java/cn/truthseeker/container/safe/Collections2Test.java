package cn.truthseeker.container.safe;

import cn.truthseeker.TestUtil;
import cn.truthseeker.container.safe.Collections2;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/26
 */
public class Collections2Test {

    @Test
    public void ofList() {
        Assert.assertTrue(Collections2.ofList(1).size() == 1);
        Assert.assertTrue(Collections2.ofList(1,1).size() == 2);
        Assert.assertTrue(Collections2.ofList(1,1,1).size() == 3);
    }

    @Test
    public void ofSet() {
        Assert.assertTrue(Collections2.ofSet(1).size() == 1);
        Assert.assertTrue(Collections2.ofSet(1,1).size() == 1);
        Assert.assertTrue(Collections2.ofSet(1,1,1).size() == 1);
    }

    @Test
    public void toMap() {
        Assert.assertTrue(Collections2.toMap(Arrays.asList(1, 2), a -> a, HashMap::new).size() == 2);
        Assert.assertTrue(Collections2.toMap(new Integer[]{1, 2}, a -> a, HashMap::new).size() == 2);

        Assert.assertTrue(Collections2.toMap(Arrays.asList(1, 2), Object::toString, a -> a + 10, HashMap::new).size() == 2);
        Assert.assertTrue(Collections2.toMap(new Integer[]{1, 2}, Object::toString, a -> a + 10, HashMap::new).size() == 2);
    }

    @Test
    public void anySatisfied() {
        Assert.assertTrue(Collections2.anySatisfied(Arrays.asList(1, 2), a -> a == 2));
        Assert.assertTrue(Collections2.anySatisfied(new Integer[]{1, 2}, a -> a == 2));
    }

    @Test
    public void assertTrue() {
        TestUtil.noException(()->Collections2.assertTrue(Arrays.asList(1,2), a->a<3, ""));
        TestUtil.withException(()->Collections2.assertTrue(Arrays.asList(1,2), a->a>3,""));
        TestUtil.noException(()->Collections2.assertTrue(new Integer[]{1, 2}, a->a<3,""));
        TestUtil.withException(()->Collections2.assertTrue(new Integer[]{1, 2}, a->a>3,""));
    }
    @Test
    public void of() {
        Assert.assertEquals(Collections2.of(NoneEmptyList::new,1).size(),1);
        Assert.assertEquals(Collections2.of(NoneEmptyList::new,1,2).size(),2);
        Assert.assertEquals(Collections2.of(NoneEmptyList::new,1,2,3).size(),3);
    }
}