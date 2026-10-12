using System;

public class __Header
{
	public int elementIdx;

	public Type type;
}
public class __Header<T> : __Header2
{
	public T value;

	public override object valueAsObj()
	{
		return null;
	}

	public override void CopyTo(object dest)
	{
	}
}
