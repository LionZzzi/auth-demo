package com.eric.authdemo.model.dto;

import com.eric.authdemo.model.domain.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Eric
 * @since 2020/8/30 21:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentDTO extends Student {

    /**
     * 教师名称
     */
    private String teacherName;

    /**
     * 学校名称
     */
    private String schoolName;
}
