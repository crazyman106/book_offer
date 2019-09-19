package com.offer.one;

/**
 * 静态内部类，使用双重校验锁，线程安全【推荐】
 *
 * @author Administrator
 *
 */
public class Singleton7 {
	private volatile static Singleton7 singleton = null;

	private Singleton7() {
	}

	public static Singleton7 getInstance() {
		if (singleton == null) {
			synchronized (Singleton7.class) {
				if (singleton == null) {
					singleton = new Singleton7();
				}
			}
		}
		return singleton;
	}
}
