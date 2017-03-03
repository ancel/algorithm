package com.work.algorithm.bigdata_sort;

import java.util.Set;

public interface Selector<T> {
	T select(Set<T> ts);
}
