package com.grey.proxy.example;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * @author: Grey
 * @description: TODO
 * @date: 2022/7/6 17:05
 */
public class JdkProxy implements InvocationHandler {
	private Object target;//需要代理的目标对象

	/**
	 proxy 是最终生成的代理实例;
	 method 是被代理目标实例的某个具体方法;
	 args 是被代理目标实例某个方法的具体入参, 在方法反射调用时使用。
	 **/
	@Override
	public Object invoke(Object o, Method method, Object[] args) throws Throwable {
		System.out.println("JDK动态代理，监听开始！");
		Object result = method.invoke(target, args);
		System.out.println("JDK动态代理，监听结束！");
		return result;
	}

	//定义获取代理对象方法
	private Object getJDKProxy(Object targetObject) {
		//为目标对象target赋值
		this.target = targetObject;
		//JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance 函数所需参数就可看出
		System.out.println(this.getClass());
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
	}

	public static void main(String[] args) {
		JdkProxy jdkProxy = new JdkProxy();//实例化JDKProxy对象
		UserManager user = (UserManager) jdkProxy.getJDKProxy(new UserManagerImpl());//获取代理对象
		user.addUser("admin", "123123");//执行新增方法
	}
}
