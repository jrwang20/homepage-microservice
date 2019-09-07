package com.imooc.homepage.dao;

import com.imooc.homepage.entity.HomepageCourse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Dao
 */
public interface HomepageCourseDao extends JpaRepository<HomepageCourse, Long> {
}
