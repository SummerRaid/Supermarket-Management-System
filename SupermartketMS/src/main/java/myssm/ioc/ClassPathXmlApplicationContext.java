package myssm.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SummerRaid
 *
 * @className: ClassPathXmlApplicationContext
 * @Description: IOC容器
 * @version: v1.17.0
 * @author: ZIRUI QIAO
 * @date: 2022/5/27 15:32
 */
public class ClassPathXmlApplicationContext implements BeanFactory{

    private Map<String, Object> beanMap = new HashMap<>();  // 存放IOC中实体类的map
    private final String path = "applicationContext.xml" ;        // IOC配置文件

    public ClassPathXmlApplicationContext() {
        this("applicationContext.xml");
    }

    public ClassPathXmlApplicationContext(String path) {
        try {
            java.net.URL url = getClass().getClassLoader().getResource(path);

            InputStream is = new FileInputStream(url.getPath());
            // 1.创建DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // 2.创建DocumentBuilder对象
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            // 3. 创建Document对象
            Document document = documentBuilder.parse(is);
            // 4. 获取所有的bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            // 遍历所有bean节点
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                // 如果是元素节点
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    // 获取beanId
                    String beanId = beanElement.getAttribute("id");
                    // 获取beanClass
                    String className = beanElement.getAttribute("class");
                    // 从所有Class中查找beanClass
                    Class beanClass = Class.forName(className);
                    // 获取beanClass的构造器
                    Constructor c = beanClass.getDeclaredConstructor();
                    c.setAccessible(true);
                    // 创建bean实例
                    Object beanObj = c.newInstance();
                    // 将bean对象保存到map中
                    beanMap.put(beanId, beanObj);
                }
            }
            // 5. 组装bean之间的依赖关系
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                // 遍历所有bean节点
                Node beanNode = beanNodeList.item(i);
                // 如果是元素节点
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    // 强转类型，方便操作
                    Element beanElement = (Element) beanNode;
                    // 获取beanId
                    String beanId = beanElement.getAttribute("id");
                    // 获取当前节点的子节点List
                    NodeList beanChildNodeList = beanElement.getChildNodes();
                    // 遍历所有子节点
                    for (int j = 0; j < beanChildNodeList.getLength(); j++) {
                        Node beanChildNode = beanChildNodeList.item(j);
                        // 如果子节点是元素节点 且 名为property
                        if(beanChildNode.getNodeType() == Node.ELEMENT_NODE &&
                        "property".equals(beanChildNode.getNodeName())) {
                            // 强转类型，方便操作
                            Element propertyElement = (Element) beanChildNode;
                            // 获取子节点名称
                            String propertyName = propertyElement.getAttribute("name");
                            // 获取子节点ref
                            String propertyRef = propertyElement.getAttribute("ref");
                            // 5.1 找到propertyRef对应的实例
                            Object refObj = beanMap.get(propertyRef);
                            // 5.2 将refObj设置到当前bean对应的实例的property属性上去
                            Object beanObj = beanMap.get(beanId);
                            Class beanClazz = beanObj.getClass();
                            Field propertyField = beanClazz.getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj, refObj);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
