package com.pkyapp.jinzaizhijianmerchant.util;

import android.widget.EditText;
import android.widget.TextView;



public class ValidateUtil {

	/**
	 * Edittext 不为空
	 * @param view
	 * @param name
	 * @return
	 */
	public  static boolean  inNotNull(EditText view,String name){
		String str = view.getText().toString();
		if(MatcherUtils.isEmpty(str)){
			view.setError("请输入"+name);
			view.setFocusable(true);
			view.requestFocus();
			return false;
		}else{
			return true;
		}
		
	}
	/**
	 * Edittext 不为空
	 * @param view
	 * @param name
	 * @return
	 */
	public  static boolean  inNotNull(TextView view, String name){
		String str = view.getText().toString();
		if(MatcherUtils.isEmpty(str)){
			view.setError(name+"不能为空");
			view.setFocusable(true);
			view.requestFocus();
			return false;
		}else{
			return true;
		}

	}
	/**
	 * 验证是否为正确手机号码
	 * @param view
	 * @return
	 */
	public static boolean isPhoneNum(EditText view){
		String str = view.getText().toString();
		if(MatcherUtils.isMobileNO(str)){
			return true;
		}else{
			view.setError("请正确输入手机号");
			view.setFocusable(true);
			view.requestFocus();
			return false;
		}
	}
	/**
	 * 验证是否为正确手机号码
	 * @param view
	 * @return
	 */
	public static boolean isPhoneNum(TextView view){
		String str = view.getText().toString();
		if(MatcherUtils.isMobileNO(str)){
			return true;
		}else{
			view.setError("请正确输入手机号");
			view.setFocusable(true);
			view.requestFocus();
			return false;
		}
	}
	/**
	 * 验证是否正确
	 * @param view
	 * @param okStr
	 * @param name
	 * @return
	 */
	public  static boolean  verify(EditText view,String okStr,String name){
		String str = view.getText().toString();
		if(str!=null&&!str.equals(okStr)){
			view.setError(name+"错误");
			view.setFocusable(true);
			view.requestFocus();
			return false;
		}else{
			return true;
		}
		
	}
	/**
	 * 验证身份证
	 * @param view
	 * @return
	 */
	public  static boolean isId(EditText view){
		String str = view.getText().toString();
		if(MatcherUtils.isIdCard(str)){
			return true;
		}else{
			view.setError("请输入正确的身份证号");
			view.setFocusable(true);
			view.requestFocus();
			return false;
		}

	}
}
//修改于:2016年2月2日
