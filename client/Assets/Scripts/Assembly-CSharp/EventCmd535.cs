using System;
using System.Xml;

[Serializable]
public class EventCmd535 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public int score;

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
