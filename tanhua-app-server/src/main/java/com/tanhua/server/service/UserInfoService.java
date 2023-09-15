package com.tanhua.server.service;

import com.tanhua.autoconfig.template.AipFaceTemplate;
import com.tanhua.autoconfig.template.OssTemplate;
import com.tanhua.dubbo.api.UserInfoApi;
import com.tanhua.model.domain.UserInfo;
import com.tanhua.model.vo.UserInfoVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: slx
 * @create: 2023/9/15
 */

@Service
public class UserInfoService {

    @DubboReference
    private UserInfoApi userInfoApi;

    @Autowired
    private OssTemplate ossTemplate;

    @Autowired
    private AipFaceTemplate aipFaceTemplate;

    public void save(UserInfo userInfo) {
        userInfoApi.save(userInfo);
    }

    // 更新
    public void update(UserInfo userInfo) {
        userInfoApi.update(userInfo);
    }

    // 根据id查询
    public UserInfoVo findById(Long id) {
        UserInfo userInfo = userInfoApi.findById(id);
        UserInfoVo vo = new UserInfoVo();

        // copy同名同类型的属性
        BeanUtils.copyProperties(userInfo, vo);

        if (userInfo.getAge() != null) {
            vo.setAge(userInfo.getAge().toString());
        }

        return vo;
    }

}
