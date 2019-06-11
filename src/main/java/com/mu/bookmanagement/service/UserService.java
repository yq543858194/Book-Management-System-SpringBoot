package com.mu.bookmanagement.service;

import com.mu.bookmanagement.entity.ResultEntity;
import com.mu.bookmanagement.entity.UserEntity;
import com.mu.bookmanagement.exception.SystemException;
import com.mu.bookmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mu.bookmanagement.enums.ExpectionEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession session;

    /**
     * 用户登录服务
     * @param user
     * @return
     * @throws Exception
     */
    public ResultEntity<UserEntity> login (UserEntity user) throws Exception {
        UserEntity userEntity = userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        if (userEntity != null) {
            session.setAttribute("user", userEntity);
            return new ResultEntity<>(ExpectionEnum.SUCCESS.getCode(),"登录成功",userEntity);
        } else {
            throw new SystemException(ExpectionEnum.USER_UNVAILD.getCode(),ExpectionEnum.USER_UNVAILD.getMsg());
        }
    }

    /**
     * 用户注册服务
     * @param user
     * @return
     * @throws Exception
     */
    public ResultEntity<UserEntity> register (UserEntity user) throws Exception {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            UserEntity userEntity = userRepository.save(user);
            if (userEntity != null) {
                return new ResultEntity<>(ExpectionEnum.SUCCESS.getCode(),"注册成功",null);
            } else {
                throw new RuntimeException("未知错误");
            }
        } else {
            throw new SystemException(ExpectionEnum.USER_EXISTS.getCode(),ExpectionEnum.USER_EXISTS.getMsg());
        }
    }

    /**
     * 用户登出服务
     * @return
     */
    public ResultEntity<UserEntity> logout () {
        session.removeAttribute("user");
        return new ResultEntity<>(ExpectionEnum.SUCCESS.getCode(),"注销成功",null);
    }

    /**
     * 获取当前登录用户服务
     * @return
     */
    public ResultEntity<UserEntity> getUser () {
        if (session.getAttribute("user") != null) {
            UserEntity userEntity = (UserEntity)session.getAttribute("user");
            return new ResultEntity<>(ExpectionEnum.SUCCESS.getCode(),"登录成功", userEntity);
        } else {
            throw new SystemException(ExpectionEnum.USER_NOT_LOGIN.getCode(),ExpectionEnum.USER_NOT_LOGIN.getMsg());
        }
    }

    /**
     * 更新用户信息服务
     * @param user
     * @return
     */
    public ResultEntity<UserEntity> updateUser (UserEntity user) {
        UserEntity userEntity = userRepository.findByUsername(user.getUsername());
        if (userEntity != null) {
            user.setId(userEntity.getId());
            UserEntity user1 = userRepository.save(user);
            return new ResultEntity<>(ExpectionEnum.SUCCESS.getCode(),"用户信息修改成功",user1);
        } else {
            throw new SystemException(ExpectionEnum.USER_NOT_EXISTS.getCode(),ExpectionEnum.USER_NOT_EXISTS.getMsg());
        }
    }

    private String getRandomFileName(String ext, String username) {
        return new Date().getTime() + username + "." + ext;
    }

    public ResultEntity<String> uploadAvatar (MultipartFile avatar, String username) throws NullPointerException, IOException {
        String fileName = avatar.getOriginalFilename();
        if (fileName != null) {
            System.out.println(fileName);
            String[] fileExt = fileName.split(".");
            String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
            System.out.println(ext);
            fileName = getRandomFileName(ext, username);
            String filePath = "/Users/nuxt/uploadImage/";
            File dest = new File(filePath + fileName);
            avatar.transferTo(dest);
            UserEntity userEntity = userRepository.findByUsername(username);
            userEntity.setAvatar(fileName);
            if (userRepository.save(userEntity) != null) {
                return new ResultEntity<>(ExpectionEnum.SUCCESS.getCode(),"上传头像成功",fileName);
            } else {
                throw new RuntimeException("未知错误");
            }
        } else {
            throw new RuntimeException("未知错误");
        }
    }
}
