package com.sys.lunasysmanagement.service.impl;

import com.sys.lunasysmanagement.config.ExcelExport;
import com.sys.lunasysmanagement.constant.CommonMethod;
import com.sys.lunasysmanagement.constant.ConstantCode;
import com.sys.lunasysmanagement.constant.ResponseCode;
import com.sys.lunasysmanagement.constant.Result;
import com.sys.lunasysmanagement.mapper.UserMapper;
import com.sys.lunasysmanagement.model.param.EditParam;
import com.sys.lunasysmanagement.model.param.UserDisplayParam;
import com.sys.lunasysmanagement.model.param.UserQueryParam;
import com.sys.lunasysmanagement.service.UserManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户管理相关方法.
 *
 * @author wangfangrui
 * @date 2019/8/22 17:15
 */
@Slf4j
@Service
public class UserManageImpl implements UserManageService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    ExcelExport excelExport;

    /**
     * 对普通用户进行灵活查询
     */
    @Override
    public Result userSearch(UserQueryParam userQueryParam) {
        /**
         * 判断是否有分页数据传入,若没有,则采用默认分页.当前页码:1,页面大小:10
         * */
        if (userQueryParam.getCurrentPage() == null || userQueryParam.getPageSize() == null) {
            userQueryParam.setCurrentPage(ConstantCode.DEFAULT_PAGE);
            userQueryParam.setPageSize(ConstantCode.DEFAULT_PAGE_SIZE);
        }
        int currentPage = userQueryParam.getCurrentPage();
        int pageSize = userQueryParam.getPageSize();
        /**
         * 计算页面数据的起始条数
         * */
        userQueryParam.setStartItemNo(pageSize * (currentPage - 1));
        try {
            List<UserDisplayParam> allUserList = userMapper.searchAll(userQueryParam);
            return Result.success(allUserList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResponseCode.SEARCH_FAILED);
        }

    }

    /**
     * 编辑用户信息
     *
     * @param editParam
     */
    @Override
    public Result editUser(EditParam editParam, HttpServletRequest request) {
        try {
            if (editParam.getTps() != null || editParam.getStorage() != null) {
                userMapper.updateService(editParam);
            }
            /**
             * 判断是否改为启用/禁用
             * */
            if (editParam.getState() != null && (editParam.getState() == ConstantCode.ORG_CODE || editParam.getState() == ConstantCode.PERSON_CODE)) {
                String adminId = CommonMethod.getUserId(request);
                if (adminId == null) {
                    return Result.fail(ResponseCode.NOT_LOGIN);
                }
                String currentTime = CommonMethod.getCurrentTime();
                String content = ConstantCode.stateMap.get(editParam.getState());
                String userId = editParam.getUserId();
                userMapper.insertLog(adminId, userId, content, currentTime);

            }
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResponseCode.UPDATE_FAILED);
        }
    }

    @Override
    public Result userExport(UserQueryParam userQueryParam, HttpServletResponse response) {
        userQueryParam.setPageSize(null);
        userQueryParam.setCurrentPage(null);
        userQueryParam.setStartItemNo(null);
        try {
            List<UserDisplayParam> allUserList = userMapper.searchAll(userQueryParam);
            excelExport.export(ConstantCode.USER_COLUMN_NAME, ConstantCode.USER_SHEET_NAME, allUserList, response);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResponseCode.EXPORT_FAILED);
        }
    }

    /**
     * 逻辑删除
     *
     * @param userId
     */
    @Override
    public Result deleteUser(String userId) {
        try {
            userMapper.deleteUser(userId);
            return Result.success();
        } catch (Exception e) {
            return Result.fail(ResponseCode.DELETE_FAILED);
        }
    }
}
