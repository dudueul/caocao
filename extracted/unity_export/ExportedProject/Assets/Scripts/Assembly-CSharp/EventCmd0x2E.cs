using System;
using System.Xml;

[Serializable]
public class EventCmd0x2E : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo1;

	public TargetInfo targetUnitInfo2;

	public bool bNeighboring;

	public string editorUnitName;

	public string editorSubUnitName;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void UpdateMission(int unitId1, int unitId2)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
