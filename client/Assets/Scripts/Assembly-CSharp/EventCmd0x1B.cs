using System;
using System.Xml;

[Serializable]
public class EventCmd0x1B : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public bool bFlag;

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
