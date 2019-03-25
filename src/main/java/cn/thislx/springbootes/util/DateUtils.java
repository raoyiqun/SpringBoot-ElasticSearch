package cn.thislx.springbootes.util;

import cn.thislx.springbootes.util.base.Inspection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author binzhao
 * @date 2017年6月9日
 */
public class DateUtils {
	private static Logger log = LoggerFactory.getLogger(DateUtils.class);
	
	 public static Diff getDistanceTime(String str1, String str2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
			Date one = df.parse(str1);
			Date two = df.parse(str2);
			return getDistanceTime(one, two);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	 }
	 
	 /**与当前时间相差的秒数
	 * @param date
	 * @return
	 */
	public static long getSecondSub(Date date){
		Date now = DateUtils.getCurrentDate();
		long interval = (date.getTime() - now.getTime())/1000;
		return interval;
	}
		
	/** 
     * 两个时间相差距离多少天多少小时多少分多少秒 
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00 
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00 
     * @return String 返回值为：xx天xx小时xx分xx秒 
     */  
    public static Diff getDistanceTime(Date one, Date two) {
        long day = 0;  
        long hour = 0;  
        long min = 0;  
        long sec = 0;  
        try {  
           
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
            if(time1<time2) {  
                diff = time2 - time1;  
            } else {  
                diff = time1 - time2;  
            }  
            day = diff / (24 * 60 * 60 * 1000);  
            hour = (diff / (60 * 60 * 1000) - day * 24);  
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);  
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);  
        } catch (Exception e) {
            e.printStackTrace();  
            return null;
        }  
        return new Diff(day, hour , min , sec );  
    }  
    
	public static String getCurrentFormatTime(){
		return getCurrentDate("yyyy-MM-dd HH:mm:ss");
	}
	public static String getCurrentFormatTimestamp(){
		return getCurrentDate("yyyy-MM-dd HH:mm:ss.SSS");
	}
	public static String getCurrentDay(){
		return getCurrentDate("yyyy-MM-dd");
	}
	public static String getCurrentDate(String format){
	    SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式
		return df.format(new Date());
	}
	public static Date strToDay(String strDate) {
		return strToDate(strDate,"yyyy-MM-dd");
	}
	public static Date strToDate(String strDate) {
		return strToDate(strDate,"yyyy-MM-dd HH:mm:ss");
	}
	public static Date strToDate(String strDate, String format) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			Date strtodate = formatter.parse(strDate);
			return strtodate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error("strToDay, --error:{}",e);
		}
		return null;
	}
	public static String getFormatDate(Object date , String format){
	    SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式
		return df.format(date);		
	}
	
	public static String getFormatTime(String time , String format){
		if(Inspection.isBlank(time)){
			return null;
		}
		Date date = strToDate(time);
		SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式
		return df.format(date);		
	}
	
	/**返回当前时间毫秒级
	 * @return
	 */
	public static String getCurrentTimestamp(){
		return new Timestamp(System.currentTimeMillis()).toString();
	}
	public static Date getCurrentDate(){
		return new Date();
//		return RequestHandler.strToDate(getCurrentTimestamp());
	}
	
	/**返回当前时间秒级
	 * @return
	 */
	public static long getCurrentTime(){
		return  (System.currentTimeMillis()/1000);
	}
	
	/**获取当前小时（24小时制）
	 * @return
	 */
	public static long getHour24(){
		Date today=new Date();
	    SimpleDateFormat f=new SimpleDateFormat("HH");
	    String time=f.format(today);
	    return Long.valueOf(time);
	}
	/**获取当天日期
	 * @return
	 */
	public static Date getToDay(){
		Date today=new Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		String time=f.format(today);
		return strToDay(time);
	}
	
	/**当前的日期加n天 
	 * @return
	 */
	public static Date toDayAddDay(int day){
		Calendar c = Calendar.getInstance();
		c.setTime(getToDay());
		c.add(Calendar.DAY_OF_WEEK, day); // 当前的时间加n天
		return c.getTime();
	}
	
	 /**时间差(天)，
	 * @param beforeDateStr	早些时间 如：昨天time
	 * @param NowDateStr	现在时间 如：今天time 
	 * @param beforeFormat	早些时间 如：yyyyMMdd
	 * @param nowFormat		现在时间 如：yyyy-MM-dd
	 * @return
	 */
	public static long getDaySub(String beforeDateStr, String NowDateStr, String beforeFormat, String nowFormat){
	        long day=0;
	        java.text.SimpleDateFormat bFormat = new java.text.SimpleDateFormat(beforeFormat);
	        java.text.SimpleDateFormat nFormat = new java.text.SimpleDateFormat(nowFormat);
	        java.util.Date beginDate;
	        java.util.Date endDate;
	        try
	        {
	            beginDate = bFormat.parse(beforeDateStr);
	            endDate= nFormat.parse(NowDateStr);    
	            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);    
	            //System.out.println("相隔的天数="+day);   
	        } catch (Exception e){
	            // TODO 自动生成 catch 块
	        	log.error("getDaySub, --error:{}",e);
	        }   
	        return day;
	}
	
	/** 
     * 两个时间相差相隔多少分 
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00 
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00 
     * @return Long 返回值为：分
     */  
    public static Long getMinTime(String one, String two) {
        long day = 0;  
        long hour = 0;  
        long min = 0;  
        try {  
           
            long time1 = strToDate(one).getTime();  
            long time2 = strToDate(two).getTime();  
            long diff ;  
            if(time1<time2) {  
                diff = time2 - time1;  
            } else {  
                diff = time1 - time2;  
            }  
            day = diff / (24 * 60 * 60 * 1000);  
            hour = (diff / (60 * 60 * 1000) - day * 24);  
            min = ((diff / (60 * 1000)) - day * 24 * 60 );  
        } catch (Exception e) {
            e.printStackTrace();  
            return null;
        }  
        return min;  
    }  
    /** 
     * 两个时间相差相隔多少分 
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00 
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00 
     * @return Long 返回值为：分
     */  
    public static Long getMinTimeByOrder(String one, String two) {
    	long day = 0;  
    	long hour = 0;  
    	long min = 0;  
    	try {  
    		
    		long time1 = strToDate(one).getTime();  
    		long time2 = strToDate(two).getTime();  
    		long diff ;  
//    		if(time1<time2) {  
//    			diff = time2 - time1;  
//    		} else {  
    			diff = time1 - time2;  
//    		}  
    		day = diff / (24 * 60 * 60 * 1000);  
    		hour = (diff / (60 * 60 * 1000) - day * 24);  
    		min = ((diff / (60 * 1000)) - day * 24 * 60 );  
    	} catch (Exception e) {
    		e.printStackTrace();  
    		return null;
    	}  
    	return min;  
    }  
	
	public static void main(String[] args) throws ParseException {
//		  System.out.println(getHour24());
//		test();
//		Date date = DateUtils.addSecond("2013-6-1 13:24:16", 4);
//		System.out.println(date);
//		Date date2 = DateUtils.addSecond(date, 4);
//		System.out.println(date2);
//		
//		System.out.println(DateUtils.getCurrentTime()+"");
		
		System.out.println(getMinTimeByOrder(DateUtils.getCurrentFormatTime(),"2017-8-17 17:25:16.000"));
	}
	
	public static Date addSecond(String time, int second){
		return addSecond(time, second,"yyyy-MM-dd HH:mm:ss");
	}
	/**对指定的时间添加N秒
	 * @param time
	 * @param second
	 * @return
	 * @throws ParseException
	 */
	public static Date addSecond(String time, int second, String format){
		try {
			DateFormat df=new SimpleDateFormat(format);
			Calendar c = Calendar.getInstance();
			c.setTime(df.parse(time));
			c.add(Calendar.SECOND, second); // 目前时间加N秒
			
			return c.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error("addSecond,args:{}(time), {}(second) --error:{}",time,second,e);
		}
		return null;
	} 
	/**对指定的时间添加N秒
	 * @param time
	 * @param second
	 * @return
	 * @throws ParseException
	 */
	public static Date addSecond(Date time, int second){
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(time);
			c.add(Calendar.SECOND, second); // 目前时间加N秒
			
			return c.getTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("addSecond,args:{}(time), {}(second) --error:{}",time,second,e);
		}
		return null;
	}
	
	public static void test() throws ParseException {
//		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");   
//		Calendar c = Calendar.getInstance();   
//		c.setTime(df.parse("2013-6-1 13:24:16.000"));
//		
//		c.add(Calendar.YEAR, -2); // 目前时间减2年   
//		System.out.println(df.format(c.getTime()));   
//		
//		
//		c.add(Calendar.MONTH, 1); // 目前时间加1个月   
//		System.out.println(df.format(c.getTime()));   
//		
//		c.add(Calendar.DAY_OF_WEEK, 7); // 目前的时间加7天   
//		System.out.println(df.format(c.getTime())); 
//		  
//		c.add(Calendar.HOUR, 3); // 目前时间加3小时
//		System.out.println(df.format(c.getTime()));   
//		
//		c.add(Calendar.MINUTE, 3); // 目前时间加3分钟   
//		System.out.println(df.format(c.getTime()));   
//		
//		c.add(Calendar.SECOND, 3); // 目前时间加3秒
//		System.out.println(df.format(c.getTime()));   
//		
//		c.add(Calendar.MILLISECOND, 3); // 目前时间加3秒
//		System.out.println(df.format(c.getTime()));   
		
		System.out.println(getMinTimeByOrder("2013-6-1 13:24:16.000", "2013-6-1 13:24:17.000"));
	}
	public static class Diff{
		private Long day;
		private Long hours;
		private Long min;
		private Long sec;
		
		public Diff(Long day, Long hours, Long min, Long sec) {
			// TODO Auto-generated constructor stub
			this.day = day;
			this.hours = hours;
			this.min = min;
			this.sec = sec;
		}
		
		public Long getHours() {
			return hours;
		}
		public void setHours(Long hours) {
			this.hours = hours;
		}
		public Long getDay() {
			return day;
		}
		public void setDay(Long day) {
			this.day = day;
		}
		public Long getMin() {
			return min;
		}
		public void setMin(Long min) {
			this.min = min;
		}
		public Long getSec() {
			return sec;
		}
		public void setSec(Long sec) {
			this.sec = sec;
		}

		@Override
		public String toString() {
			return "Diff [day=" + day + ", hours=" + hours + ", min=" + min
					+ ", sec=" + sec + "]";
		}
	}
}
