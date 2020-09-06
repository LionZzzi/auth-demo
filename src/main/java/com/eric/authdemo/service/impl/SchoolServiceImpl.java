package com.eric.authdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eric.authdemo.dao.SchoolMapper;
import com.eric.authdemo.model.domain.School;
import com.eric.authdemo.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Eric
 * @since 2020/8/26 4:46 下午
 */
@Slf4j
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {
}
