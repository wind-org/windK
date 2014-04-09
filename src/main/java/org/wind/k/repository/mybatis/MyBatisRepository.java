package org.wind.k.repository.mybatis;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;


/**
 * it's good for scannning the mybatis Dao class by MapperScannerConfigurer
 * @author stephen
 *
 */
@Retention(RetentionPolicy.RUNTIME)//the virtual machine will read the annotation in runtime
@Target(ElementType.TYPE)//annotation type ,for class or method,（ElementType.method denotes method）
@Documented //document it in javadoc
@Component
public @interface MyBatisRepository {
	String values() default "";
}
