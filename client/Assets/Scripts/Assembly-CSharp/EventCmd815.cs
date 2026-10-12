using System;
using System.Xml;

[Serializable]
public class EventCmd815 : EventCmdBase
{
	public static readonly string ms_code;

	public bool aiState;

	private bool m_backupEntrustTurn;

	private bool m_backupEntrustBattle;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
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
