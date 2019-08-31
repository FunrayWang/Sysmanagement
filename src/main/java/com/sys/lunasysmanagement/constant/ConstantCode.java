package com.sys.lunasysmanagement.constant;

import java.util.HashMap;

/**
 * 配置类
 *
 * @author wangfangrui
 * @date 2019/8/21 17:10
 */
public class ConstantCode {


    public static final String COOKIE_TOKEN = "cookieToken";
    public static final String COOKIE_USERID = "cookieUserId";
    public static final int COOKIE_EXPIRED_TIME = 1800;
    public static final long REDIS_EXPIRED_TIME = 1800L;
    public static int FIRST_LOGIN = 1;
    public static int ORG_CODE = 0;
    public static int PERSON_CODE = 1;
    /**
     * 默认分页大小
     */
    public static int DEFAULT_PAGE_SIZE = 10;
    /**
     * 默认页数
     */
    public static int DEFAULT_PAGE = 1;

    public static String[] USER_COLUMN_NAME = {"账户", "用户类型", "用户名称", "联系人", "联系电话", "状态", "TPS", "存储空间", "创建时间"};
    public static String USER_SHEET_NAME = "用户信息导出";
    public static String[] AUDIT_COLUMN_NAME = {"邮箱地址", "账户类型", "名称", "审核状态", "审核人员", "审核时间", "申请时间"};
    public static String AUDIT_SHEET_NAME = "审核信息导出";
    public static Integer APPROVE_PEND = 1;

    public static HashMap<Integer, String> stateMap = new HashMap<>();
    public static HashMap<Integer, String> auditMap = new HashMap<>();

    static {
        stateMap.put(0, "启用");
        stateMap.put(1, "禁用");
        auditMap.put(1, "待审核");
        auditMap.put(2, "审核拒绝");
        auditMap.put(3, "审核通过");
    }
}
