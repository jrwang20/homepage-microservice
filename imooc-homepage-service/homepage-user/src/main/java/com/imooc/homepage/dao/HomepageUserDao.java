package com.imooc.homepage.dao;

import com.imooc.homepage.entity.HomepageUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * HomepageUser实体表对应的DAO
 */
public interface HomepageUserDao extends JpaRepository<HomepageUser, Long> {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    HomepageUser findByUsername(String username);
}
