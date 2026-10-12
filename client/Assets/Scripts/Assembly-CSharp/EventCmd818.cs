using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd818 : EventCmdBase
{
	public static readonly string ms_code;

	public List<GameObjectActivationInfo> goActivations;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
