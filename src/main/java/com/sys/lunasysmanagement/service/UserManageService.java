package com.sys.lunasysmanagement.service;

import com.sys.lunasysmanagement.constant.Result;
import com.sys.lunasysmanagement.model.param.EditParam;
import com.sys.lunasysmanagement.model.param.UserQueryParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is description.
 *
 * @author wangfangrui
 * @date 2019/8/22 17:14
 */
public interface UserManageService {
    /**
     * 灵活查询公司用户信息
     */
    Result userSearch(UserQueryParam userQueryParam);

    /**
     * 编辑用户信息
     */
    Result editUser(EditParam editParam, HttpServletRequest request);

    /**
     * 用户excel的导出
     */
    Result userExport(UserQueryParam userQueryParam, HttpServletResponse response);

    /**
     * 逻辑删除
     */

    Result deleteUser(String userId);

}
