package com.lagou.edu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Transactional
 * @Description: 自定义事物注解 暂时只支持对类的修饰
 * @Author: wuguizhen
 * @Date: 2020/3/5 17:29
 * @Version: v1.0 文件初始创建
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Transactional {
}
