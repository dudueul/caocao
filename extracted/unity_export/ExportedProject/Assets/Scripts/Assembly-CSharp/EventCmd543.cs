using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd543 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public int spActionId;

	private Dictionary<UnitData, List<SpActionBase>> removedSpActions;

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

	private void RunPassive(bool revert = false)
	{
	}

	private void PassiveApply(EnumData.spAction spaction, bool revert)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
