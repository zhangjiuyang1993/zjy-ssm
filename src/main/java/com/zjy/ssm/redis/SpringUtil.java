package com.zjy.ssm.redis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhangjiuyang
 * @create 2018/8/3
 * @since 1.0.0
 */
@Component
public class SpringUtil implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;//spring应用上下文

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        SpringUtil.beanFactory = beanFactory;
    }

    /*
     * 获取对象
     * @author zhangjiuyang
     * @date 2018/8/3 14:48
     * @param [name]
     * @return T
     */
    public static <T> T getBean(String name) throws BeansException {
        T result = (T) beanFactory.getBean(name);
        return result;
    }

    /*
     * 获取类型为requiredType的对象
     * @author zhangjiuyang
     * @date 2018/8/3 14:49
     * @param [clazz]
     * @return T
     */
    public static <T> T getBean(Class clazz) throws BeansException {
        T result = (T) beanFactory.getBean(clazz);
        return result;
    }

    /*
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     * @author zhangjiuyang
     * @date 2018/8/3 14:52
     * @param [name]
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    /*
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     * @author zhangjiuyang
     * @date 2018/8/3 14:58
     * @param [name]
     * @return boolean
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.isSingleton(name);
    }

    /*
     *
     * @author zhangjiuyang
     * @date 2018/8/3 15:00
     * @param [name]
     * @return java.lang.Class<?>
     */
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getType(name);
    }

    /*
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     * @author zhangjiuyang
     * @date 2018/8/3 15:01
     * @param [name]
     * @return java.lang.String[]
     */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getAliases(name);
    }
}
