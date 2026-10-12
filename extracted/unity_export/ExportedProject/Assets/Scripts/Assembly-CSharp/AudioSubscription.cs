using System;
using System.Collections.Generic;
using System.Reflection;
using UnityEngine;
using antilunchbox;

[Serializable]
public class AudioSubscription
{
	public AudioSourcePro owner;

	public bool isStandardEvent;

	public AudioSourceStandardEvent standardEvent;

	public Component sourceComponent;

	public string methodName;

	private bool isBound;

	public AudioSourceAction actionType;

	public string cappedName;

	public bool filterLayers;

	public bool filterTags;

	public bool filterNames;

	public int tagMask;

	public int nameMask;

	public string nameToAdd;

	public List<string> allNames;

	public int layerMask;

	public List<string> tags;

	public List<string> names;

	private Component targetComponent;

	private FieldInfo eventField;

	private Delegate eventDelegate;

	private MethodInfo handlerProxy;

	private ParameterInfo[] handlerParameters;

	public bool componentIsValid
	{
		get
		{
			return false;
		}
	}

	public bool standardEventIsValid
	{
		get
		{
			return false;
		}
	}

	public void Bind(AudioSourcePro sourcePro)
	{
	}

	public void Unbind()
	{
	}

	private FieldInfo getField(Component sourceComponent, string fieldName)
	{
		return null;
	}

	private bool signatureIsCompatible(ParameterInfo[] lhs, ParameterInfo[] rhs)
	{
		return false;
	}

	private bool areTypesCompatible(ParameterInfo lhs, ParameterInfo rhs)
	{
		return false;
	}

	[ProxyEvent]
	private void CallbackProxy()
	{
	}

	private Delegate createProxyEventDelegate(object target, Type delegateType, ParameterInfo[] eventParams, MethodInfo eventHandler)
	{
		return null;
	}

	private void callProxyEventHandler(params object[] arguments)
	{
	}

	private MethodInfo getMethodInfoForAction(AudioSourceAction act)
	{
		return null;
	}
}
