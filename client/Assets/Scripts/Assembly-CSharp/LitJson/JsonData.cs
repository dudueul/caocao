using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.Specialized;

namespace LitJson
{
	public class JsonData : IJsonWrapper, IEquatable<JsonData>, IList, IOrderedDictionary, IEnumerable, ICollection, IDictionary
	{
		private IList<JsonData> inst_array;

		private bool inst_boolean;

		private double inst_double;

		private int inst_int;

		private long inst_long;

		private IDictionary<string, JsonData> inst_object;

		private string inst_string;

		private string json;

		private JsonType type;

		private IList<KeyValuePair<string, JsonData>> object_list;

		int ICollection.Count
		{
			get
			{
				return 0;
			}
		}

		bool ICollection.IsSynchronized
		{
			get
			{
				return false;
			}
		}

		object ICollection.SyncRoot
		{
			get
			{
				return null;
			}
		}

		bool IDictionary.IsFixedSize
		{
			get
			{
				return false;
			}
		}

		bool IDictionary.IsReadOnly
		{
			get
			{
				return false;
			}
		}

		ICollection IDictionary.Keys
		{
			get
			{
				return null;
			}
		}

		ICollection IDictionary.Values
		{
			get
			{
				return null;
			}
		}

		bool IJsonWrapper.IsArray
		{
			get
			{
				return false;
			}
		}

		bool IJsonWrapper.IsBoolean
		{
			get
			{
				return false;
			}
		}

		bool IJsonWrapper.IsDouble
		{
			get
			{
				return false;
			}
		}

		bool IJsonWrapper.IsInt
		{
			get
			{
				return false;
			}
		}

		bool IJsonWrapper.IsLong
		{
			get
			{
				return false;
			}
		}

		bool IJsonWrapper.IsObject
		{
			get
			{
				return false;
			}
		}

		bool IJsonWrapper.IsString
		{
			get
			{
				return false;
			}
		}

		bool IList.IsFixedSize
		{
			get
			{
				return false;
			}
		}

		bool IList.IsReadOnly
		{
			get
			{
				return false;
			}
		}

		// C# has no syntax for parameterized property 'System.Collections.IDictionary.Item'.
		object IDictionary.get_Item(object key)
		{
			return null;
		}

		void IDictionary.set_Item(object key, object value)
		{
		}

		// C# has no syntax for parameterized property 'System.Collections.Specialized.IOrderedDictionary.Item'.
		object IOrderedDictionary.get_Item(int idx)
		{
			return null;
		}

		void IOrderedDictionary.set_Item(int idx, object value)
		{
		}

		// C# has no syntax for parameterized property 'System.Collections.IList.Item'.
		object IList.get_Item(int index)
		{
			return null;
		}

		void IList.set_Item(int index, object value)
		{
		}

		public int Count
		{
			get
			{
				return 0;
			}
		}

		public bool IsArray
		{
			get
			{
				return false;
			}
		}

		public bool IsBoolean
		{
			get
			{
				return false;
			}
		}

		public bool IsDouble
		{
			get
			{
				return false;
			}
		}

		public bool IsInt
		{
			get
			{
				return false;
			}
		}

		public bool IsLong
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

		public bool IsString
		{
			get
			{
				return false;
			}
		}

		// C# has no syntax for parameterized property 'Item'.
		public JsonData get_Item(string prop_name)
		{
			return null;
		}

		public void set_Item(string prop_name, JsonData value)
		{
		}

		// C# has no syntax for parameterized property 'Item'.
		public JsonData get_Item(int index)
		{
			return null;
		}

		public void set_Item(int index, JsonData value)
		{
		}

		public JsonData()
		{
		}

		public JsonData(bool boolean)
		{
		}

		public JsonData(double number)
		{
		}

		public JsonData(int number)
		{
		}

		public JsonData(long number)
		{
		}

		public JsonData(ulong number)
		{
		}

		public JsonData(object obj)
		{
		}

		public JsonData(string str)
		{
		}

		public static implicit operator JsonData(bool data)
		{
			return null;
		}

		public static implicit operator JsonData(double data)
		{
			return null;
		}

		public static implicit operator JsonData(int data)
		{
			return null;
		}

		public static implicit operator JsonData(long data)
		{
			return null;
		}

		public static implicit operator JsonData(ulong data)
		{
			return null;
		}

		public static implicit operator JsonData(string data)
		{
			return null;
		}

		public static explicit operator bool(JsonData data)
		{
			return false;
		}

		public static explicit operator double(JsonData data)
		{
			return 0.0;
		}

		public static explicit operator int(JsonData data)
		{
			return 0;
		}

		public static explicit operator long(JsonData data)
		{
			return 0L;
		}

		public static explicit operator ulong(JsonData data)
		{
			return 0uL;
		}

		public static explicit operator string(JsonData data)
		{
			return null;
		}

		void ICollection.CopyTo(Array array, int index)
		{
		}

		void IDictionary.Add(object key, object value)
		{
		}

		void IDictionary.Clear()
		{
		}

		bool IDictionary.Contains(object key)
		{
			return false;
		}

		IDictionaryEnumerator IDictionary.GetEnumerator()
		{
			return null;
		}

		void IDictionary.Remove(object key)
		{
		}

		IEnumerator IEnumerable.GetEnumerator()
		{
			return null;
		}

		bool IJsonWrapper.GetBoolean()
		{
			return false;
		}

		double IJsonWrapper.GetDouble()
		{
			return 0.0;
		}

		int IJsonWrapper.GetInt()
		{
			return 0;
		}

		long IJsonWrapper.GetLong()
		{
			return 0L;
		}

		string IJsonWrapper.GetString()
		{
			return null;
		}

		void IJsonWrapper.SetBoolean(bool val)
		{
		}

		void IJsonWrapper.SetDouble(double val)
		{
		}

		void IJsonWrapper.SetInt(int val)
		{
		}

		void IJsonWrapper.SetLong(long val)
		{
		}

		void IJsonWrapper.SetString(string val)
		{
		}

		string IJsonWrapper.ToJson()
		{
			return null;
		}

		void IJsonWrapper.ToJson(JsonWriter writer)
		{
		}

		int IList.Add(object value)
		{
			return 0;
		}

		void IList.Clear()
		{
		}

		bool IList.Contains(object value)
		{
			return false;
		}

		int IList.IndexOf(object value)
		{
			return 0;
		}

		void IList.Insert(int index, object value)
		{
		}

		void IList.Remove(object value)
		{
		}

		void IList.RemoveAt(int index)
		{
		}

		IDictionaryEnumerator IOrderedDictionary.GetEnumerator()
		{
			return null;
		}

		void IOrderedDictionary.Insert(int idx, object key, object value)
		{
		}

		void IOrderedDictionary.RemoveAt(int idx)
		{
		}

		private ICollection EnsureCollection()
		{
			return null;
		}

		private IDictionary EnsureDictionary()
		{
			return null;
		}

		private IList EnsureList()
		{
			return null;
		}

		private JsonData ToJsonData(object obj)
		{
			return null;
		}

		private static void WriteJson(IJsonWrapper obj, JsonWriter writer)
		{
		}

		public int Add(object value)
		{
			return 0;
		}

		public void Clear()
		{
		}

		public bool Equals(JsonData x)
		{
			return false;
		}

		public JsonType GetJsonType()
		{
			return JsonType.None;
		}

		public void SetJsonType(JsonType type)
		{
		}

		public string ToJson()
		{
			return null;
		}

		public void ToJson(JsonWriter writer)
		{
		}

		public override string ToString()
		{
			return null;
		}
	}
}
