package com.offer.one;

/**
 * 单例模式，饿汉式，变种，线程安全
 * 
 * @author Administrator
 *
 */
public class Singleton4 {
	private static Singleton4 singleton = null;
	static {
		singleton = new Singleton4();
	}

	private Singleton4() {
	}

	public static synchronized Singleton4 getInstance() {
		return singleton;
	}
}
