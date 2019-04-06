package cn.truthseeker.container.util;

import cn.truthseeker.TestUtil;
import org.junit.Test;


/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/26
 */
public class AssertTest {

    @Test
    public void isTrue() {
        TestUtil.withException(() -> Assert.isTrue(false, "a"));
    }
}