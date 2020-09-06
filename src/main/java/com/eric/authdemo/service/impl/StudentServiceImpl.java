package com.eric.authdemo.service.impl;

import cn.hutool.core.lang.func.Func1;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eric.authdemo.dao.StudentMapper;
import com.eric.authdemo.model.domain.School;
import com.eric.authdemo.model.domain.Student;
import com.eric.authdemo.model.domain.Teacher;
import com.eric.authdemo.model.dto.StudentDTO;
import com.eric.authdemo.service.FindService;
import com.eric.authdemo.service.StudentService;
import com.github.dozermapper.core.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.function.Function;

/**
 * @author Eric
 * @since 2020/8/26 4:46 下午
 */
@Slf4j
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private Mapper mapper;

    @Resource
    private FindService findService;

    @Override
    public StudentDTO findById(String id) throws BadSqlGrammarException {
        Student student = baseMapper.selectById(id);
        StudentDTO dto = mapper.map(student, StudentDTO.class);
        LambdaQueryWrapper<Student> asd = Wrappers.lambdaQuery(Student.class)
                .eq(Student::getName, "张学生");
        dto.setTeacherName((String) findService.check(Teacher::getName));
        //dto.setSchoolName((String) findService.check("name", School.class));
        return dto;
    }
}
