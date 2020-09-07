package com.eric.authdemo.controller;

import com.eric.authdemo.model.common.Result;
import com.eric.authdemo.model.dto.StudentDTO;
import com.eric.authdemo.service.StudentService;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Eric
 * @since 2020/8/26 4:41 下午
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/findById/{id}")
    public Result<StudentDTO> findById(@PathVariable Long id) {
        try {
            return Result.success(studentService.findById(id), "ok");
        } catch (BadSqlGrammarException | NoSuchFieldException e) {
            return Result.fail(e.getCause().getMessage());
        }
    }
}
