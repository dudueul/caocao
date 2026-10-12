using System;
using System.Runtime.InteropServices;
using System.Xml;
using Caocao;

[Serializable]
[StructLayout((LayoutKind)0, Size = 24)]
public struct MapObjectInfo : IEventXmlSerializable
{
	public int objectId;

	public IntVector2 pos;

	public int creation;

	public int attribute;

	public ObjectSide side;

	public bool IsUnit
	{
		get
		{
			return false;
		}
	}

	public void ImportFromXml(XmlNode xmlNode)
	{
	}
}
