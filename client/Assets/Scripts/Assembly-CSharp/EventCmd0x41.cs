using System;
using System.Xml;

[Serializable]
public class EventCmd0x41 : EventCmdBase
{
	public static readonly string ms_code;

	public EnumData.ForceType nGroupIndex;

	public EnumData.CompareSign nCalSignIndex;

	public int nTargetAreaIndex;

	public int nParam;

	public IntVector2 vStartPos;

	public IntVector2 vEndPos;

	public int nAttackAreaIndex;

	public IntVector2 vAttackAreaPivot;

	public TargetInfo targetUnitInfo;

	public bool includeObjectUnit;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void GetAttackRangeCount(IntVector2 pivotPos, out int liveUnitCount)
	{
		liveUnitCount = default(int);
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
