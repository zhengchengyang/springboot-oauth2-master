package com.zcyang.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcyang.auth.domain.RoleEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2021-09-14
 */
public interface RoleService extends IService<RoleEntity> {

    /***
     * 根据用户ID查询角色信息
     * @param userId
     * @return
     */
    List<RoleEntity> queryRoleListByUserId(Long userId);
}
