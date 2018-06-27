package com.thon.commons.utils;

import java.util.*;

/**
 * @author 57
 */
public final class CollectionUtil {
	
	private CollectionUtil() {
		throw new AssertionError();
	}

	public static <K, V> Map<K, V> newHashMap() {
		return new HashMap<K, V>();
	}
	
	public static <K, V> Map<K, V> newHashMap(Map<K, V> map) {
		return new HashMap<K, V>(map);
	}
	
	public static <K, V> Map<K, V> newTreeMap() {
		return new TreeMap<K, V>();
	}
	
	public static <K, V> Map<K, V> newTreeMap(Map<K, V> map) {
		return new TreeMap<K, V>(map);
	}
	
	public static <K, V> Map<K, V> newTreeMap(Comparator<? super K> comparator) {
		return new TreeMap<K, V>(comparator);
	}
	
	public static <K, V> Map<K, V> newTreeMap(Map<K, V> map, Comparator<? super K> comparator) {
		return new TreeMap<K, V>(map);
	}
	
	public static <T> List<T> newArrayList() {
		return new ArrayList<T>();
	}
	
	public static <T> List<T> newArrayList(Collection<? extends T> collection) {
		return new ArrayList<T>(collection);
	}
	
	public static <T> List<T> newLinkedList() {
		return new LinkedList<T>();
	}
	
	public static <T> List<T> newLinkedList(Collection<? extends T> collection) {
		return new LinkedList<T>(collection);
	}
	
}
