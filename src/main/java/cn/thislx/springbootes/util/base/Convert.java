package cn.thislx.springbootes.util.base;

import cn.thislx.springbootes.util.DateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

/**数据转换辅助类（如：数据字典id转换为名称、数据二次整理等）
 * @author binzhao
 * @date 2017年3月24日
 */
public class Convert {
	public static String toString(Integer str){
		return null == str ? null : str.toString();
	}
	public static String toString(Long str){
		return null == str ? null : str.toString();
	}
	public static String toString(Double str){
		return null == str ? null : str.toString();
	}
	public static Integer toInteger(Map<String, Object> map, String key){
		if(null == map){
			return null;
		}
		Object objValue = map.get(key);
		return null != objValue && Inspection.isInteger(objValue.toString()) ? Integer.valueOf(objValue.toString()) : null;
	}
	public static Long toLong(Map<String, Object> map, String key){
		if(null == map){
			return null;
		}
		Object objValue = map.get(key);
		return null != objValue && Inspection.isInteger(objValue.toString()) ? Long.valueOf(objValue.toString()) : null;
	}
	public static Double toDouble(Map<String, Object> map, String key){
		if(null == map){
			return null;
		}
		Object objValue = map.get(key);
		return null != objValue && Inspection.isDouble(objValue.toString()) ? Double.valueOf(objValue.toString()) : null;
	}
	public static String toString(Map<String, Object> map, String key){
		if(null == map){
			return null;
		}
		Object objValue = map.get(key);
		return null != objValue && Inspection.isNotBlank(objValue.toString()) ? objValue.toString() : null;
	}
	public static Boolean toBoolean(String str){
		return Inspection.isNotBlank(str) && str.equals("true") ? Boolean.valueOf(str) : false;
	}
	public static Integer toInteger(String str){
		return Inspection.isNotBlank(str) && Inspection.isInteger(str) ? Integer.valueOf(str) : null;
	}
	public static Long toLong(String str){
		return Inspection.isNotBlank(str) && Inspection.isInteger(str) ? Long.valueOf(str) : null;
	}

	public static String convertTimeS(Object dateTime) {
		return  null != dateTime ? DateUtils.getFormatDate(dateTime,"yyyy-MM-dd HH:mm:ss"):null;
	}
	public static String convertDay(Date day) {
		return  null != day ?DateUtils.getFormatDate(day,"yyyy-MM-dd"):null;
	}
	public static String convertDay(String day) {
		return  null != day ?DateUtils.getFormatDate(DateUtils.strToDay(day),"yyyy-MM-dd"):null;
	}
	public static void convertLength(List<Map<String, Object>> list, String key, int saveLength) {
		if(null != list && list.size()>0){
			for (Map<String, Object> map : list) {
				Convert.convertLength(map, key, saveLength);
			}
		}
	}
	public static void convertLength(Map<String, Object> agentMap, String key, int saveLength) {
		if(null == agentMap){
			return;
		}
		if(null != agentMap.get(key) && Inspection.isNotBlank(agentMap.get(key).toString())){
			String desc = agentMap.get(key).toString();
			if(desc.length()>saveLength){
				agentMap.put("c"+key,desc.substring(0, saveLength)+"..");
			}else{
				agentMap.put("c"+key,desc);
			}
		}else{
			agentMap.put("c"+key,"无");
		}
	}
	

	
	public static void convertFormatTime(List<Map<String,Object>> list, String format, String formatTip, String... keys) {
		if(null == list && null != keys){
			return;
		}
		for (Map<String,Object> map : list) {
			convertFormatTime(map,format,formatTip, keys);
		}
	}
	
	/**时间转换为（某天前，某个月前），沿用原有key名称
	 * @param
	 * @param keys
	 */
	public static void convertFormatTime(Map<String, Object> map, String format, String formatTip, String... keys) {
		if(null == map){
			return;
		}
		Object keyValue;
		for (String key : keys) {
			keyValue = map.get(key);
			if(null != keyValue){
				map.put("c"+key,DateUtils.getFormatDate(keyValue, format));
				map.put(key,DateUtils.getFormatDate(keyValue, formatTip));
			}else{
				map.put(key, "-");
			}
		}
	}
	 
