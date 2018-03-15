package com.ue.entity;

/**
 * @auther: 作者 dzc
 * @description: 类说明 参数定值枚举 可以根据需要增加，只能增加不能修改
 * @Date: created in 18:46 2017/11/10
 */
public enum ParameterEnum {

    /** 定值0   */
    ZERO("0"),

    /** 定值1   */
    ONE("1"),

    /**定值2    */
    TWO("2"),

    /** 请求成功   */
    requestSuccess("请求成功"),

    /** 请求失败   */
    requestFail("请求失败"),

    /** 请求成功   */
    delSuccess("删除成功"),

    /** 请求失败   */
    delFail("删除失败"),

    /** 请求成功   */
    updateSuccess("更新成功"),

    /** 请求失败   */
    updateFail("更新失败"),

    /** 请求成功   */
    addSuccess("新增成功"),

    /** 请求失败   */
    addFail("新增失败"),

    /** 定值1   */
    loginSuccess("登录成功"),

    /** 定值1   */
    loginFail("登录失败"),

    /** 定值0   */
    tokenFail("ToKen验证失败"),

    uploadSuccess("上传成功"),

    uploadFail("上传失败")
    ;




    ParameterEnum(String des){
        this.des = des;
    }

    private String des;

    private Integer intDes;

    public String getDes() {
        return des;
    }

}
