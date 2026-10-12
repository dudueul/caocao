using System;
using System.Collections.Generic;

internal static class DeepCloneHelper
{
	public static T DeepClone<T>(T obj, Type exceptListAdoptedType = null, List<string> exceptFieldName = null, List<Type> exceptType = null)
	{
		return default(T);
	}

	private static object CloneProcedure(object obj, Type exceptListAdoptedType = null, List<string> exceptFieldNames = null, List<Type> exceptTypes = null)
	{
		return null;
	}
}
