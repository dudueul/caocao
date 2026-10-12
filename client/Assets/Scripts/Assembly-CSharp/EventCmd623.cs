using System;
using System.Xml;

[Serializable]
public class EventCmd623 : EventCmdBase
{
	public static readonly string ms_code;

	public int slotIdx;

	public IntVector2 position;

	[NonSerialized]
	private UnitData m_unitData;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public override bool _ApplyOnLoading()
	{
		return false;
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
