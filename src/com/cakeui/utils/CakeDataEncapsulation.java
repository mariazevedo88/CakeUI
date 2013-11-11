package com.cakeui.utils;

import java.io.Serializable;

/**
 * 
 * @author Sarah Caixeta
 * @email caixeta.sarah@gmail.com
 * 
 * Class that encapsulates data so the application can exchange different kinds of data between its components. 
 * 
 */
public class CakeDataEncapsulation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private DataType dataType;
	private Object content;
	
	public enum DataType {
		GENERIC_DATA, NETWORK_STATUS
	}
	
	/**
	 * Constructor.
	 * @param dataType - {@link DataType}
	 * @param content - If the {@code dataType} is {@link DataType#NETWORK_STATUS}, the content must be either 
	 * <code>R.string.broadcast_network_down</code> or <code>R.string.broadcast_network_up</code>. 
	 * If {@code dataType} is {@link DataType#GENERIC_DATA}, the content can be any object. 
	 */
	public CakeDataEncapsulation (DataType dataType, Object content){
		this.dataType = dataType;
		this.content = content;
	}
	
	public DataType getDataType(){
		return this.dataType;
	}
	
	public Object getContent(){
		return this.content;
	}
}
