package com.zhy.http.okhttp.callback;
/**
	 * 获取请求地址url响应接口
	 * 
	 * @author Administrator
	 * 
	 */
	public abstract class OnGetRequestUrl {
		
		public abstract void onSuccess(String urlString);

		public void onFail(Throwable reason, String e){};
		
		public void onFinish(){};
	}
//修改于:2016年2月2日
