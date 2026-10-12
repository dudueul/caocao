using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Xml;
using LitJson;

[Serializable]
public class EventCmd212 : EventCmdBase
{
	public static readonly string ms_code;

	public List<int> instanceIds;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void OnCheckInstances(JsonData response)
	{
	}

	[DebuggerHidden]
	private IEnumerator Wait()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
