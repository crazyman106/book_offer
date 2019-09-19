package com.offer.one;

/**
 * 单例模式，使用静态内部类，线程安全【推荐】
 * 
 * @author Administrator
 *
 */
public class Singleton5 {

	private final static class SingletonHolder {
		private static final Singleton5 singleton = new Singleton5();
	}

	private Singleton5() {
	}

	public static Singleton5 getInstance() {
		return SingletonHolder.singleton;
	}
}
