using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd526 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public int spActionId;

	public int spActionValue;

	public bool addPassive;

	public SpActionBase.Origin origin;

	public int passiveListId;

	private Dictionary<UnitData, SpActionBase.Origin> removedSpActionOrigin;

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

	private void RunPassiveList(bool revert = false)
	{
	}

	private void PassiveApply(EnumData.spAction spaction, int spactionValue, bool revert)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
