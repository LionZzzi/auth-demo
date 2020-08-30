package com.eric.authdemo.config.dozer;

import com.eric.authdemo.model.SecurityUserDetails;
import com.eric.authdemo.model.domain.Permission;
import com.eric.authdemo.model.domain.User;
import com.eric.authdemo.model.dto.PermissionDTO;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * VO <-> DTO -> DO
 *
 * @author Eric
 */
@Configuration
public class DozerConfig {

    @Bean
    public Mapper dozerMapper() {
        return DozerBeanMapperBuilder.create()
                //指定 dozer mapping 的配置文件(放到 resources 类路径下即可)，可添加多个 xml 文件，用逗号隔开
                .withMappingFiles("dozer/dozer-mapping.xml")
                .withMappingBuilder(beanMappingBuilder())
                .build();
    }

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                // 个性化配置添加在此
                mapping(User.class, SecurityUserDetails.class);
                mapping(Permission.class, PermissionDTO.class);
            }
        };
    }
}
