using System;
using System.Reflection;
using LitJson;

public static class MemberInfoExtensions
{
	public static Type GetDefinedType(this MemberInfo self)
	{
		return null;
	}

	public static void SetValue(this MemberInfo self, object obj, object value)
	{
	}

	public static object GetValue(this MemberInfo self, object obj)
	{
		return null;
	}

	private static object ConvertValue(Type type, JsonData data)
	{
		return null;
	}

	public static void SetValue(this MemberInfo self, object obj, JsonData data)
	{
	}
}
