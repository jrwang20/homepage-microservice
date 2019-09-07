package com.imooc.homepage.dao;

import com.imooc.homepage.entity.HomepageUserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * HomepageUserCourse数据表访问接口定义
 */
public interface HomepageUserCourseDao extends JpaRepository<HomepageUserCourse, Long> {

    /** 通过用户Id寻找数据记录 */
    List<HomepageUserCourse> findAllByUserId(Long userId);

}
