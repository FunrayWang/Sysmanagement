package com.sys.lunasysmanagement.mapper;

import com.sys.lunasysmanagement.model.SysAdminUserModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 管理员表操作.
 *
 * @author wangfangrui
 * @date 2019/8/22 10:07
 */
@Repository
public interface SysUserMapper {

    SysAdminUserModel loginCheck(@Param("userName") String userName, @Param("userPwd") String userPwd);

    int resetPwd(@Param("userId") String userId, @Param("userPwd") String userPwd);
}
