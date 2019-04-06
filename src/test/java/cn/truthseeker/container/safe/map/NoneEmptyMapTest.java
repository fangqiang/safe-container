package cn.truthseeker.container.safe.map;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/4/5
 */
public class NoneEmptyMapTest {

    @Test
    public void putAll() {
        NoneEmptyMap map = new NoneEmptyMap();
        map = new NoneEmptyMap(new HashMap());
    }

    @Test
    public void put() {
        NoneEmptyMap map = new NoneEmptyMap();
        TestUtil.withException(()-> map.put("a",""));
        TestUtil.withException(()-> map.put("a",null));
        TestUtil.withException(()-> map.put("a", new byte[0]));
        TestUtil.noException(()-> map.put("a", new byte[1]));

        TestUtil.withException(()-> map.putAll(Maps.of("a","")));
        TestUtil.noException(()-> map.putAll(Maps.of("a","a")));

    }

    @Test
    public void getNullable() {
        NoneEmptyMap map = new NoneEmptyMap();
        map.getNullable("a");
    }

    @Test
    public void newInstance() {
        NoneEmptyMap map = new NoneEmptyMap();
        map.newInstance();
    }

    @Test
    public void assertTrue() {
        NoneEmptyMap map = new NoneEmptyMap(Maps.of("a", "a"));
        TestUtil.noException(()->map.assertTrue((k,v)-> k.equals("a"), "error occur"));
        TestUtil.withException(()->map.assertTrue((k,v)-> !k.equals("a"), "error occur"));
    }
}