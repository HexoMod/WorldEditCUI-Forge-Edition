package com.github.hexosse.wecuife.exceptions;

public class InvalidSelectionTypeException extends RuntimeException
{
	private static final long serialVersionUID = 3992465828925267654L;

	public InvalidSelectionTypeException(String regionType, String eventName)
	{
		super(String.format("Selection event %s is not supported for selection type %s", eventName, regionType));
	}
}
