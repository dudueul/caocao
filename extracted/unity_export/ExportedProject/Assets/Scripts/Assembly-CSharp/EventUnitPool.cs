using System.Collections.Generic;
using System.Xml;

public class EventUnitPool
{
	private Dictionary<long, EventUnit> m_EventUnitPool;

	private Dictionary<long, bool> m_EventUnitStatePool;

	private long m_lStartUnitSerial;

	private long m_lCurrentUnitSerial;

	public Dictionary<long, EventUnit> GetEventUnitPool
	{
		get
		{
			return null;
		}
	}

	public void CreateEventPool()
	{
	}

	private void AddEventUnit(long lSerial, EventUnit unit, bool bIsEmpty)
	{
	}

	private long GetSerial()
	{
		return 0L;
	}

	private void GetEventUnitData(long lSerial, out EventUnit eEventUnit)
	{
		eEventUnit = null;
	}

	public void DeleteEventUnitPool()
	{
	}

	public bool OnUpdate()
	{
		return false;
	}

	public void SetImportedXmlData(ref XmlNode xmlNode)
	{
	}

	public int GetNextAddUnit(long lSerial, int addIndex)
	{
		return 0;
	}
}
