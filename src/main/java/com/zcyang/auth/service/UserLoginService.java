package com.zcyang.auth.service;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zcyang.auth.domain.RoleEntity;
import com.zcyang.auth.domain.UserEntity;
import com.zcyang.auth.domain.UserLoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义UserService
 */
@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserLoginModel loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper wrapper = new QueryWrapper<UserEntity>();
        wrapper.eq("login_name", username);
        UserEntity userEntity = userService.getOne(wrapper);
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return construnctUserModel(userEntity);
    }

    /**
     * 封装用户登录实体
     */
    private UserLoginModel construnctUserModel(UserEntity userEntity) {
        UserLoginModel userLoginModel = new UserLoginModel();
        userLoginModel.setUserId(userEntity.getUserId());
        userLoginModel.setUsername(userEntity.getLoginName());
        userLoginModel.setPassword(userEntity.getPassword());
        userLoginModel.setAuthorities(getUserAuthorities(userLoginModel.getUserId()));
        return userLoginModel;
    }

    /***
     * 获取人员的角色列表
     * @param userId
     * @return
     */
    private List<GrantedAuthority> getUserAuthorities(Long userId) {
        List<RoleEntity> roleList = roleService.queryRoleListByUserId(userId);
        List<String> roleCodeList = roleList.stream().map(RoleEntity::getRoleCode)
                .map(str->"ROLE_".concat(str)).collect(Collectors.toList());
        return AuthorityUtils.createAuthorityList(roleCodeList.stream().toArray(String[]::new));
    }


    public static void main(String[] args) {
        System.out.println(SecureUtil.md5("123456"));
    }
}
