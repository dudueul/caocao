using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd534 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public int nTargetAreaIndex;

	public int nTargetAreaBonusAIPoint;

	public List<IntRect> coords;

	public int nAttackAreaIndex;

	public IntVector2 vAttackAreaPivot;

	public TargetInfo pivotUnitInfo;

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
