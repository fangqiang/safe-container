package cn.truthseeker.container.safe;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/4/7
 */
public class CommonCollectionOperTest {

    @Test
    public void anySatisfied() {
        NoneEmptyList<Integer> of = NoneEmptyList.of(1, 2);
        Assert.assertTrue(of.anySatisfied(a->a==1));
        Assert.assertFalse(of.anySatisfied(a->a==3));
    }

    @Test
    public void assertTrue() {
        TestUtil.withException(()->NoneEmptyList.of(1, 2).assertTrue(a->a<2, ""));
        TestUtil.noException(()->NoneEmptyList.of(1, 2).assertTrue(a->a<3, ""));
    }

    @Test
    public void toMap() {
        HashMap<Integer, String> map = NoneEmptyList.of(1, 2).toMap(Object::toString, HashMap::new);
        Assert.assertEquals(map.get(1),"1");
        Assert.assertEquals(map.get(2),"2");

        HashMap<String, Integer> map1 = NoneEmptyList.of(1, 2).toMap(Object::toString, a -> a + 10, HashMap::new);
        Assert.assertEquals(map1.get("1").intValue(),11);
        Assert.assertEquals(map1.get("2").intValue(),12);
    }
}