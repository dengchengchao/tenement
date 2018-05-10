package com.dengchengchao.tenement.utils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author dengchengchao
 * @Time 2018/5/9
 * @Description 自定义的ArrayList<String> 主要为了重载toString
 */
public class TenList<E> extends ArrayList<E> {

    public String toString(){
        StringBuilder result= new StringBuilder();
        for (E str:this){
            result.append(str).append("\r\n");
        }
        return result.toString();
    }


    public TenList(Collection<? extends E> c) {
        super(c);
    }
}
