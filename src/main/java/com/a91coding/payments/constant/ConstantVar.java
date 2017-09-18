package com.a91coding.payments.constant;

public class ConstantVar {
    public final static String ADMIN_USER_LIST = "/admin/user/list";
    public final static String ADMIN_USER_UPDATE = "/admin/user/update";
    public final static String ADMIN_USER_DETAIL = "/admin/user/detail";
    public static final byte OP_STATUS_NEW = (byte) 1;//状态 1：新增
    public static final byte OP_STATUS_UPDATE = (byte) 2;//状态 2：更新
    public static final byte OP_STATUS_FINISH = (byte) 3;//状态 3：完成
    public static final String VERYFY_CODE_KEY = "verify_code";//图片校验码
}
