using System;
using System.Collections.Generic;
using System.IO;
using System.Reflection;
using UnityEngine;

public class UnityHeapDump
{
	private class StructOrClass
	{
		private List<StructOrClass> Children;

		public int Size { get; private set; }

		public Type ParsedType { get; private set; }

		public int InstanceID { get; private set; }

		private int ArraySize { get; set; }

		private string Identifier { get; set; }

		public StructOrClass(Type type, string assemblyFolder)
		{
		}

		public StructOrClass(UnityEngine.Object uObject)
		{
		}

		private StructOrClass(string name, object root, TypeData rootTypeData, HashSet<object> seenObjects)
		{
		}

		private void ParseField(FieldInfo fieldInfo, object root, HashSet<object> seenObjects)
		{
		}

		private void ParseItem(object obj, string objName, HashSet<object> seenObjects)
		{
		}

		private void Write(StreamWriter writer, string indent)
		{
		}

		private static int GetTotalLength(Array val)
		{
			return 0;
		}
	}

	public class TypeData
	{
		private static Dictionary<Type, TypeData> seenTypeData;

		private static Dictionary<Type, TypeData> seenTypeDataNested;

		public int Size { get; private set; }

		public List<FieldInfo> DynamicSizedFields { get; private set; }

		public TypeData(Type type, bool nested = false)
		{
		}

		public static void Clear()
		{
		}

		public static void Start()
		{
		}

		public static TypeData Get(Type type)
		{
			return null;
		}

		public static TypeData GetNested(Type type)
		{
			return null;
		}

		private void ProcessField(FieldInfo field, Type fieldType)
		{
		}

		private static bool IsStaticallySized(Type type)
		{
			return false;
		}

		private static int GetStaticSize(Type type)
		{
			return 0;
		}
	}

	private const int TYPE_MIN_SIZE_TO_PRINT = 1;

	private const int ROOT_MIN_SIZE = 1;

	private const int CHILD_MIN_SIZE = 1;

	private static int ThreadJobsPrinting;

	private static int ThreadJobsDone;

	private static HashSet<Type> genericTypes;

	public static void Create()
	{
	}
}
