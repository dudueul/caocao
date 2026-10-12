using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd537 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public int nHP;

	public int nMP;

	public int nEP;

	public List<EnumData.Conditions> applyConditions;

	public List<EnumData.Conditions> releaseConditions;

	public bool usePercent;

	public bool releaseAllBuffs;

	public bool releaseAllDebuffs;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	public IEnumerator ChangeUnitStateUnit(float waitingTime)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ApplyUnitStatus(UnitData data, float waitingTime)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
