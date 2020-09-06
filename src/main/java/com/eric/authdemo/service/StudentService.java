package com.eric.authdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eric.authdemo.model.domain.Role;
import com.eric.authdemo.model.domain.Student;
import com.eric.authdemo.model.dto.StudentDTO;
import org.springframework.jdbc.BadSqlGrammarException;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

/**
 * @author Eric
 * @since 2020/8/26 4:46 下午
 */
public interface StudentService extends IService<Student> {
    StudentDTO findById(String id) throws BadSqlGrammarException, NoSuchFieldException;
}
