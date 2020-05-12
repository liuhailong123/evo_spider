package cn.com.evo.cms.utils;

public class ChineseCharUtil {
	/**
	 * 去除中文中的特殊字符（保留数字）
	 * @param chineseStr
	 * @return
	 */
	public static String removeSpecialChar(String chineseStr){
		if(chineseStr == null){
			return "";
		}
		char[] chars = chineseStr.toCharArray();
		String temp = "";
		for (int i = 0; i < chars.length; i++) {
			if ((chars[i] >= 19968 && chars[i] <= 40869) || (chars[i] >= 97 && chars[i] <= 122)
					|| (chars[i] >= 65 && chars[i] <= 90) || (chars[i] >= 48 && chars[i] <= 57)) {
				temp += chars[i];
			}
		}
		return temp;
	}
	
	public static void main(String[] args) {
		String temp = "你!@#$%^&*#%!#$#好0123456789";
		System.out.println(removeSpecialChar(temp));
	}
}
