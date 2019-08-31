package com.sys.lunasysmanagement.mapper;

import com.sys.lunasysmanagement.model.param.EditParam;
import com.sys.lunasysmanagement.model.param.UserDisplayParam;
import com.sys.lunasysmanagement.model.param.UserQueryParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户相关表操作
 *
 * @author wangfangrui
 * @date 2019/8/22 17:18
 */
@Repository
public interface UserMapper {

    List<UserDisplayParam> searchAll(UserQueryParam userQueryParam);

    int updateService(EditParam editParam);

    int deleteUser(String userId);

    int insertLog(@Param("adminId") String adminId, @Param("userId") String userId, @Param("content") String content, @Param("currentTime") String currentTime);
}
