package com.tanhua.server.controller;

import com.tanhua.model.domain.UserInfo;
import com.tanhua.server.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: slx
 * @create: 2023/9/15
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/loginReginfo")
    public ResponseEntity loginRegiInfo(@RequestBody UserInfo userInfo,
                                        @RequestHeader("Authorization") String token) {
        userInfo.setId(UserHolder.getUserId());
        userInfoService.save(userInfo);
        return ResponseEntity.ok(null);
    }

}
