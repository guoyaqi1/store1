package com.itheima.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 鑾峰彇javabean
 * @author Administrator
 *
 */
public class BeanFactory {

	public static Object getBean(String id){
		try {
			//1.获取document对象
			Document doc=new SAXReader().read(BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml"));
			
			//2.调用api selectSingleNode(表达式)
			Element beanEle=(Element) doc.selectSingleNode("//bean[@id='"+id+"']");
			
			//3.获取元素的class 属性
			String classValue = beanEle.attributeValue("class");
			
			//4.调用反射返回实现类对象
			final Object newInstance = Class.forName(classValue).newInstance();
			
			
			//5.鍒ゆ柇鏄惁鏄痙ao
			if(id.endsWith("Dao")){
				//鑻ユ槸dao 鍒涘缓浠ｇ悊瀵硅薄
				Object proxy = Proxy.newProxyInstance(newInstance.getClass().getClassLoader(), newInstance.getClass().getInterfaces(), new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						//鍒ゆ柇鏄惁鏄痵ave鏂规硶,鑻ユ槸save鏂规硶 鎵撳嵃涓�鍙ヨ瘽
						if("save".equals(method.getName())){
							System.out.println("鎵ц浜哾ao涓殑淇濆瓨鎿嶄綔");
						}
						
						return method.invoke(newInstance, args);
					}
				});
				
				return proxy;
			}
			
			
			return newInstance;
		}  catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取bean失败");
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getBean("ProductDao1"));
	}
}
