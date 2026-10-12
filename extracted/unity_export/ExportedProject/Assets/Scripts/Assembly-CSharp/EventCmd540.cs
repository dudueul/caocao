using System;
using System.Xml;

[Serializable]
public class EventCmd540 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public int weaknessSlot;

	public bool isRandomSlot;

	public EnumData.WeaknessType weaknessType;

	public bool isRandomWeakness;

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
