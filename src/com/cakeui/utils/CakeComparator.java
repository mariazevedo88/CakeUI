package com.cakeui.utils;

import java.util.Comparator;

/**
 * 
 * @author Mariana Azevedo
 * @email mariana@bsi.ufla.br
 *
 * Class that implements generic Comparator. 
 */

public class CakeComparator<T> implements Comparator<T>{

	@Override
	public int compare(T lhs, T rhs) {
		return lhs.toString().compareTo(rhs.toString());
	}

}
