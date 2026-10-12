using System;
using System.Collections.Generic;
using Thingsoft;

public class itDic : IPoolElement
{
	public Dictionary<string, __Header2> key2Header;

	private static bool initPool;

	private static readonly Type intType;

	private static readonly Type doubleType;

	private static readonly Type itDicType;

	private byte restored;

	public ITweenCallback callbacks { get; set; }

	public int Count
	{
		get
		{
			return 0;
		}
	}

	private itDic()
	{
	}

	public static itDic Allocate()
	{
		return null;
	}

	public static void Restore(itDic one)
	{
	}

	public static void ClearPool()
	{
	}

	public void Clear()
	{
	}

	public Type GetType(string key)
	{
		return null;
	}

	public itDic Clone()
	{
		return null;
	}

	private void Add<T>(string key, T value)
	{
	}

	private void Replace<T>(string key, __Header2 header, T value)
	{
	}

	public void Set<T>(string key, T value)
	{
	}

	public T Get<T>(string key)
	{
		return default(T);
	}

	public T? GetNullable<T>(string key) where T : struct
	{
		return null;
	}

	public object GetAsObjcet(string key)
	{
		return null;
	}

	public void Remove(string key)
	{
	}

	public void CleanArgs()
	{
	}

	public bool Contains(string key)
	{
		return false;
	}

	public bool Contains<T>(string key)
	{
		return false;
	}

	public bool isRestored()
	{
		return false;
	}

	public void MarkRestored()
	{
	}

	public void MarkUsed()
	{
	}
}
