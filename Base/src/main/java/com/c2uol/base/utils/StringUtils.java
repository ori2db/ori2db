package com.c2uol.base.utils;

import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @描述: 字符效验工具
 * @作者: Lyon
 * @版本: v1.0
 * @时间: 2017年7月27日下午10:23:13
 *
 */
public class StringUtils {

	private StringUtils() {

	}

	/**
	 * 
	 * @描述: 字符为空
	 * @参数: @param str
	 * @参数: @return
	 * @返回值: boolean
	 * @版本: v1.0
	 * @时间: 2017年7月27日下午10:25:11
	 *
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @描述: 字符为数字
	 * @参数: @param str
	 * @参数: @return
	 * @返回值: boolean
	 * @版本: v1.0
	 * @时间: 2017年7月27日下午10:25:17
	 *
	 */
	public static boolean isInteger(String str) {
		if (isEmpty(str)) {
			return false;
		}
		if (!str.matches("\\d+")) {
			return false;
		}
		return true;
	}
	
	 /**
     * 将集合组装成字符串
     * for example: str1,str2,str3
     * 中间使用逗号（，）分隔
     * @param e 实现Collection 的集合类型
     * @return
     */
    public String parseArray2str(Collection<String> e){

        if(e instanceof  Collection == false){
            throw new RuntimeException("params is not implement Interface Collection . ");
        }

        StringBuilder sb = new StringBuilder();

        if (e.size() <= 0  ) return  null;

        Iterator<String> it = e.iterator();
        while (it.hasNext()){
            sb.append(it.next());
            if(it.hasNext()){
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
