package cn.truthseeker.container.safe;

import cn.truthseeker.util.Emptys;

import java.util.Map;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/21
 */
public interface CommonNoneNullMapOper<K, V> extends CommonMapOper<K, V> {

    default boolean putIgnoreNull(K key, V value) {
        if(Emptys.isNoneNull(key, value)){
            put(key, value);
            return true;
        }else{
            return false;
        }
    }

    default void putAllIgnoreNull(Map<K, V> map) {
        map.forEach(this::putIgnoreNull);
    }
}
