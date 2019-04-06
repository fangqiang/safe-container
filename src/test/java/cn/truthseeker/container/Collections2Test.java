package cn.truthseeker.container;

import cn.truthseeker.TestUtil;
import cn.truthseeker.container.safe.collection.Collections2;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class Collections2Test {

    @Test
    public void anySatisfy() {
        assertTrue(Collections2.anySatisfied(Arrays.asList(1, 2), a -> a == 1));
        assertTrue(Collections2.anySatisfied(Arrays.asList(1, 2).toArray(), a -> (int) a == 2));

        assertTrue(!Collections2.anySatisfied(Arrays.asList(1, 2), a -> a == 3));
        assertTrue(!Collections2.anySatisfied(Arrays.asList(1, 2).toArray(), a -> (int) a == 3));

    }
}