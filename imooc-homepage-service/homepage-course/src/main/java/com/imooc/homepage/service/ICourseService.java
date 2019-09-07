package com.imooc.homepage.service;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfoRequest;

import java.util.List;

/**
 * 课程相关服务接口定义
 */
public interface ICourseService {

    /**
     * 通过Id获取课程信息
     */
    CourseInfo getCourseInfo(Long id);

    /**
     * 通过一个list的Id获取课程信息
     */
    List<CourseInfo> getCourseInfos(CourseInfoRequest request);

}
