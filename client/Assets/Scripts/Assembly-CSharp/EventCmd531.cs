using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd531 : EventCmdBase
{
	private enum ObjectCreation
	{
		Create = 0,
		Modify = 1,
		Delete = 2
	}

	private struct recorder
	{
		public BattleMapObject obj;

		public ObjectCreation creation;

		public int prevState;
	}

	public static readonly string ms_code;

	public List<MapObjectInfo> objectInfoList;

	public bool useArea;

	public List<IntVector2> posList;

	public IntVector2 startPos;

	public IntVector2 endPos;

	public int rate;

	[NonSerialized]
	private List<recorder> m_recorderList;

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

	private void SetObjectOnArea()
	{
	}

	private void SetObjectOnPoints()
	{
	}

	private void SetObject(IntVector2 pos)
	{
	}

	private MapObjectInfo GetObjectInfo()
	{
		return default(MapObjectInfo);
	}

	private IntVector2 GetPos()
	{
		return default(IntVector2);
	}

	private void AddRecordForRevert(BattleMapObject obj, ObjectCreation creation, int prevState = 0)
	{
	}

	private bool Dicing()
	{
		return false;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
