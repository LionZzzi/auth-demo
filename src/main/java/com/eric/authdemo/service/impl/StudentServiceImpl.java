package com.eric.authdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eric.authdemo.dao.StudentMapper;
import com.eric.authdemo.model.domain.Student;
import com.eric.authdemo.model.domain.Teacher;
import com.eric.authdemo.model.dto.StudentDTO;
import com.eric.authdemo.service.StudentService;
import com.github.dozermapper.core.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Eric
 * @since 2020/8/26 4:46 下午
 */
@Slf4j
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private Mapper mapper;

    @Override
    public StudentDTO findById(String id) {
        Student student = baseMapper.selectById(id);
        StudentDTO dto = mapper.map(student, StudentDTO.class);
        QueryWrapper<Teacher> eq = new QueryWrapper<Teacher>().eq("id", student.getTeacherId());
        // list()
        dto.setTeacherName("");
        return dto;
    }
}
