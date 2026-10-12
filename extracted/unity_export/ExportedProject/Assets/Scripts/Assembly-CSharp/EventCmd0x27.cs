using System;
using System.Xml;

[Serializable]
public class EventCmd0x27 : EventCmdBase
{
	public static readonly string ms_code;

	public EnumData.MapType eMapType;

	public int nLinkedIndexOuter;

	public int nLinkedIndexHuge;

	public int nLinkedIndexInner;

	public int nLinkedIndexBattle;

	public string editorMapName;

	public string findTemp1;

	public string findTemp2;

	public string findTemp3;

	public string findTemp4;

	public int LinkedIndex
	{
		get
		{
			return 0;
		}
	}

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
