package com.offer.one;

/**
 * 单例模式，懒汉式，线程安全，多线程环境下效率不高
 * 
 * @author Administrator
 *
 */
public class Singleton3 {
	private static Singleton3 singleton = null;

	private Singleton3() {
	}

	public static synchronized Singleton3 getInstance() {
		if (singleton == null) {
			singleton = new Singleton3();
		}
		return singleton;
	}
}
