using System;
using System.Collections.Generic;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd640 : EventCmdBase
{
	public static readonly string ms_code;

	public List<IntRect> leftArrowAreas;

	public List<IntRect> rightArrowAreas;

	public List<int> arrowDamages;

	public List<int> arrowMpDamages;

	public List<int> turns;

	public int leftArcherUnitId;

	public IntVector2 leftArcherPos;

	public UnitDirection leftArcherDir;

	public int rightArcherUnitId;

	public IntVector2 rightArcherPos;

	public UnitDirection rightArcherDir;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public override void _Revert()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
