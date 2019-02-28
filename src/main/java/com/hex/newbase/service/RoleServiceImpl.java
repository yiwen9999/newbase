package com.hex.newbase.service;

import com.hex.newbase.domain.Role;
import com.hex.newbase.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User: hexuan
 * Date: 2019/2/28
 * Time: 3:32 PM
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        roleRepository.deleteById(id);
    }
}
