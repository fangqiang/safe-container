package cn.truthseeker.container.safe;

import cn.truthseeker.container.safe.list.SafeArrayList;
import cn.truthseeker.container.safe.list.SafeList;
import cn.truthseeker.container.safe.map.Maps;
import cn.truthseeker.container.safe.map.SafeHashMap;
import cn.truthseeker.container.safe.map.SafeMap;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/15
 */
public final class Safes {

    public static <K,V> SafeMap<K,V> newSafeMap(){
        return new SafeHashMap();
    }

    public static <K,V>  SafeMap<K,V> newSafeMapOmitNull(Map<K,V> map){
        return Maps.filterByKeyValue(map, (k,v) -> Utils.isNoneNull(k,v), SafeHashMap::new);
    }

    public static <K,V>  SafeMap<K,V> newSafeMapOmitEmpty(Map<K,V> map){
        return Maps.filterByKeyValue(map, (k,v) -> Utils.isNoneEmpty(k,v), SafeHashMap::new);
    }

    public static <E>  SafeList<E> newSafeList(){
        return new SafeArrayList<>();
    }

    public static <E>  SafeList<E> newSafeListOmitNull(List<E> list){
        return list.stream().filter(Objects::nonNull).collect(Collectors.toCollection(SafeArrayList::new));
    }

    public static <E>  SafeList<E> newSafeListOmitEmpty(List<E> list){
        return list.stream().filter(Utils::isNotEmpty).collect(Collectors.toCollection(SafeArrayList::new));
    }
}
