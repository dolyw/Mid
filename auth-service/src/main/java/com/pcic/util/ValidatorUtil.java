package com.pcic.util;

import com.pcic.exception.ValidException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * ValidatorHelper
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/2 14:32
 */
public class ValidatorUtil {

    private static Validator validator;

    /**
     * Validator
     *
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/2 14:35
     */
    static {
        // 关闭快速模式，默认全部校验，快速模式打开校验到一个不通过数据会直接返回
        validator = Validation.byProvider(HibernateValidator.class).configure()
                .failFast(false).buildValidatorFactory().getValidator();
    }

    /**
     * 检验方法
     *
     * @param object 对象
	 * @param classes 检验分组
     * @return void
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/2 14:37
     */
    public static void validate(Object object, Class... classes) {
        Set<ConstraintViolation<Object>> result = validator.validate(object, classes);
        if (result.size() > 0) {
            StringBuffer errorMsg = new StringBuffer("");
            for (ConstraintViolation constraintViolation : result) {
                errorMsg.append(constraintViolation.getMessageTemplate()).append("-");
                errorMsg.append(constraintViolation.getPropertyPath()).append(";");
            }
            throw new ValidException(errorMsg.toString());
        }
    }

    /**
     * 检验方法返回错误信息
     *
     * @param object 对象
     * @param classes 检验分组
     * @return void
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/2 14:37
     */
    public static String validateAndResult(Object object, Class... classes) {
        Set<ConstraintViolation<Object>> result = validator.validate(object, classes);
        if (result.size() > 0) {
            StringBuffer errorMsg = new StringBuffer();
            for (ConstraintViolation constraintViolation : result) {
                errorMsg.append(constraintViolation.getMessageTemplate()).append("-");
                errorMsg.append(constraintViolation.getPropertyPath()).append(";");
            }
            return errorMsg.toString();
        }
        return "";
    }

}
