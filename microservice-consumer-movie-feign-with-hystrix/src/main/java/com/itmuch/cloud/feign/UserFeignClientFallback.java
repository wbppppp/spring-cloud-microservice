package com.itmuch.cloud.feign;

import com.itmuch.cloud.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallback implements UserFeignClient {

    @Override
    public User findById(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }
}