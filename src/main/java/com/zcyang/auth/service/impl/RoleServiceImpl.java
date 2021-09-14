package com.zcyang.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcyang.auth.domain.RoleEntity;
import com.zcyang.auth.mapper.RoleMapper;
import com.zcyang.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-09-14
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleEntity> queryRoleListByUserId(Long userId) {
        return roleMapper.queryRoleListByUserId(userId);
    }
}
