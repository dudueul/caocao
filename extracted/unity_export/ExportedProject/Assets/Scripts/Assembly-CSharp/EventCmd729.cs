using System;
using System.Xml;

[Serializable]
public class EventCmd729 : EventCmdBase
{
	public static readonly string ms_code;

	public EnumData.RaidType raidType;

	public EnumData.RaidUseType raidUseType;

	public EnumData.RaidDifficulty raidDifficulty;

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
