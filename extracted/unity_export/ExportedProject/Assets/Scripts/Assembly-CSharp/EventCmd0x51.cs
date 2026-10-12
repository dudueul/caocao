using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd0x51 : EventCmdBase
{
	public static readonly string ms_code;

	public List<string> ignoreNicks;

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
