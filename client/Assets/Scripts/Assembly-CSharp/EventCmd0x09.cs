using System;
using System.Xml;

[Serializable]
public class EventCmd0x09 : EventCmdBase
{
	public static readonly string ms_code;

	public float watingSeconds;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public override void Skip()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
