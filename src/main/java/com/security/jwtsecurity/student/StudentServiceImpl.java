package com.security.jwtsecurity.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    public static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Student getStudent(Long studentId) {
        return getStudent(studentId);
    }
}
