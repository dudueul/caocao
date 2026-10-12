using System;
using System.Diagnostics;
using System.Reflection;
using UnityEngine;

[Serializable]
public class PropertyReference
{
	[SerializeField]
	private Component mTarget;

	[SerializeField]
	private string mName;

	private FieldInfo mField;

	private PropertyInfo mProperty;

	private static int s_Hash;

	public Component target
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string name
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool isValid
	{
		get
		{
			return false;
		}
	}

	public bool isEnabled
	{
		get
		{
			return false;
		}
	}

	public PropertyReference()
	{
	}

	public PropertyReference(Component target, string fieldName)
	{
	}

	public Type GetPropertyType()
	{
		return null;
	}

	public override bool Equals(object obj)
	{
		return false;
	}

	public override int GetHashCode()
	{
		return 0;
	}

	public void Set(Component target, string methodName)
	{
	}

	public void Clear()
	{
	}

	public void Reset()
	{
	}

	public override string ToString()
	{
		return null;
	}

	public static string ToString(Component comp, string property)
	{
		return null;
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	public object Get()
	{
		return null;
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	public bool Set(object value)
	{
		return false;
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	private bool Cache()
	{
		return false;
	}

	private bool Convert(ref object value)
	{
		return false;
	}

	public static bool Convert(Type from, Type to)
	{
		return false;
	}

	public static bool Convert(object value, Type to)
	{
		return false;
	}

	public static bool Convert(ref object value, Type from, Type to)
	{
		return false;
	}
}
