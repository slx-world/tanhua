package com.tanhua.dubbo.api;

import com.tanhua.model.domain.UserInfo;

/**
 * @author: slx
 * @create: 2023/9/15
 */

public interface UserInfoApi {

    public void save(UserInfo userInfo);

    public void update(UserInfo userInfo);

    // 根据id查询
    UserInfo findById(Long id);

}
