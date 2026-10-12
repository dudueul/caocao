using System;
using System.Xml;

[Serializable]
public class EventCmd627 : EventCmdBase
{
	public static readonly string ms_code;

	public SlotInfo slotInfo;

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

	public override void _Revert()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
