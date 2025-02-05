package com.github.redfox197.demo.database.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.redfox197.demo.database.entity.SubReddit;
import com.github.redfox197.demo.database.repository.SubRedditRepo;

@Service
public class SubRedditService {
    @Autowired
    private SubRedditRepo subRedditRepo;

    public SubReddit save(SubReddit entity) {
        return subRedditRepo.save(entity);
    }

    public List<SubReddit> findAll() {
        return subRedditRepo.findAll();
    }

    public Optional<SubReddit> findById(Long id) {
        return subRedditRepo.findById(id);
    }

    public void delete(SubReddit entity) {
        subRedditRepo.delete(entity);
    }

}
