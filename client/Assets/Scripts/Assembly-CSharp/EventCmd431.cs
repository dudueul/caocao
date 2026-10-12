using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Xml;
using LitJson;
using UnityEngine;

[Serializable]
public class EventCmd431 : EventCmdBase
{
	public static readonly string ms_code;

	public string m_prefabName;

	public string text;

	public string sound;

	public EnumData.TargetDirection arrow;

	public bool m_destroyAnywhereTouched;

	public List<string> m_maskingUIPath;

	public bool m_UICameraEventOn;

	public Vector2 extend;

	public string clickButtonOnRun;

	private GameObject m_tutorialGameObj;

	private List<Transform> visibleTrsfs;

	private List<Event431Helper> eventHelpers;

	private List<GameObject> colliderObjects;

	public bool m_waitForResponse;

	public string m_pathToWaitForResponse;

	public bool waitForResponse
	{
		get
		{
			return false;
		}
	}

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator AddFullScreenCollider(Transform blackBG)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator WaitSpecificAreaInteraction(Transform blackBG)
	{
		return null;
	}

	public void OnCompleteRequest(JsonData resoponse)
	{
	}

	private void MakeSureThatTargetIsVisible(List<Bounds> bounds)
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitForDirecitonEnd()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
