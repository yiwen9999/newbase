package com.hex.newbase.repository;

import com.hex.newbase.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: hexuan
 * Date: 2019/2/28
 * Time: 2:53 PM
 */
public interface RoleRepository extends JpaRepository<Role, String> {
}
