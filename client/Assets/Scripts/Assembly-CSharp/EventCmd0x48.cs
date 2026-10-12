using System;
using System.Xml;

[Serializable]
public class EventCmd0x48 : EventCmdBase
{
	public static readonly string ms_code;

	public int weaponID;

	public int weaponGrade;

	public int armorID;

	public int armorGrade;

	public int extraID;

	public int unitID;

	public TargetInfo targetUnitInfo;

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

	public override bool _ApplyOnLoading()
	{
		return false;
	}

	public override void _Revert()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
