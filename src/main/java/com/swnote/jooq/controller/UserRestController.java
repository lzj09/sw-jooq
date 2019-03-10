package com.swnote.jooq.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.swnote.jooq.generator.tables.pojos.User;
import com.swnote.jooq.service.IUserService;

/**
 * 用户REST信息
 * 
 * @author lzj
 * @date [2019-03-10]
 */
@RestController
@RequestMapping("/api/user")
public class UserRestController {
    
    @Autowired
    private IUserService userService;
    
    /**
     * 创建用户
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User add(@RequestBody User user) {
        userService.create(user);
        return user;
    }

    /**
     * 根据id删除用户
     * 
     * @param user_id
     */
    @RequestMapping(value = "/delete/{user_id}", method = RequestMethod.GET)
    public void delete(@PathVariable("user_id") String user_id) {
        userService.delete(user_id);
    }

    /**
     * 更新用户
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public User edit(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    /**
     * 根据id获取用户
     * 
     * @param user_id
     * @return
     */
    @RequestMapping(value = "/view/{user_id}", method = RequestMethod.GET)
    public User view(@PathVariable("user_id") String user_id) {
        return userService.retrieve(user_id);
    }

    /**
     * 根据条件获取用户列表
     * 
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<User> list(@RequestBody Map<String, Object> params) {
        return userService.queryForList(params);
    }
}