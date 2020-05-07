package com.sucifitz.myshop.commons.utils;
 
/**
 *
 * @author Sucifitz
 * @create 2020/3/29 20:03
 */
public class RegexpUtils {

    /**
     * 验证手机号
     */
    public static final String PHONE = "/\\^(((13[0-9]{1})|(15[0-9]{1}))+\\d{8})\\$/";

    /**
     * 验证邮箱地址
     */
    public static final String EMAIL = "^[a-z0-9A-Z]+[- |a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-z]{2,}$";

    /**
     * 验证手机号
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        return phone.matches(PHONE);
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        return email.matches(EMAIL);
    }
}