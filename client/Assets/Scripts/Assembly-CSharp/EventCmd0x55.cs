using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd0x55 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public IntVector2 vPos;

	public UnitDirection nDirect;

	public int nAIIndex;

	public bool bAllRevival;

	public bool bWaiting;

	public EnumData.RevivalPosType revivalPosType;

	public TargetInfo revivalPosTargetInfo;

	public bool countRevivalNum;

	public override string ToString()
	{
		return null;
	}

	private IntVector2 FindRevivalPos(UnitData unit)
	{
		return default(IntVector2);
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator Waiting()
	{
		return null;
	}

	private void UnitRevival(UnitData unit)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
