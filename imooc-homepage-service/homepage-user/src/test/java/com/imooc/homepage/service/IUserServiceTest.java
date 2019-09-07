package com.imooc.homepage.service;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.Application;
import com.imooc.homepage.dao.HomepageUserCourseDao;
import com.imooc.homepage.entity.HomepageUserCourse;
import com.imooc.homepage.vo.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class IUserServiceTest {

    @Autowired
    IUserService userService;

    @Autowired
    HomepageUserCourseDao userCourseDao;

    @Test
    @Transactional
    public void createUser() {

//        {"email":"jerry@gmail.com","id":6,"username":"Jerry"}
        System.out.println(
                JSON.toJSONString(
                        userService.createUser(
                                new CreateUserRequest(
                                        "Jerry",
                                        "jerry@gmail.com"
                                )
                        )
                )
        );
    }

    @Test
    public void getUserInfo() {

//        {"email":"jerry@gmail.com","id":6,"username":"Jerry"}
        System.out.println(
                JSON.toJSONString(
                        userService.getUserInfo(
                                6L
                        )
                )
        );
    }

    @Test
    public void testCreateHomepageUserCourse() {

        HomepageUserCourse course1 = new HomepageUserCourse();
        course1.setUserId(6L);
        course1.setCourseId(6L);

        HomepageUserCourse course2 = new HomepageUserCourse();
        course2.setUserId(6L);
        course2.setCourseId(7L);

        System.out.println(userCourseDao.saveAll(Arrays.asList(course1, course2)).size());
    }
}