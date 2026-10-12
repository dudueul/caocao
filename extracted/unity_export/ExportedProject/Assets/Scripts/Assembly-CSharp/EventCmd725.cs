using System;
using System.Xml;

[Serializable]
public class EventCmd725 : EventCmdBase
{
	public static readonly string ms_code;

	public string start;

	public string end;

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
