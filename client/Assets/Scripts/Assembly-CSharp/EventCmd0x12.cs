using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Xml;
using LitJson;
using UnityEngine;

[Serializable]
public class EventCmd0x12 : EventCmdBase
{
	public static readonly string ms_code;

	public int unitID;

	public string selectionText;

	public string question;

	public List<int> nSaveFlagIDList;

	[NonSerialized]
	private PanelAmbition m_panelAmbition;

	public int localSaveFlagID;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void OnSelect(int selected, bool isSameSelect)
	{
	}

	private void OnFlagSaveResult(JsonData response)
	{
	}

	private void UpdateGauge()
	{
	}

	private void ShowAmbitionGauge()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitForClosingAmbition(GameObject ambition)
	{
		return null;
	}

	private void CloseAmbitionPanel()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
