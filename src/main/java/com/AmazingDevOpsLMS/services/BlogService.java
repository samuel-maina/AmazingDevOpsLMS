/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AmazingDevOpsLMS.services;

import com.AmazingDevOpsLMS.model.Blog;
import com.AmazingDevOpsLMS.repositories.BlogRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samuel
 */
@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Optional<Blog> findBlogById(String id) {
        return blogRepository.findById(id);
    }

}
