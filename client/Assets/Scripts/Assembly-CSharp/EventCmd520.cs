using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd520 : EventCmdBase
{
	public static readonly string ms_code;

	public string editorObjectName;

	public int mapObjID;

	public int AttributeValue;

	public IntVector2 vPos;

	public int CreationIndex;

	public bool IsFocus;

	public bool Waiting;

	public EnumData.TownBuildingType objectType;

	private bool m_isUnit;

	[NonSerialized]
	private BattleMapObject m_object;

	[NonSerialized]
	private bool m_activated;

	[NonSerialized]
	private int m_prevState;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator Wait()
	{
		return null;
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	private BattleMapObject CreateMapObject(int statusId, int attributeValue)
	{
		return null;
	}

	private int GetLevel(int mapObjID)
	{
		return 0;
	}

	private int GetBuildingObjectID()
	{
		return 0;
	}

	public override bool _ApplyOnLoading()
	{
		return false;
	}

	private bool IsUnit()
	{
		return false;
	}

	public void SwitchingReconObjectActivity(bool active)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
