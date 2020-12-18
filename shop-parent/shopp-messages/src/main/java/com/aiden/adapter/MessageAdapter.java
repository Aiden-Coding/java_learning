
package com.aiden.adapter;

import com.alibaba.fastjson.JSONObject;

/**
 *
 *
 * @classDesc: 功能描述:(所有消息都会交给他进行妆发)

 * @createTime: 2017年10月25日 上午12:07:08
 * @version: v1.0

 */
public interface MessageAdapter {
	//接受消息
    public void distribute(JSONObject jsonObject);
}
