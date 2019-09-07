package com.imooc.homepage.service.impl;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfoRequest;
import com.imooc.homepage.UserInfo;
import com.imooc.homepage.client.CourseClient;
import com.imooc.homepage.dao.HomepageUserCourseDao;
import com.imooc.homepage.dao.HomepageUserDao;
import com.imooc.homepage.entity.HomepageUser;
import com.imooc.homepage.entity.HomepageUserCourse;
import com.imooc.homepage.service.IUserService;
import com.imooc.homepage.vo.CreateUserRequest;
import com.imooc.homepage.vo.UserCourseInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户相关服务实现
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private HomepageUserDao homepageUserDao;

    @Autowired
    private HomepageUserCourseDao homepageUserCourseDao;

    @Autowired
    private CourseClient courseClient;

    @Override
    public UserInfo createUser(CreateUserRequest request) {

        //如果请求无效，或者请求中包含的username已经在数据库中存在，返回invalid的无效用户对象
        if(!request.validate()
        || homepageUserDao.findByUsername(request.getUsername()) != null) {
            return UserInfo.invalid();
        }

        //利用Dao进行User数据的创建
        HomepageUser newUser = homepageUserDao.save(
                new HomepageUser(
                        request.getUsername(),
                        request.getEmail()
                )
        );

        //返回UserVO对象
        return new UserInfo(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getEmail()
        );
    }

    @Override
    public UserInfo getUserInfo(Long id) {

        Optional<HomepageUser> user = homepageUserDao.findById(id);
        if(!user.isPresent()) {
            return UserInfo.invalid();
        }

        return new UserInfo(
                user.get().getId(),
                user.get().getUsername(),
                user.get().getEmail()
        );
    }

    @Override
    public UserCourseInfo getUserCourseInfo(Long id) {

        //1. 根据用户Id查询出用户信息
        Optional<HomepageUser> user = homepageUserDao.findById(id);
        if(!user.isPresent()) {
            return UserCourseInfo.invalid();
        }

        //2. 根据用户信息，获取UserInfo和CourseInfoList
        HomepageUser homepageUser = user.get();
        //2.1. 获取UserInfo
        UserInfo userInfo = new UserInfo(
                homepageUser.getId(),
                homepageUser.getUsername(),
                homepageUser.getEmail()
        );
        //2.2. 构造CourseInfoList
        List<HomepageUserCourse> userCourseList = homepageUserCourseDao.findAllByUserId(id);
        if(CollectionUtils.isEmpty(userCourseList)) {
            return new UserCourseInfo(userInfo, Collections.emptyList());
        }
        List<CourseInfo> courseInfoList = courseClient.getCourseInfoList(
                new CourseInfoRequest(
                        userCourseList.stream()
                        .map(HomepageUserCourse::getCourseId)
                        .collect(Collectors.toList())
                )
        );

        //3. 构造用户选课VO，并返回
        return new UserCourseInfo(
                userInfo,
                courseInfoList
        );
    }
}
