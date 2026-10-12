using System;
using System.Collections.Generic;
using System.Reflection;
using UnityEngine;

[Serializable]
public class EventDelegate
{
	[Serializable]
	public class Parameter
	{
		public UnityEngine.Object obj;

		public string field;

		[NonSerialized]
		private object mValue;

		[NonSerialized]
		public Type expectedType;

		[NonSerialized]
		public bool cached;

		[NonSerialized]
		public PropertyInfo propInfo;

		[NonSerialized]
		public FieldInfo fieldInfo;

		public object value
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public Type type
		{
			get
			{
				return null;
			}
		}

		public Parameter()
		{
		}

		public Parameter(UnityEngine.Object obj, string field)
		{
		}

		public Parameter(object val)
		{
		}
	}

	public delegate void Callback();

	[SerializeField]
	private MonoBehaviour mTarget;

	[SerializeField]
	private string mMethodName;

	[SerializeField]
	private Parameter[] mParameters;

	public bool oneShot;

	[NonSerialized]
	private Callback mCachedCallback;

	[NonSerialized]
	private bool mRawDelegate;

	[NonSerialized]
	private bool mCached;

	[NonSerialized]
	private MethodInfo mMethod;

	[NonSerialized]
	private ParameterInfo[] mParameterInfos;

	[NonSerialized]
	private object[] mArgs;

	private static int s_Hash;

	public MonoBehaviour target
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string methodName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Parameter[] parameters
	{
		get
		{
			return null;
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

	public EventDelegate()
	{
	}

	public EventDelegate(Callback call)
	{
	}

	public EventDelegate(MonoBehaviour target, string methodName)
	{
	}

	private static string GetMethodName(Callback callback)
	{
		return null;
	}

	private static bool IsValid(Callback callback)
	{
		return false;
	}

	public override bool Equals(object obj)
	{
		return false;
	}

	public override int GetHashCode()
	{
		return 0;
	}

	private void Set(Callback call)
	{
	}

	public void Set(MonoBehaviour target, string methodName)
	{
	}

	private void Cache()
	{
	}

	public bool Execute()
	{
		return false;
	}

	public void Clear()
	{
	}

	public override string ToString()
	{
		return null;
	}

	public static void Execute(List<EventDelegate> list)
	{
	}

	public static bool IsValid(List<EventDelegate> list)
	{
		return false;
	}

	public static EventDelegate Set(List<EventDelegate> list, Callback callback)
	{
		return null;
	}

	public static void Set(List<EventDelegate> list, EventDelegate del)
	{
	}

	public static EventDelegate Add(List<EventDelegate> list, Callback callback)
	{
		return null;
	}

	public static EventDelegate Add(List<EventDelegate> list, Callback callback, bool oneShot)
	{
		return null;
	}

	public static void Add(List<EventDelegate> list, EventDelegate ev)
	{
	}

	public static void Add(List<EventDelegate> list, EventDelegate ev, bool oneShot)
	{
	}

	public static bool Remove(List<EventDelegate> list, Callback callback)
	{
		return false;
	}

	public static bool Remove(List<EventDelegate> list, EventDelegate ev)
	{
		return false;
	}
}
