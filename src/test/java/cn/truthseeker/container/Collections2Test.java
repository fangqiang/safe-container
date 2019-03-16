package cn.truthseeker.container;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class Collections2Test {

    @Test
    public void checkSafe() {
        List list = Arrays.asList(1);
        List list2 = Arrays.asList(1, null);

        Assert.assertFalse(TestUtil.throwException((a) -> cn.truthseeker.container.util.Assert.checkSafe(list)));
        Assert.assertTrue(TestUtil.throwException((a) -> cn.truthseeker.container.util.Assert.checkSafe(list2)));
    }
}