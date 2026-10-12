using System;
using System.Xml;

[Serializable]
public class EventCmd0x45 : EventCmdBase
{
	public static readonly string ms_code;

	public MapVariable mapVariable;

	public bool isAdjustByPVPMode;

	public bool isAdjustByInstanceMode;

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
