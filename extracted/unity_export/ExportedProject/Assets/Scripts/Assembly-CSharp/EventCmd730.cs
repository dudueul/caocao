using System;
using System.Xml;

[Serializable]
public class EventCmd730 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo triggerUnitInfo;

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
