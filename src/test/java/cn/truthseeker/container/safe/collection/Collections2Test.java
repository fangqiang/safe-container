package cn.truthseeker.container.safe.collection;

import cn.truthseeker.container.safe.Safes;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/26
 */
public class Collections2Test {

    @Test
    public void toMap() {
        Map<Integer, Integer> map = Collections2.toMap(Arrays.asList(1, 2), a -> a);
        Assert.assertTrue(map.size()==2);

        map = Collections2.toMap(new Integer[]{1,2}, a -> a);
        Assert.assertTrue(map.size()==2);
    }

    @Test
    public void anySatisfied() {
        boolean b = Collections2.anySatisfied(Arrays.asList(1, 2), a -> (int) a ==2);
        Assert.assertTrue(b);

        b = Collections2.anySatisfied(new Integer[]{1,2}, a -> (int) a ==2);
        Assert.assertTrue(b);
    }

    @Test
    public void checkSafe() {
    }
}