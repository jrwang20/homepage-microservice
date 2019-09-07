package com.imooc.homepage.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfoRequest;
import com.imooc.homepage.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程对外服务接口
 */
@Slf4j
@RestController
public class HomepageCourseController {

    @Autowired
    ICourseService courseService;

    /**
     * 无网关: 127.0.0.1:7001/homepage-course/get/course?id=6
     * 有网关: 127.0.0.1:9000/imooc/homepage-course/get/course?id=6
     */
    @GetMapping("/get/course")
    public CourseInfo getCourseInfo(@RequestParam("id") Long id) {
        log.info("<homepage-course>: get course -> {}", id);
        return courseService.getCourseInfo(id);
    }

    @PostMapping("/get/courses")
    public List<CourseInfo> getCourseInfoList(@RequestBody CourseInfoRequest request) {
        log.info("<homepage-course>: get course list -> {}", JSON.toJSONString(request));
        return  courseService.getCourseInfos(request);
    }
}