	public static List<Map<String, Object>> convertToNewList(List<Map<String, Object>> list, String[] orderTitle, String[] newTitle){
		if(null == list || list.size()==0){
			return null;
		} 
		List<Map<String, Object>> resultList = new LinkedList<Map<String, Object>>();
		Map<String, Object> temp = null;
		for (Map<String, Object> map : list) {
			temp = new HashMap<String, Object>();
			for (int i=0;i<orderTitle.length;i++) {
				temp.put(newTitle[i],map.containsKey(orderTitle[i])? map.get(orderTitle[i]):null);
			}
			resultList.add(temp);
		}
		return resultList;
	}
	public static Map<String, Object> convertToNewList(Map<String, Object> map, String[] orderTitle, String[] newTitle){
		if(null == map || map.size()==0){
			return null;
		} 
		Map<String, Object> temp = new HashMap<String, Object>();
		for (int i=0;i<orderTitle.length;i++) {
			temp.put(newTitle[i], map.get(orderTitle[i]));
		}
		return temp;
	}

	public static String convetNameToSimple(List<Map<String,Object>> dataList, String key, String delStr){
		if(null != dataList && dataList.size()>0){
			String ids = null;
			StringBuffer buf = new StringBuffer();
			for (Map<String, Object> map : dataList) {
				ids = null == map.get(key)? null : map.get(key).toString();
				if(Inspection.isNotBlank(ids)){
					map.put("s"+key, ids.replace(delStr, ""));
				}
			}
			return Inspection.isNotBlank(buf.toString())?buf.toString().trim():null;
		}
		return null;
	}
	
