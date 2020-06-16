package com.duyj.work.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtil {
	
	//字母数字下划线
	public static final String NAME="^[A-Za-z0-9_]*$";
	
	//字母
	public static final String CHAR_ONLY="^[A-Za-z]*$";
	
	public static final String NUM_ONLY="^[0-9]*$";
	
	//
	public static final String EMAIL="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	
	public static final String PHONE="[0-9]{3,4}//-?[0-9]+";
	
	public static final String URL = "[a-zA-z]+://[^\\s]*";
	
	public static final String CHINESE = "^[\u4e00-\u9fa5]*$";
	
	private RegexUtil(){}
	
	public static boolean match(String str, String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	public static boolean lookingAt(String str, String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.lookingAt();
	}
	
	//todo
	public static boolean find(String str, String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
	
	public static void main(String[] args) {

		String abc="123@222.com";
		
		boolean ss = RegexUtil.match(abc, RegexUtil.EMAIL);
		
		//System.out.println(ss);
		
		Q.p(abc.matches("^123.*"));
		
	}

}
