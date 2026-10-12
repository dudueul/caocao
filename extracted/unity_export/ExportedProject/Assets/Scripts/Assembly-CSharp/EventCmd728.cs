using System;
using System.Xml;

[Serializable]
public class EventCmd728 : EventCmdBase
{
	public static readonly string ms_code;

	public bool checkType;

	public EnumData.GuildSiegeBuildingEffectTargetType targetFacilityCheckType;

	public EnumData.GuildFacilityType facilityType;

	public int facilityId;

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
