package site.zido.jianliao.tools.testToolbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import site.zido.cydServer.common.tools.RandomTool;
import site.zido.cydServer.common.tools.StringTool;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用于测试的工具类.
 *
 * 生成数据方法支持所有基本类型的包装类型以及Date类型
 * Date: 2017/3/22 0022
 * Time: 2:44
 *
 * @author <a href="http://zido.site">wuhongxu</a>.
 * @version 1.0.0
 */
@Component
public class TestTool {
    private static Logger logger = LoggerFactory.getLogger(TestTool.class);
    @Resource
    private StringTool stringTool;
    @Resource
    private RandomTool randomTool;

    /**
     * 自动向实体类注入模拟值
     * @param entities 实体类
     * @param <T> 类型
     */
    public <T> void generateEntities(T... entities) {
        for (T entity : entities) {
            generate(entity);
        }
    }

    /**
     * 通过类型和长度生成模拟实体集合
     * @param _class 类型
     * @param length 长度
     * @param <T> 类型
     * @return 模拟实体集合
     */
    public <T> List<T> generateEntities(Class<T> _class,int length) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(generate(_class));
        }
        return list;
    }

    /**
     * 根据类型生成一个已注入模拟值的实体类
     * @param _class 类型集合
     * @param <T> 类型
     * @return 实体类
     */
    public <T> T generate(Class<T> _class) {
        T entity = null;
        try {
            entity = _class.newInstance();
        } catch (InstantiationException e) {
            logger.error("必须设置一个公有无参构造函数");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        final Field[] fields = _class.getDeclaredFields();
        for (Field field : fields) {
            injectValue(entity, field);
        }
        return entity;
    }

    /**
     * 自动注入模拟值
     * @param entity 实体类
     * @param <T> 类型
     */
    public <T> void generate(T entity) {
        final Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            injectValue(entity, field);
        }
    }

    /**
     * 向实体注入值
     * @param entity 实体的实例
     * @param field 属性
     * @param <T> 类型
     */
    private <T> void injectValue(T entity, Field field) {
        final Class<?> _class = entity.getClass();
        final Class<?> type = field.getType();
        if (field.getAnnotation(NoInjectValue.class) != null)
            return;
        final GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);
        if (generatedValue != null)
            return;
        final Column annotation = field.getAnnotation(Column.class);

        if (type == String.class) {
            int length;
            if (annotation == null)
                length = 255;
            else {
                length = annotation.length();
            }
            final String s = randomTool.randomString(length, true);

            try {
                final String name = stringTool.captureName(field.getName());
                final Method method = _class.getMethod("set" + name, String.class);
                method.invoke(entity, s);
            } catch (NoSuchMethodException e) {
                logger.error(field.getName() + "值必须有set方法");
                e.printStackTrace();
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error(field.getName() + "注入值出错");
                e.printStackTrace();
            }
            return;
        }
        if (type == Date.class) {
            try {
                final String name = stringTool.captureName(field.getName());
                final Method method = _class.getMethod("set" + name, Date.class);
                method.invoke(entity, new Date());
            } catch (NoSuchMethodException e) {
                logger.error(field.getName() + "值必须有set方法");
                e.printStackTrace();
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error(field.getName() + "注入值出错");
                e.printStackTrace();
            }
            return;
        }
        int length;
        if (annotation == null)
            length = 9;
        else {
            final int l = annotation.length();
            length = l > 9 ? 9 : l;
        }
        try {
            Constructor<?> constructor = field.getType().getConstructor(String.class);
            final String name = stringTool.captureName(field.getName());
            final Method method = _class.getMethod("set" + name,field.getType());
            method.invoke(entity, constructor.newInstance(randomTool.randomInt(length, true) + ""));
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            logger.error("---------------属性名为" + field.getName() + "的类型不支持-----------------");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
