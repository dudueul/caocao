using System;
using System.Xml;

[Serializable]
public class EventCmd0x0C : EventCmdBase
{
	public static readonly string ms_code;

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
