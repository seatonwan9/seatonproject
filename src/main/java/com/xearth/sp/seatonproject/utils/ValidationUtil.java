package com.xearth.sp.seatonproject.utils;

/**
 * 校验工具类
 */
public class ValidationUtil {

    /**
     * 长度校验
     *
     * @param data
     * @param length
     * @return
     */
    public static boolean lengthValidation(String data, int length) {
        if(data.length() > length) {
            return true;
        }
        return false;
    }

    /**
     * 数值校验
     *
     * @param num
     * @return
     */
    public static boolean numberValidation(String num) {
        try {
            Double.valueOf(num);
        } catch (Exception ex) {
            return true;
        }
        return false;
    }

    /**
     * 小数位校验
     *
     * @param num
     * @return
     */
    public static boolean decimalPlacesValidation(String num, int length) {
        String[] numArr = num.split("\\.");
        if (numArr.length > 1 && numArr[1].length() > length) {
            return true;
        }
        return false;
    }

    /**
     * 大于0的整数校验
     *
     * @param num
     * @return
     */
    public static boolean integerValidation(String num) {
        String regex = "^0$|^[1-9]\\d*$";
        return num.matches(regex);
    }

    /**
     * 非负数校验
     *
     * @param num
     * @return
     */
    public static boolean notNegativeValidation(String num) {
        String regex = "^\\d+(\\.\\d+)?$";
        return num.matches(regex);
    }
}
