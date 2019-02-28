package com.hex.newbase.service;

import com.hex.newbase.domain.Role;

import java.util.Optional;

/**
 * User: hexuan
 * Date: 2019/2/28
 * Time: 3:22 PM
 */
public interface RoleService {
    Role save(Role role);

    Optional<Role> findById(String id);

    void delete(String id);
}
