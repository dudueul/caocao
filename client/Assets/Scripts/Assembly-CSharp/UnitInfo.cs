using System;
using System.Runtime.InteropServices;
using System.Xml;

[Serializable]
[StructLayout((LayoutKind)0, Size = 16)]
public struct UnitInfo : IEventXmlSerializable
{
	public int unitID;

	public string nick;

	public UnitInfo(int id, string nickName)
	{
		unitID = 0;
		nick = null;
	}

	public void ImportFromXml(XmlNode xmlNode)
	{
	}

	public override string ToString()
	{
		return null;
	}

	public override int GetHashCode()
	{
		return 0;
	}

	public override bool Equals(object obj)
	{
		return false;
	}
}
