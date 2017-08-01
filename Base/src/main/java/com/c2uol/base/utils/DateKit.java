package com.c2uol.base.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.c2uol.enums.DateEnum;


/**
 * 时间日期工具类
 * @author jeanbinjean
 *
 */
public class DateKit {
	
	 
	
	 private static final String PATTERN = "yyyy-MM-dd hh:mm:ss";
	 

	    public static String getCurrentDate(String pattern){
	        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	        Date date = new Date(System.currentTimeMillis());
	        String currentDate =  sdf.format(date);
	        return currentDate;
	    }


	    public static String getCurrentDate(){
	        return getCurrentDate(PATTERN);
	    }

	    /**
	     * 调整日前 ，将 currentDate 进行日期调整
	     * 调整天数为 value 的值
	     * value 为 ＋value 表示日期加上 value 天
	     * value 为 －value 表示获取参数日期多少天之前的日期
	     * @param currentDate
	     * @param value
	     * @return
	     */
	    public static String dateChange(String currentDate , int value){

	        if(StringUtils.isEmpty(currentDate)){
	        		throw new RuntimeException("时间参数不能为空。");
	        }

	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        Date currDate = null;
	        try{
	            currDate = simpleDateFormat.parse(currentDate);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(currDate);
	        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+value);
	        return simpleDateFormat.format(calendar.getTime()) ;
	    }
	    
	    /**
	     * 根据日期枚举 DateEnum
	     * 获取到对应的值
	     * for example:
	     * <code>
	     *    String yearStr =  DateKit.GetCurrentTimeByFlag(DateEnum.YEAR);
	     *    //String monthStr = DateKit.GetCurrentTimeByFlag(DateEnum.MONTH);
	     *    //String dayStr = DateKit.GetCurrentTimeByFlag(DateEnum.DAY);
	     *    System.out.println(yearStr);
	     * </code>
	     * console output:
	     * <pre>
	     *     2017
	     * </pre>
	     * @param dateEnum
	     * @return
	     */
	    public static String GetCurrentTimeByFlag(DateEnum dateEnum) {
	        Calendar calendar = Calendar.getInstance();
	        int value = dateEnum.getValue() == DateEnum.MONTH.getValue() ? 
	                calendar.get(dateEnum.getValue())+1:calendar.get(dateEnum.getValue());
	    		return String.valueOf(value);
	    }

	    public static void main(String[] args) {
	        String str = DateKit.GetCurrentTimeByFlag(DateEnum.DAY);
	        System.out.println(str);
	     
	    }

}
