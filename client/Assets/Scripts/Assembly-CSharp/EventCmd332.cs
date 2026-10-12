using System;
using System.Xml;

[Serializable]
public class EventCmd332 : EventCmdBase
{
	public static readonly string ms_code;

	public int targetScenario;

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
