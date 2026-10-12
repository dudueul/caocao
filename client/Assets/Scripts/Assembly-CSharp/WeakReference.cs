using System;

public class WeakReference<T> where T : class
{
	private WeakReference m_weakRef;

	public T target
	{
		get
		{
			return null;
		}
	}

	public WeakReference(T target)
	{
	}

	public static implicit operator WeakReference<T>(T target)
	{
		return null;
	}

	public static implicit operator T(WeakReference<T> weakRef)
	{
		return null;
	}
}
