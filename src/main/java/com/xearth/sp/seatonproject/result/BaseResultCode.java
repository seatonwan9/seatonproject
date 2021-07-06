package com.xearth.sp.seatonproject.result;

import java.io.Serializable;

/**
 * 业务代码消息
 */
public interface BaseResultCode extends Serializable {

	/**
	 * 状态码
	 *
	 * @return Integer
	 */
	Integer getCode();

	/**
	 * 消息
	 *
	 * @return String
	 */
	String getMessage();
}
