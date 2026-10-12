using System;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd828 : EventCmdBase
{
	public static readonly string ms_code;

	private GameObject m_block;

	private Action m_callback;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void OnWorldMapStandby()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
