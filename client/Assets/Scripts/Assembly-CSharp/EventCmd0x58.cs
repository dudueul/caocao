using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd0x58 : EventCmdBase
{
	public static readonly string ms_code;

	public int mapObjID;

	public IntVector2 vPos;

	public int CreationIndex;

	public bool IsFocus;

	public bool Waiting;

	public int AttributeValue;

	public ObjectSide side;

	public string name;

	public bool useAbsoluteLevel;

	public int absoluteLevel;

	[NonSerialized]
	private BattleMapObject m_object;

	[NonSerialized]
	private bool m_activated;

	[NonSerialized]
	private int m_prevState;

	[NonSerialized]
	private BattleMapObject m_sp547Object;

	[NonSerialized]
	private bool m_sp547Activated;

	[NonSerialized]
	private int m_sp547PrevState;

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

	public override bool _ApplyOnLoading()
	{
		return false;
	}

	public override void _Revert()
	{
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
