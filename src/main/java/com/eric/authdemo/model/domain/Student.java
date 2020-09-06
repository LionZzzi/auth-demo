package com.eric.authdemo.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Eric
 * @since 2020/5/19 23:45
 */
@Data
@TableName(value = "t_student")
@EqualsAndHashCode(callSuper = true)
public class Student extends Model<Student> {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 学生名
     */
    private String name;

    /**
     * 教师ID
     */
    private Long teacherId;

    /**
     * 班级ID
     */
    private Long classId;
}