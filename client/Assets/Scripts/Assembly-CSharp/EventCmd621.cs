using System;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd621 : EventCmdBase
{
	public static readonly string ms_code;

	public UnitInfo unitInfo;

	public IntVector2 pos;

	public UnitDirection direct;

	public bool hide;

	public int level;

	public int groupNo;

	[NonSerialized]
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
