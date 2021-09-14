package com.zcyang.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcyang.auth.domain.UserEntity;
import com.zcyang.auth.mapper.UserMapper;
import com.zcyang.auth.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-09-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

}
