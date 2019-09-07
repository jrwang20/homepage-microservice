package com.imooc.homepage.service;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.Application;
import com.imooc.homepage.CourseInfoRequest;
import com.imooc.homepage.dao.HomepageCourseDao;
import com.imooc.homepage.entity.HomepageCourse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


/**
 * 课程服务测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ICourseServiceTest {

    @Autowired
    HomepageCourseDao courseDao;

    @Autowired
    ICourseService courseService;

    @Test
    public void createCourseInfo() {

        HomepageCourse course1 = new HomepageCourse(
                "JDK11&12 new features introduction",
                0,
                "https://www.udemy.com",
                "Introduction to new features of JDK11 and JDK12"
        );

        HomepageCourse course2 = new HomepageCourse(
                "Advertisement Systems Design and Implementation Based on SpringCloud",
                1,
                "https://www.udemy.com",
                "MicroService Architectures Design"
        );

        System.out.println(courseDao.saveAll(
                Arrays.asList(course1, course2)
        ).size());

    }

    @Test
    public void getCourseInfo() {
        System.out.println(JSON.toJSONString(
                courseService.getCourseInfo(6L)
        ));

        System.out.println(JSON.toJSONString(
                courseService.getCourseInfo(8L)
        ));
    }

    @Test
    public void getCourseInfos() {

        System.out.println(JSON.toJSONString(
                courseService.getCourseInfos(
                        new CourseInfoRequest(Arrays.asList(6L, 7L))
                )
        ));
    }
}