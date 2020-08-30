package com.eric.authdemo.controller;

import com.eric.authdemo.model.common.Result;
import com.eric.authdemo.model.domain.User;
import com.eric.authdemo.service.UserService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Eric
 * @since 2020/8/26 4:41 下午
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/findById/{id}")
    public Result<User> findById(@PathVariable String id) {
        return Result.success(userService.getById(id), "ok");
    }

    @GetMapping("/current")
    public Result<String> current() {
        SecurityContext context = SecurityContextHolder.getContext();
        return Result.success("user", "ok");
    }

    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return Result.success(userService.save(user), "ok");
    }
}
