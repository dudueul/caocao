using System;
using System.Xml;

[Serializable]
public class EventCmd0x3C : EventCmdBase
{
	public static readonly string ms_code;

	public int unitID;

	public EnumData.UnitJoining joining;

	private EnumData.CompareSign calSignIndex;

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
