package fun.huanghai.guifan.service.impl;

import fun.huanghai.guifan.bean.User;
import fun.huanghai.guifan.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String addUser(User user) {
        System.out.println(user);
        return "success";
    }
}
