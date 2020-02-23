package com.tcg.dynamic.data.source;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tcg.dynamic.data.source.annotation.DataSourceType;

@Aspect
@Component
public class DataSourceAspect {

    private static final Logger log = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut("@annotation(com.tcg.dynamic.data.source.annotation.DataSourceType)")
    public void dataSourceTypePointCut() {
    }

    @Around(value = "dataSourceTypePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("switch data source...");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        @SuppressWarnings("unchecked")
        DataSourceType dataSourceType = (DataSourceType) methodSignature.getDeclaringType().getAnnotation(DataSourceType.class);
        if (dataSourceType == null) {
            dataSourceType = method.getAnnotation(DataSourceType.class);
        }
        if (dataSourceType != null && StringUtils.isNotBlank(dataSourceType.value())) {
            log.info("switch data source to {}, thread {}", dataSourceType.value(), Thread.currentThread().getId());
            DataSourceSwitcher.setDataSource(dataSourceType.value());
        }

        Object r = null;
        try {
            r = joinPoint.proceed();
        } finally {
            DataSourceSwitcher.clearDataSource();
        }

        return r;
    }
}
