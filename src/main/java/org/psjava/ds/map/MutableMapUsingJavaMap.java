package org.psjava.ds.map;

import java.util.Iterator;

import org.psjava.ds.KeyValuePair;
import org.psjava.util.AssertStatus;
import org.psjava.util.EqualityTester;
import org.psjava.util.OrderFreeIterableHash;
import org.psjava.util.StrictEqualityTester;

public class MutableMapUsingJavaMap {

	public static <K, V> MutableMap<K, V> wrap(final java.util.Map<K, V> map) {
		return new MutableMap<K, V>() {

			@Override
			public boolean containsKey(K key) {
				return map.containsKey(key);
			}

			/** always check existence because java'map allows null value */
			@Override
			public V get(K key) {
				AssertStatus.assertTrue(map.containsKey(key), "key is not int the map");
				return map.get(key);
			}

			/** always check existence because java'map allows null value */
			@Override
			public V getOrNull(K key) {
				return map.get(key);
			}

			/** always check existence because java'map allows null value */
			@Override
			public void add(K key, V value) {
				AssertStatus.assertTrue(!map.containsKey(key), "already contains");
				map.put(key, value);
			}

			@Override
			public void replace(K key, V value) {
				AssertStatus.assertTrue(map.containsKey(key), "key is not in map");
				map.put(key, value);
			}

			@Override
			public void addOrReplace(K key, V value) {
				map.put(key, value);
			}

			@Override
			public void clear() {
				map.clear();
			}

			@Override
			public boolean isEmpty() {
				return map.isEmpty();
			}

			@Override
			public Iterator<KeyValuePair<K, V>> iterator() {
				return MapIteratorFromJavaMap.create(map);
			}

			@Override
			public int size() {
				return map.size();
			}

			@Override
			public void remove(K key) {
				map.remove(key);
			}

			@Override
			public String toString() {
				return map.toString();
			}

			@Override
			public boolean equals(Object obj) {
				return StrictEqualityTester.areEqual(this, obj, new EqualityTester<Map<K, V>>() {
					@Override
					public boolean areEqual(Map<K, V> m1, Map<K, V> m2) {
						return MapEqualityTester.areEqual(m1, m2);
					}
				});
			}

			@Override
			public int hashCode() {
				return OrderFreeIterableHash.hash(this);
			}

		};
	}

	private MutableMapUsingJavaMap() {
	}

}
