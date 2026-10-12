using System;
using System.Xml;

[Serializable]
public class EventCmd0x3E : EventCmdBase
{
	public static readonly string ms_code;

	public int unitID;

	public TargetInfo targetUnitInfo;

	public int nWeaponIndex;

	public int nWeaponGradeIndex;

	public int nInstrumentIndex;

	public int nInstrumentGradeIndex;

	public int nExtraItem;

	public string editorUnitName;

	public string editorItemName;

	public string editorInstrumentItemName;

	public string editorExtraItemName;

	public override string ToString()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
