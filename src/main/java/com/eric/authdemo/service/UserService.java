package com.eric.authdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eric.authdemo.model.domain.User;

/**
 * @author Eric
 * @since 2020/8/26 4:46 下午
 */
public interface UserService extends IService<User> {
    /**
     * 用户名查询
     *
     * @param name 用户名
     * @return 用户实体
     */
    User findByName(String name);
}
