package com.work.algorithm.bigdata_sort;

import java.util.Set;

public class IntegerAscSelector implements Selector<Integer>{

	@Override
	public Integer select(Set<Integer> ts) {
		Integer result = 2^32;
		for (Integer i : ts) {
			result = Math.min(i, result);
		}
		return result;
	}

}
