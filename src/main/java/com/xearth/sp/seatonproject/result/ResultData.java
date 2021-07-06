package com.xearth.sp.seatonproject.result;

import com.xearth.sp.seatonproject.constant.APPConstant;
import com.xearth.sp.seatonproject.utils.ObjectUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Optional;

/**
 * 统一API响应结果封装
 */
@ApiModel(description = "返回信息")
public class ResultData<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "状态码", required = true)
	private int code;
	@ApiModelProperty(value = "是否成功", required = true)
	private boolean success;
	@ApiModelProperty(value = "承载数据")
	private T data;
	@ApiModelProperty(value = "返回消息", required = true)
	private String msg;

	public ResultData() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "R{" +
				"code=" + code +
				", success=" + success +
				", data=" + data +
				", msg='" + msg + '\'' +
				'}';
	}

	private ResultData(BaseResultCode resultCode) {
		this(resultCode, null, resultCode.getMessage());
	}

	private ResultData(BaseResultCode resultCode, String msg) {
		this(resultCode, null, msg);
	}

	private ResultData(BaseResultCode resultCode, T data) {
		this(resultCode, data, resultCode.getMessage());
	}

	private ResultData(BaseResultCode resultCode, T data, String msg) {
		this(resultCode.getCode(), data, msg);
	}

	private ResultData(int code, T data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
		this.success = ResultCode.SUCCESS.code == code;
	}

	/**
	 * 判断返回是否为成功
	 *
	 * @param result Result
	 * @return 是否成功
	 */
	public static boolean isSuccess(@Nullable ResultData<?> result) {
		return Optional.ofNullable(result)
			.map(x -> ObjectUtil.nullSafeEquals(ResultCode.SUCCESS.code, x.code))
			.orElse(Boolean.FALSE);
	}

	/**
	 * 判断返回是否为成功
	 *
	 * @param result Result
	 * @return 是否成功
	 */
	public static boolean isNotSuccess(@Nullable ResultData<?> result) {
		return !ResultData.isSuccess(result);
	}

	/**
	 * 返回R
	 *
	 * @param data 数据
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public static <T> ResultData<T> data(T data) {
		return data(data, APPConstant.DEFAULT_SUCCESS_MESSAGE);
	}

	/**
	 * 返回R
	 *
	 * @param data 数据
	 * @param msg  消息
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public static <T> ResultData<T> data(T data, String msg) {
		return data(APPConstant.SC_OK, data, msg);
	}

	/**
	 * 返回R
	 *
	 * @param code 状态码
	 * @param data 数据
	 * @param msg  消息
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public static <T> ResultData<T> data(int code, T data, String msg) {
		return new ResultData<>(code, data, data == null ? APPConstant.DEFAULT_NULL_MESSAGE : msg);
	}

	/**
	 * 返回R
	 *
	 * @param msg 消息
	 * @param <T> T 泛型标记
	 * @return R
	 */
	public static <T> ResultData<T> success(String msg) {
		return new ResultData<>(ResultCode.SUCCESS, msg);
	}

	/**
	 * 返回R
	 *
	 * @param resultCode 业务代码
	 * @param <T>        T 泛型标记
	 * @return R
	 */
	public static <T> ResultData<T> success(BaseResultCode resultCode) {
		return new ResultData<>(resultCode);
	}

	/**
	 * 返回R
	 *
	 * @param resultCode 业务代码
	 * @param msg        消息
	 * @param <T>        T 泛型标记
	 * @return R
	 */
	public static <T> ResultData<T> success(BaseResultCode resultCode, String msg) {
		return new ResultData<>(resultCode, msg);
	}

	/**
	 * 返回R
	 *
	 * @param msg 消息
	 * @param <T> T 泛型标记
	 * @return R
	 */
	public static <T> ResultData<T> fail(String msg) {
		return new ResultData<>(ResultCode.FAILURE, msg);
	}


	/**
	 * 返回R
	 *
	 * @param code 状态码
	 * @param msg  消息
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public static <T> ResultData<T> fail(int code, String msg) {
		return new ResultData<>(code, null, msg);
	}

	/**
	 * 返回R
	 *
	 * @param resultCode 业务代码
	 * @param <T>        T 泛型标记
	 * @return R
	 */
	public static <T> ResultData<T> fail(BaseResultCode resultCode) {
		return new ResultData<>(resultCode);
	}

	/**
	 * 返回R
	 *
	 * @param resultCode 业务代码
	 * @param msg        消息
	 * @param <T>        T 泛型标记
	 * @return R
	 */
	public static <T> ResultData<T> fail(BaseResultCode resultCode, String msg) {
		return new ResultData<>(resultCode, msg);
	}

	/**
	 * 返回R
	 *
	 * @param flag 成功状态
	 * @return R
	 */
	public static <T> ResultData<T> status(boolean flag) {
		return flag ? success(APPConstant.DEFAULT_SUCCESS_MESSAGE) : fail(APPConstant.DEFAULT_FAILURE_MESSAGE);
	}

}
