using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;
using UnityEngine;

public class JSONObject
{
	public enum Type
	{
		NULL = 0,
		STRING = 1,
		NUMBER = 2,
		OBJECT = 3,
		ARRAY = 4,
		BOOL = 5,
		BAKED = 6
	}

	public delegate void AddJSONConents(JSONObject self);

	public delegate void FieldNotFound(string name);

	public delegate void GetFieldResponse(JSONObject obj);

	private const int MAX_DEPTH = 100;

	private const string INFINITY = "\"INFINITY\"";

	private const string NEGINFINITY = "\"NEGINFINITY\"";

	private const string NaN = "\"NaN\"";

	private static readonly char[] WHITESPACE;

	public Type type;

	public List<JSONObject> list;

	public List<string> keys;

	public string str;

	public float n;

	public bool b;

	private const float maxFrameTime = 0.008f;

	private static readonly Stopwatch printWatch;

	public bool isContainer
	{
		get
		{
			return false;
		}
	}

	public int Count
	{
		get
		{
			return 0;
		}
	}

	public float f
	{
		get
		{
			return 0f;
		}
	}

	public static JSONObject nullJO
	{
		get
		{
			return null;
		}
	}

	public static JSONObject obj
	{
		get
		{
			return null;
		}
	}

	public static JSONObject arr
	{
		get
		{
			return null;
		}
	}

	public bool IsNumber
	{
		get
		{
			return false;
		}
	}

	public bool IsNull
	{
		get
		{
			return false;
		}
	}

	public bool IsString
	{
		get
		{
			return false;
		}
	}

	public bool IsBool
	{
		get
		{
			return false;
		}
	}

	public bool IsArray
	{
		get
		{
			return false;
		}
	}

	public bool IsObject
	{
		get
		{
			return false;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public JSONObject get_Item(int index)
	{
		return null;
	}

	public void set_Item(int index, JSONObject value)
	{
	}

	// C# has no syntax for parameterized property 'Item'.
	public JSONObject get_Item(string index)
	{
		return null;
	}

	public void set_Item(string index, JSONObject value)
	{
	}

	public JSONObject(Type t)
	{
	}

	public JSONObject(bool b)
	{
	}

	public JSONObject(float f)
	{
	}

	public JSONObject(Dictionary<string, string> dic)
	{
	}

	public JSONObject(Dictionary<string, JSONObject> dic)
	{
	}

	public JSONObject(AddJSONConents content)
	{
	}

	public JSONObject(JSONObject[] objs)
	{
	}

	public JSONObject()
	{
	}

	public JSONObject(string str)
	{
	}

	public JSONObject(string str, int maxDepth, bool storeExcessLevels, bool strict)
	{
	}

	public static JSONObject StringObject(string val)
	{
		return null;
	}

	public void Absorb(JSONObject obj)
	{
	}

	public static JSONObject Create()
	{
		return null;
	}

	public static JSONObject Create(Type t)
	{
		return null;
	}

	public static JSONObject Create(bool val)
	{
		return null;
	}

	public static JSONObject Create(float val)
	{
		return null;
	}

	public static JSONObject Create(int val)
	{
		return null;
	}

	public static JSONObject CreateStringObject(string val)
	{
		return null;
	}

	public static JSONObject CreateBakedObject(string val)
	{
		return null;
	}

	public static JSONObject Create(string val)
	{
		return null;
	}

	public static JSONObject Create(string val, int maxDepth)
	{
		return null;
	}

	public static JSONObject Create(string val, int maxDepth, bool storeExcessLevels)
	{
		return null;
	}

	public static JSONObject Create(string val, int maxDepth, bool storeExcessLevels, bool strict)
	{
		return null;
	}

	public static JSONObject Create(AddJSONConents content)
	{
		return null;
	}

	public static JSONObject Create(Dictionary<string, string> dic)
	{
		return null;
	}

	private void Parse(string str)
	{
	}

	private void Parse(string str, int maxDepth, bool storeExcessLevels, bool strict)
	{
	}

	public void Add(bool val)
	{
	}

	public void Add(float val)
	{
	}

	public void Add(int val)
	{
	}

	public void Add(string str)
	{
	}

	public void Add(AddJSONConents content)
	{
	}

	public void Add(JSONObject obj)
	{
	}

	public void AddField(string name, bool val)
	{
	}

	public void AddField(string name, float val)
	{
	}

	public void AddField(string name, int val)
	{
	}

	public void AddField(string name, AddJSONConents content)
	{
	}

	public void AddField(string name, string val)
	{
	}

	public void AddField(string name, JSONObject obj)
	{
	}

	public void SetField(string name, bool val)
	{
	}

	public void SetField(string name, float val)
	{
	}

	public void SetField(string name, int val)
	{
	}

	public void SetField(string name, JSONObject obj)
	{
	}

	public void RemoveField(string name)
	{
	}

	public void GetField(ref bool field, string name)
	{
	}

	public void GetField(ref bool field, string name, FieldNotFound fail)
	{
	}

	public void GetField(ref float field, string name)
	{
	}

	public void GetField(ref float field, string name, FieldNotFound fail)
	{
	}

	public void GetField(ref int field, string name)
	{
	}

	public void GetField(ref int field, string name, FieldNotFound fail)
	{
	}

	public void GetField(ref uint field, string name)
	{
	}

	public void GetField(ref uint field, string name, FieldNotFound fail)
	{
	}

	public void GetField(ref string field, string name)
	{
	}

	public void GetField(ref string field, string name, FieldNotFound fail)
	{
	}

	public void GetField(string name, GetFieldResponse response)
	{
	}

	public void GetField(string name, GetFieldResponse response, FieldNotFound fail)
	{
	}

	public JSONObject GetField(string name)
	{
		return null;
	}

	public bool HasFields(string[] names)
	{
		return false;
	}

	public bool HasField(string name)
	{
		return false;
	}

	public void Clear()
	{
	}

	public JSONObject Copy()
	{
		return null;
	}

	public void Merge(JSONObject obj)
	{
	}

	private static void MergeRecur(JSONObject left, JSONObject right)
	{
	}

	public void Bake()
	{
	}

	[DebuggerHidden]
	public IEnumerable BakeAsync()
	{
		return null;
	}

	public string Print()
	{
		return null;
	}

	public string Print(bool pretty)
	{
		return null;
	}

	public IEnumerable<string> PrintAsync()
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerable<string> PrintAsync(bool pretty)
	{
		return null;
	}

	private IEnumerable StringifyAsync(int depth, StringBuilder builder)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerable StringifyAsync(int depth, StringBuilder builder, bool pretty)
	{
		return null;
	}

	private void Stringify(int depth, StringBuilder builder)
	{
	}

	private void Stringify(int depth, StringBuilder builder, bool pretty)
	{
	}

	public static implicit operator WWWForm(JSONObject obj)
	{
		return null;
	}

	public override string ToString()
	{
		return null;
	}

	public string ToString(bool pretty)
	{
		return null;
	}

	public Dictionary<string, string> ToDictionary()
	{
		return null;
	}

	public static implicit operator bool(JSONObject o)
	{
		return false;
	}
}
