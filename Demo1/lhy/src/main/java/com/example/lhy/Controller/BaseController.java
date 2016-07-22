package com.example.lhy.Controller;

import android.content.Context;

import com.example.lhy.IModelChangedListener;


/**
 * @author Serena
 * @time 2016/7/17  13:55
 * @desc
 */

public abstract class BaseController {

	protected IModelChangedListener mListener;
	protected Context mContext;
	public BaseController(Context mContext) {
		this.mContext = mContext;
	}

	public void setListener(IModelChangedListener listener) {
		this.mListener = listener;
	}

	public void sendAsyncMessage(final int action,final Object... values){
		new Thread(){
			public void run() {
				handlerMessage(action, values);
			}
		}.start();
	}

	protected abstract void handlerMessage(int action,Object... values);
	
}
