package com.pkyapp.jinzaizhijianmerchant.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证
 * 
 * @author slg
 * 
 */
public class MatcherUtils {
	//private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	private final static Pattern phoneNum = Pattern
			.compile("^([1][3][0-9]{1}|[1][5][0-9]{1}|[1][8][0-9]{1}|[1][4][0-9]{1})[0-9]{8}$");
	private final static Pattern isHttp = Pattern.compile("/^(http://)/i");
	// 定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
	private final static Pattern isIdentityCard = Pattern
			.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0){
			return false;	
		}
			
		Pattern emailer  =Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		return emailer.matcher(email).matches();
	}
	/**
	 * 验证是否为空
	 * @param text
	 * @return
	 */
	public static boolean isEmpty(String text){
		if(text==null){
			return true;
		}
		text = text.trim();
		if(!text.equals("")&&!text.equalsIgnoreCase("null")){
			return false;
		}else{
			return true;
		}
		
		
		
	}
	/**
	 * 是否是一个合法的手机号
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhoneNum(String phone) {
		if (phone == null || phone.trim().length() == 0)
			return false;
		return phoneNum.matcher(phone).matches();
	}

	/**
	 * 验证手机格式
	 */
	public static boolean isMobileNO(String mobiles) {
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
		String telRegex = "[1][34578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (mobiles.trim().equals(""))
			return false;
		else
			return mobiles.matches(telRegex);
	}

	/**
	 * 验证号码 手机号 固话均可
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public static boolean isPhoneOrTelNumberValid(String phoneNumber) {
		boolean isValid = false;
		String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|"
				+ "(^0[3-9]{1}\\d{2}-?\\d{7,8}$)|"
                + "(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|"
				+ "(^0[3-9]{1}\\d{2}-?\\d{7,8}-(\\d{1,4})$))";
		
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(phoneNumber);

		if (matcher.matches()) {
			isValid = true;
		}

		return isValid;

	}

	/**
	 * 是否不为空
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isNotNull(String str) {
		if (str == null || str.trim().length() == 0
				|| str.trim().toUpperCase().equals("NULL")) {
			return false;
		}
		return true;
	}

	/**
	 * 是否是http开头
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isHttp(String url) {
		if (url == null || url.trim().length() == 0)
			return false;
		return isHttp.matcher(url).matches();
	}

	/**
	 * 判断是否是身份证号
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isIdCard(String num) {
		if (num == null || num.trim().length() == 0)
			return false;
		return isIdentityCard.matcher(num).matches();
	}

}
//修改于:2016年7月29日
