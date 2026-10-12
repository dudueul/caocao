using System;
using System.Xml;

[Serializable]
public class EventCmd999 : EventCmdBase
{
	public static readonly string ms_code;

	public string scriptPath;

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
