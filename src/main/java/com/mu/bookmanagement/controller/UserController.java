package com.mu.bookmanagement.controller;

import com.mu.bookmanagement.entity.ResultEntity;
import com.mu.bookmanagement.entity.UserEntity;
import com.mu.bookmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录接口
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public ResultEntity<UserEntity> login (@RequestParam String username, @RequestParam String password) throws Exception {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        return userService.login(user);
    }

    /**
     * 用户注册接口
     * @param rusername
     * @param rpassword
     * @param remail
     * @param radminCode
     * @return
     * @throws Exception
     */
    @PostMapping("/register")
    public ResultEntity<UserEntity> register (@RequestParam String rusername, @RequestParam String rpassword, @RequestParam String remail, @RequestParam String radminCode) throws Exception {
        UserEntity user = new UserEntity();
        user.setUsername(rusername);
        user.setPassword(rpassword);
        user.setEmail(remail);
        user.setAdminCode(radminCode);
        return userService.register(user);
    }

    /**
     * 用户登出接口
     * @return
     * @throws Exception
     */
    @GetMapping("/logout")
    public ResultEntity<UserEntity> logout () {
        return userService.logout();
    }

    /**
     * 获取当前已登录用户信息接口
     * @return
     */
    @GetMapping("/getUser")
    public ResultEntity<UserEntity> getUser () {
        return userService.getUser();
    }

    /**
     * 更新用户接口
     * @param username
     * @param password
     * @param name
     * @param school
     * @param email
     * @param phone
     * @param work
     * @param description
     * @return
     */
    @PostMapping("/updateUser")
    public ResultEntity<UserEntity> updateUser (@RequestParam String username,
                                                @RequestParam String password,
                                                @RequestParam String name,
                                                @RequestParam String school,
                                                @RequestParam String email,
                                                @RequestParam String phone,
                                                @RequestParam String work,
                                                @RequestParam String description) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setDescription(description);
        user.setName(name);
        user.setSchool(school);
        user.setPhone(phone);
        user.setWork(work);
        return userService.updateUser(user);
    }

    /**
     * 上传用户头像接口
     * @param avatar
     * @param username
     * @return
     */
    @PostMapping("/uploadAvatar")
    public ResultEntity<String> uploadAvatar (@RequestParam MultipartFile avatar, @RequestParam String username) throws IOException {
        return userService.uploadAvatar(avatar, username);
    }
}
