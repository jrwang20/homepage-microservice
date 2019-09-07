package com.imooc.homepage.vo;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * 用户选课请求信息定义
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseInfo {

    /** 选课用户信息 */
    private UserInfo userInfo;
    /** 当前用户所选课程信息 */
    private List<CourseInfo> courseInfoList;

    /**
     * 当请求出错时，返回一个无效的用户选课请求对象
     * @return
     */
    public static UserCourseInfo invalid() {

        return new UserCourseInfo(
                UserInfo.invalid(),
                Collections.emptyList()
        );
    }
}
