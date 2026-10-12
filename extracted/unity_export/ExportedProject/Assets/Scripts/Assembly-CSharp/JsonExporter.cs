using System;
using System.Collections.Generic;
using LitJson;

public static class JsonExporter
{
	public static void Export<T>(this List<T> obj, out JsonData jsonData) where T : IJsonExportable
	{
		jsonData = null;
	}

	public static void Export(this IJsonExportable obj, out JsonData jsonData)
	{
		jsonData = null;
	}

	private static void ExportImpl(out JsonData jsonData, Type type, object v)
	{
		jsonData = null;
	}
}
