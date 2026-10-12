using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x43 : EventCmdBase
{
	public static readonly string ms_code;

	private int m_addTurn;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public void CheckIsLose()
	{
	}

	private void PanelPopupAskAction()
	{
	}

	[DebuggerHidden]
	private IEnumerator _PanelPopupAskAction()
	{
		return null;
	}

	private void UndoAction()
	{
	}

	[DebuggerHidden]
	private IEnumerator _UndoAction()
	{
		return null;
	}

	private void UndoTurn()
	{
	}

	private void OnTurnReverted()
	{
	}

	private static int ExtendTurnCost(int turn)
	{
		return 0;
	}

	private void PanelPopupExtendTurn()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
