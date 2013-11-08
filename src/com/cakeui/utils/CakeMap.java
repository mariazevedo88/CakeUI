package com.cakeui.utils;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class CakeMap<T, E> extends TreeMap<T, E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4162502760579250465L;

	public CakeMap(){
		super(new CakeComparator<T>());
	}
	
	public T getKeyFromIndex(int index){
		
		int indexOf = 0;
		Set<T> set = this.keySet();
		
		Iterator<T> itSet = set.iterator();
		
		while (itSet.hasNext()){
			
			T object = itSet.next();
			
			if (indexOf == index)
				return object;
			
			indexOf++;
		}
		
		return null;
	}
	
	public E getEntryFromIndex(int indexKey){
		
		T keyObject = getKeyFromIndex(indexKey);
		
		E entryObject = this.get(keyObject);
		
		return entryObject;
		
	}
	
}
