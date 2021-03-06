package org.psjava.ds.map;

import org.psjava.ds.KeyValuePair;
import org.psjava.util.ConvertedDataIterable;
import org.psjava.util.DataConverter;

public class ValuesInMap {
	public static <K, V> Iterable<V> get(Map<K, V> map) {
		return ConvertedDataIterable.create(map, new DataConverter<KeyValuePair<K, V>, V>() {
			@Override
			public V convert(KeyValuePair<K, V> pair) {
				return pair.getValue();
			}
		});
	}

	private ValuesInMap() {
	}
}
