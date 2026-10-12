using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x5D : EventCmdBase
{
	public static readonly string ms_code;

	public int nParam;

	public EnumData.CalculatingSign nCalSignIndex;

	public bool hidePopup;

	private int m_turnBackup;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowPopUp(int limitCount)
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
