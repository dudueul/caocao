using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x21 : EventCmdBase
{
	public static readonly string ms_code;

	public IntVector2 vPos;

	public int nState;

	public bool bOption1;

	public bool bOption2;

	[NonSerialized]
	private BattleMapObject m_object;

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
	private IEnumerator Waiting()
	{
		return null;
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
