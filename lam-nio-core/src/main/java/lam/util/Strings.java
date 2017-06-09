package lam.util;
/**
* <p>
* Strings extends org.apache.commons.lang3.StringUtils
* </p>
* @author linanmiao
* @date 2017年4月13日
* @version 1.0
*/
public class Strings extends org.apache.commons.lang3.StringUtils{

	public static int intValue(String value, int defaultValue){
		try{
			return Integer.valueOf(value);
		}catch(NumberFormatException n){
			return defaultValue;
		}
	}
	
	public static boolean allNull(String ...strings){
		for(String string : strings){
			if(string == null){
				return false;
			}
		}
		return true;
	}
	
	public static boolean existsNull(Object ...objects){
		for(Object object : objects){
			if(object == null){
				return true;
			}
		}
		return false;
	}
	
}
