using System;
using System.Runtime.InteropServices;
using System.Xml;
using Caocao;

[Serializable]
[StructLayout((LayoutKind)0, Size = 48)]
public struct SpawnInfo : IEventXmlSerializable
{
	public int objectId;

	public int unitId;

	public int unitLevel;

	public int unitAi;

	public IntVector2 pos;

	public int triggerVariable;

	public int creatureId;

	public int creatureLevel;

	public int spawnAmount;

	public UnitDirection spawnDirection;

	public int aiIndex;

	public bool isActiveSpawner
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