	public static String convetNamesToStr(List<Map<String,Object>> dataList, String key){
		if(null != dataList && dataList.size()>0){
			String ids = null;
			StringBuffer buf = new StringBuffer();
			for (Map<String, Object> map : dataList) {
				ids = null == map.get(key)? null : map.get(key).toString();
				if(null != ids){
					buf.append(ids);
					buf.append(" ");
				}
			}
			return Inspection.isNotBlank(buf.toString())?buf.toString().trim():null;
		}
		return null;
	}

 
	public static void convertStringToMap(Map<String, Object> map, String key, String sp){
		if(null == map){
			return;
		}
		String value = null;
		Map<String, Object> strMap = new LinkedHashMap<String, Object>();
		value = null != map.get(key)?map.get(key).toString():null;
		if(null != value){
			String line = null;
			String tempName = null;
			String tempValue = null;
			BufferedReader dr = new BufferedReader(new StringReader(value));
			try {
				line = dr.readLine();
				while(line!= null){ 
					if(line.indexOf(sp)!=-1){
						tempName = line.substring(0, line.indexOf(sp));
						tempValue = line.substring(line.indexOf(sp)+1 );
						if(null !=tempName && !tempName.trim().equals("") && null !=tempValue && !tempValue.trim().equals("")){
							strMap.put(tempName, tempValue);
						}
					}
					line = dr.readLine();
				} 
				if(strMap.size()>0){
					map.put(key+"Map", strMap);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
 
	public static String convertDecompositionToOne(String str, String sp){
		if(null == str){
			return null;
		}
		String[] strArray = str.split(sp);
		if(null !=strArray[0] && !strArray[0].trim().equals("") ){
			return strArray[0];
		}
		return null;
	}
	public static List<String> convertDecomposition(String decompostionStr, String sp){
		if(null == decompostionStr || decompostionStr.trim().equals("")){
			return null;
		} 
		List<String> spTemp = new LinkedList<String>();
		String[] strArray = decompostionStr.split(sp);
		 for (int i =0;i<strArray.length;i++) {
			if(null !=strArray[i] && !strArray[i].trim().equals("") ){
				spTemp.add(strArray[i]);
			}
		}
		return spTemp;
	}

	public static void convertNameEncodeUrl(List<Map<String,Object>> list, String name) {
		if(null == list && null != name){
			return;
		}
		try {
			for (Map<String,Object> map : list) {
				if(null != map.get(name)){
						map.put("u"+name, URLEncoder.encode(map.get(name).toString(),"utf-8"));
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void convertNameEncodeUrl(Map<String,Object> map, String name) {
		if(null == map && null != name){
			return;
		}
		try {
				if(null != map.get(name)){
					map.put("u"+name, URLEncoder.encode(map.get(name).toString(),"utf-8"));
				}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void convertEncodeUrl(List<Map<String,String>> list, String name) {
		if(null == list && null != name){
			return;
		}
		try {
			for (Map<String,String> map : list) {
				if(null != map.get(name)){
						map.put("u"+name, URLEncoder.encode(map.get(name).toString(),"utf-8"));
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void convertEncodeUrl(Map<String,String> map, String name) {
		if(null == map && null != name){
			return;
		}
		try {
				if(null != map.get(name)){
					map.put("u"+name, URLEncoder.encode(map.get(name).toString(),"utf-8"));
				}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
	public static String convertEncode(String word) {
		// TODO Auto-generated method stub
		if(Inspection.isBlank(word)){
			return null;
		}
		try {
			return URLEncoder.encode(word,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void convertNull(List<Map<String,Object>> list, String name) {
		convertNull(list, name,"-");
	}
	
	/**
	 * @param list
	 * @param name
	 * @param value
	 * @return list内的数据此属性值是否都未null
	 */
	public static boolean convertNull(List<Map<String,Object>> list, String name, Object value) {
		if(null == list && null != name){
			return true;
		}
		boolean isAllNull = true;
		try {
			for (Map<String,Object> map : list) {
				if(null == map.get(name) || Inspection.isBlank(map.get(name).toString())){
						map.put(name,value);
				}else{
					isAllNull = false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAllNull;
	}
	public static int convertNullReturnCount(List<Map<String,Object>> list, String name, Object value) {
		if(null == list && null != name){
			return 0;
		}
		int nullCount = 0;
		try {
			for (Map<String,Object> map : list) {
				if(null == map.get(name) || Inspection.isBlank(map.get(name).toString())){
					map.put(name,value);
					nullCount++;
				} 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nullCount;
	}
	
	public static String convertMToHour(String seconds, String defaultValue){
		try {
			if(Inspection.isDOUBLE_NEGATIVE(seconds)&& Double.valueOf(seconds)>0){
				int i = (int)(Double.valueOf(seconds)/3600);
				return String.valueOf(i<1?1:i);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defaultValue;
	}
	public static String convertMToMinutes(String seconds){
		try {
			if(Inspection.isDOUBLE_NEGATIVE(seconds) && Double.valueOf(seconds)>0){
				int i = (int)((Double.valueOf(seconds))/60);
				return String.valueOf(i<1?1:i);
			};
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String convertMPercentage(String value, String defaultValue){
		try {
			if(Inspection.isDOUBLE_NEGATIVE(value)){
				DecimalFormat df = new DecimalFormat("0.00");
				String CNY = df.format(Double.valueOf(value)*100);
				return CNY+"%";
			};
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defaultValue;
	}
	
	public static void main(String[] args) throws ParseException {
		
//		 Date date = new SimpleDateFormat("yyyy-MM-dd HH").parse("2016-10-18 18:00:00"); 
//		  String sd = new SimpleDateFormat("yyyy-MM-dd HH").format(date);
//		 System.out.println(sd);
		
//		String d  = "0.9806";
//		System.out.println(Double.valueOf(d)*100);
		System.out.println(DateUtils.getDistanceTime("2017-04-24 13:54:51.523000","2017-04-24 12:51:51.523000"));
	}
 
}
