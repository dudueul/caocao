using System;
using System.Xml;

[Serializable]
public class EventSection : EventCmdBase
{
	public static readonly string ms_code;

	public string memo;

	public override string ToString()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
