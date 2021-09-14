package com.zcyang.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zcyang.auth.domain.RoleEntity;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2021-09-14
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /***
     * 根据用户ID查询角色信息
     * @param userId
     * @return
     */
    List<RoleEntity> queryRoleListByUserId(Long userId);
}
