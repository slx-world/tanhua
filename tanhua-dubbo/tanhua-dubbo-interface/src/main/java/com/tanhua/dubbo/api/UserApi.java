package com.tanhua.dubbo.api;

import com.tanhua.model.domain.User;

/**
 * @author: slx
 * @create: 2023/9/15
 */

/**
 * 公共接口
 */

public interface UserApi {

    // 根据手机号码查询用户
    User findByMobile(String mobile);

    // 保存用户，返回用户id
    Long save(User user);

}
