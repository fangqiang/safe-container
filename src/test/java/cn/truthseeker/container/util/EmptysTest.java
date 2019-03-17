package cn.truthseeker.container.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/17
 */
public class EmptysTest {
    @Test
    public void isNotEmpty() {
        assertFalse(Emptys.isNotEmpty(new HashMap()));
        assertFalse(Emptys.isNotEmpty(new ArrayList()));
        assertFalse(Emptys.isNotEmpty("\r"));
        assertFalse(Emptys.isNotEmpty(new Object[0]));
        assertFalse(Emptys.isNotEmpty(new String[0]));
        assertFalse(Emptys.isNotEmpty(new byte[0]));
        assertFalse(Emptys.isNotEmpty(new short[0]));
        assertFalse(Emptys.isNotEmpty(new int[0]));
        assertFalse(Emptys.isNotEmpty(new long[0]));
        assertFalse(Emptys.isNotEmpty(new double[0]));
        assertFalse(Emptys.isNotEmpty(new float[0]));
        assertFalse(Emptys.isNotEmpty(new boolean[0]));
        assertFalse(Emptys.isNotEmpty(new char[0]));
    }

    @Test
    public void oneNull() {
        assertTrue(Emptys.oneNull("a",null));
        assertTrue(Emptys.oneNull(null,"a"));
        assertFalse(Emptys.oneNull(null,null));
        assertFalse(Emptys.oneNull("a","a"));
    }

    @Test
    public void oneEmpty() {
        assertTrue(Emptys.oneEmpty("","a"));
        assertTrue(Emptys.oneEmpty("a",""));
        assertFalse(Emptys.oneEmpty("",null));
        assertFalse(Emptys.oneEmpty("a","a"));
    }
}