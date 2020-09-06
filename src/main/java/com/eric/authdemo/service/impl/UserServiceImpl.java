package com.eric.authdemo.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eric.authdemo.dao.UserMapper;
import com.eric.authdemo.model.domain.User;
import com.eric.authdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Eric
 * @since 2020/8/26 4:46 下午
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findByName(String name) {
        return new LambdaQueryChainWrapper<>(baseMapper).eq(User::getName, name).one();
    }
}
