package cn.truthseeker.container.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/4/30
 */
public class ReflectUtilsTest {

    A a = new A();

    static class A{
        B b = new B();
    }

    static class B{
        String s="hello";
    }

    @Test
    public void reflectInnerInstance() throws Exception {
        String s = ReflectUtils.reflectInnerInstance(new ReflectUtilsTest(), "a.b.s", String.class);
        Assert.assertEquals(s, "hello");
    }
}