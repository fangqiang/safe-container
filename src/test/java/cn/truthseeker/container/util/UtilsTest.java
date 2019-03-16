package cn.truthseeker.container.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/16
 */
public class UtilsTest {

    @Test
    public void isAnyNull() {
        assertFalse(Utils.isAnyNull(1,2));
        assertFalse(Utils.isAnyNull("",1,2));
        assertTrue(Utils.isAnyNull(1,2,null));
    }

    @Test
    public void isAnyEmpty() {
        assertFalse(Utils.isAnyEmpty(1,2));
        assertTrue(Utils.isAnyEmpty("",1,2));
        assertTrue(Utils.isAnyEmpty(1,2,null));
    }

    @Test
    public void sleep() {
        Utils.sleep(1);
    }

    @Test
    public void mapAddList() {
        HashMap<String, List<Object>> map = new HashMap<>();
        Utils.mapAddList(map, "a",1);
        Utils.mapAddList(map, "a",1);
    }

    @Test
    public void isNotEmpty() {
        assertFalse(Utils.isNotEmpty(new HashMap()));
        assertFalse(Utils.isNotEmpty(new ArrayList()));
        assertFalse(Utils.isNotEmpty("\r"));
        assertFalse(Utils.isNotEmpty(new Object[0]));
        assertFalse(Utils.isNotEmpty(new String[0]));
        assertFalse(Utils.isNotEmpty(new byte[0]));
        assertFalse(Utils.isNotEmpty(new short[0]));
        assertFalse(Utils.isNotEmpty(new int[0]));
        assertFalse(Utils.isNotEmpty(new long[0]));
        assertFalse(Utils.isNotEmpty(new double[0]));
        assertFalse(Utils.isNotEmpty(new float[0]));
        assertFalse(Utils.isNotEmpty(new boolean[0]));
        assertFalse(Utils.isNotEmpty(new char[0]));
    }

    @Test
    public void oneNull() {
        assertTrue(Utils.oneNull("a",null));
        assertTrue(Utils.oneNull(null,"a"));
        assertFalse(Utils.oneNull(null,null));
        assertFalse(Utils.oneNull("a","a"));
    }

    @Test
    public void oneEmpty() {
        assertTrue(Utils.oneEmpty("","a"));
        assertTrue(Utils.oneEmpty("a",""));
        assertFalse(Utils.oneEmpty("",null));
        assertFalse(Utils.oneEmpty("a","a"));
    }

    @Test
    public void splitOmitEmpty() {
        List<String> strings = Utils.splitOmitEmpty(",, a ,b ,\r \t,c,,", ",");
        assertTrue(strings.size()==3);
        assertTrue(strings.containsAll(Arrays.asList("a","b","c")));
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void isEmpty1() {
    }

    @Test
    public void isEmpty2() {
    }

    @Test
    public void isEmpty3() {
    }

    @Test
    public void isEmpty4() {
    }

    @Test
    public void isEmpty5() {
    }

    @Test
    public void isEmpty6() {
    }

    @Test
    public void isEmpty7() {
    }

    @Test
    public void isEmpty8() {
    }

    @Test
    public void isEmpty9() {
    }

    @Test
    public void isEmpty10() {
    }

    @Test
    public void isEmpty11() {
    }

    @Test
    public void isNoneNull() {
    }

    @Test
    public void isNoneEmpty() {
    }

    @Test
    public void isEmpty12() {
    }
}