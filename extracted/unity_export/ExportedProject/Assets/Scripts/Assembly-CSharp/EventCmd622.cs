using System;
using System.Xml;

[Serializable]
public class EventCmd622 : EventCmdBase
{
	public static readonly string ms_code;

	public int tutorialId;

	public bool going;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public override void _Apply()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
