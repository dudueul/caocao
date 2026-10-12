using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd541 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public List<EnumData.Skills> addedSkills;

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

	private void AddSkills(UnitData unitData)
	{
	}

	private void RemoveSkills(UnitData unitData)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
