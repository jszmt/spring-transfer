package com.lagou.edu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Autowired
 * @Description: 自定义Autowired注解
 * @Author: wuguizhen
 * @Date: 2020/3/5 16:30
 * @Version: v1.0 文件初始创建
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD })
public @interface Autowired {
}
