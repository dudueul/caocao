using System;
using System.Xml;

[Serializable]
public class EventCmd721 : EventCmdBase
{
	public static readonly string ms_code;

	public string path;

	public bool active;

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
