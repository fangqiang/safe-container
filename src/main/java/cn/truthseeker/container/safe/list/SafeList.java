package cn.truthseeker.container.safe.list;

import cn.truthseeker.container.util.Emptys;

import java.util.List;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public interface SafeList<E> extends List<E> {
    default void addIgnoreNull(E e){
        if(e != null){
            add(e);
        }
    }

    default void addIgnoreEmpty(E e){
        if(Emptys.isNotEmpty(e)){
            add(e);
        }
    }

    default SafeList<E> cleanEmpty(){
        removeIf(Emptys::isEmpty);
        return this;
    }
}