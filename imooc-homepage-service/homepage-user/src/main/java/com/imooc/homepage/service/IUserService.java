package com.imooc.homepage.service;

import com.imooc.homepage.UserInfo;
import com.imooc.homepage.vo.CreateUserRequest;
import com.imooc.homepage.vo.UserCourseInfo;

/**
 * 用户相关服务接口定义
 */
public interface IUserService {

    /**
     * 创建用户
     */
    UserInfo createUser(CreateUserRequest request);

    /**
     * 根据用户Id获取用户信息
     */
    UserInfo getUserInfo(Long id);

    /**
     * 根据用户Id获取用户所选课程信息
     */
    UserCourseInfo getUserCourseInfo(Long id);
}
