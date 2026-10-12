using System;
using System.Xml;

[Serializable]
public class EventCmd720 : EventCmdBase
{
	public static readonly string ms_code;

	public int scenarioId;

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
