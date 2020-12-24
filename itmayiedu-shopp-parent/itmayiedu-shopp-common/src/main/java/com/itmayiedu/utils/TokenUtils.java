package com.itmayiedu.utils;

import java.util.UUID;

import com.itmayiedu.constants.Constants;

public class TokenUtils {
 
	
	 public static String getToken(){
		 return Constants.MEMBER_TOKEN+UUID.randomUUID();
	 }
	
	
}
