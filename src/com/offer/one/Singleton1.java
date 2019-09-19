package com.offer.one;

/**
 * 单例模式，饿汉式，线程安全
 *
 * @author Administrator
 *
 */
public class Singleton1 {
	private static final Singleton1 singleton = new Singleton1();

	private Singleton1() {
	}

	public static Singleton1 getInstance() {
		return singleton;
	}
}
