package fun.huanghai.guifan.controller;

import fun.huanghai.guifan.bean.User;
import fun.huanghai.guifan.service.UserService;
import fun.huanghai.guifan.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody @Valid User user, BindingResult bind){

        for (ObjectError error : bind.getAllErrors()) {
            return error.getDefaultMessage();
        }
        return userService.addUser(user);
    }

    @PostMapping("/addUser2")
    public String addUser2(@RequestBody @Valid User user){

        return userService.addUser(user);
    }

    @GetMapping("/getUser")
    public ResultVO<User> getUser(){
        User user = new User();
        user.setId(1l);
        user.setAccount("123456578");
        user.setPassword("12344556");
        user.setEmail("34234@qq.com");
        return new ResultVO<User>(user);
    }

    @GetMapping("/getUser2")
    public User getUser2(){
        User user = new User();
        user.setId(1l);
        user.setAccount("123456578");
        user.setPassword("12344556");
        user.setEmail("34234@qq.com");
        return user;
    }

}
