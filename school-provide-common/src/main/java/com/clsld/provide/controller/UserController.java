package com.clsld.provide.controller;

import com.clsld.api.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user",method = RequestMethod.GET)
public class UserController {

    private com.clsld.provide.dao.UserDao userDao;

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public User getUserByid(@RequestParam("userid") String userid) {
        return userDao.selectByPrimaryKey(userid);
    }

    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    public int addUser(@RequestParam("nickname") String nickname,
                        @RequestParam("avatarurl") String avatarurl) {
        User user = new User();
        user.setNickname(nickname);
        user.setAvatarUrl(avatarurl);
        return userDao.insert(user);
    }

    @RequestMapping(value = "getUserAll")
    public List<User> getUserAll(){
        List<User> user  = userDao.selectUserAll();
        return user;
    }

    @RequestMapping(value = "updateUser")
    public int updateUser(@RequestParam("userid")int userid,@RequestParam("nikcname")String nickname,
                          @RequestParam("genderclass")String genderclass,@RequestParam("gender")int gender,
                          @RequestParam("studentNum")Integer studentNum,@RequestParam("avatarUrl")String avatarUrl){
        User user = userDao.selectByPrimaryKey(String.valueOf(userid));
        user.setNickname(nickname);
        user.setGenderclass(genderclass);
        user.setGender(gender);
        user.setStudentNum(studentNum);
        user.setAvatarUrl(avatarUrl);
        return userDao.updateByPrimaryKey(user);
    }

    @RequestMapping(value = "deleteUser",method = RequestMethod.GET)
    public int deleteUser(@RequestParam("userid")String userid){
        return userDao.deleteByPrimaryKey(userid);
    }
}