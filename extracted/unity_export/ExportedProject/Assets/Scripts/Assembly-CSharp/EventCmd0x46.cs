using System;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd0x46 : EventCmdBase
{
	public static readonly string ms_code;

	public bool bIsHide;

	public UnitInfo unitInfo;

	public IntVector2 vPos;

	public UnitDirection nDirect;

	public int nGradeIndex;

	public int nQuality;

	public int nAIIndex;

	public TargetInfo aiTargetUnitInfo;

	public IntVector2 aiTargetPos;

	public int bfIndex;

	private UnitData m_unitData;

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
