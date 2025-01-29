package com.github.redfox197.demo.database.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.redfox197.demo.database.entity.Role;
import com.github.redfox197.demo.database.repository.RoleRepo;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role save(Role entity) {
        return roleRepo.save(entity);
    }

    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    public Optional<Role> findById(long id) {
        return roleRepo.findById(id);
    }

    public void delete(Role entity) {
        roleRepo.delete(entity);
    }
}
