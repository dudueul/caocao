using System;
using System.Xml;

[Serializable]
public class EventCmd997 : EventCmdBase
{
	public static readonly string ms_code;

	public string eventTokenToAndroid;

	public string eventTokenToIOS;

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
