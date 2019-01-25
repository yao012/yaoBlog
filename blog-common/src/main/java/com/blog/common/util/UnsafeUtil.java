package com.blog.common.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Copy from Google Protobuf <code>com.google.protobuf.UnsafeUtil</code>
 * @author Jerry Chin
 */
public class UnsafeUtil {
	private static final Logger logger = Logger.getLogger(UnsafeUtil.class.getName());

	private static final Unsafe UNSAFE = getUnsafe();
	private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS =
			supportsUnsafeArrayOperations();
	public static final long BYTE_ARRAY_BASE_OFFSET = arrayBaseOffset(byte[].class);

	/**
	 * Gets the {@code sun.misc.Unsafe} instance, or {@code null} if not available on this platform.
	 */
	static Unsafe getUnsafe() {
		Unsafe unsafe = null;
		try {
			unsafe =
					AccessController.doPrivileged(
							new PrivilegedExceptionAction<Unsafe>() {
								@Override
								public Unsafe run() throws Exception {
									Class<Unsafe> k = Unsafe.class;

									for (Field f : k.getDeclaredFields()) {
										f.setAccessible(true);
										Object x = f.get(null);
										if (k.isInstance(x)) {
											return k.cast(x);
										}
									}
									// The sun.misc.Unsafe field does not exist.
									return null;
								}
							});
		} catch (Throwable e) {
			// Catching Throwable here due to the fact that Google AppEngine raises NoClassDefFoundError
			// for Unsafe.
		}
		return unsafe;
	}

	/** Indicates whether or not unsafe array operations are supported on this platform. */
	private static boolean supportsUnsafeArrayOperations() {
		if (UNSAFE == null) {
			return false;
		}
		try {
			Class<?> clazz = UNSAFE.getClass();
			clazz.getMethod("objectFieldOffset", Field.class);
			clazz.getMethod("arrayBaseOffset", Class.class);
			clazz.getMethod("arrayIndexScale", Class.class);
			clazz.getMethod("getInt", Object.class, long.class);
			clazz.getMethod("putInt", Object.class, long.class, int.class);
			clazz.getMethod("getLong", Object.class, long.class);
			clazz.getMethod("putLong", Object.class, long.class, long.class);
			clazz.getMethod("getObject", Object.class, long.class);
			clazz.getMethod("putObject", Object.class, long.class, Object.class);
			clazz.getMethod("getByte", Object.class, long.class);
			clazz.getMethod("putByte", Object.class, long.class, byte.class);
			clazz.getMethod("getBoolean", Object.class, long.class);
			clazz.getMethod("putBoolean", Object.class, long.class, boolean.class);
			clazz.getMethod("getFloat", Object.class, long.class);
			clazz.getMethod("putFloat", Object.class, long.class, float.class);
			clazz.getMethod("getDouble", Object.class, long.class);
			clazz.getMethod("putDouble", Object.class, long.class, double.class);

			return true;
		} catch (Throwable e) {
			logger.log(
					Level.WARNING,
					"platform method missing - proto runtime falling back to safer methods: " + e);
		}
		return false;
	}

	static boolean hasUnsafeArrayOperations() {
		return HAS_UNSAFE_ARRAY_OPERATIONS;
	}

	private static int arrayBaseOffset(Class<?> clazz) {
		return HAS_UNSAFE_ARRAY_OPERATIONS ? UNSAFE.arrayBaseOffset(clazz) : -1;
	}

	public static void putByte(byte[] target, long index, byte value) {
		UNSAFE.putByte(target, BYTE_ARRAY_BASE_OFFSET + index, value);
	}
}
