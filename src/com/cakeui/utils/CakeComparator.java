package com.cakeui.utils;

import java.util.Comparator;

public class CakeComparator<T> implements Comparator<T>{

	@Override
	public int compare(T lhs, T rhs) {
		return lhs.toString().compareTo(rhs.toString());
	}

}
