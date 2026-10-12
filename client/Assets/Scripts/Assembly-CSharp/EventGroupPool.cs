using System.Collections.Generic;
using System.Xml;

public class EventGroupPool
{
	private List<EventGroup> m_EventGroupPool;

	private int m_nCurrentGroupIndex;

	public List<EventGroup> GetEventGroupPool
	{
		get
		{
			return null;
		}
	}

	private EventGroup GetEventGroupData(int nWindowID)
	{
		return null;
	}

	private int CreateGroupID(int nIndex)
	{
		return 0;
	}

	public void CreateEventGroupPool()
	{
	}

	private void AddEventGroup()
	{
	}

	private void AddEventGroups(int nCount)
	{
	}

	public void DeleteAllEventGroup()
	{
	}

	public bool OnUpdate()
	{
		return false;
	}

	public void SetImportedXmlData(ref XmlNode xmlNode)
	{
	}

	private void AddImportedEventGroup(ref XmlNode xmlNode)
	{
	}

	public int GetNextAddUnit(long lSerial, int addIndex)
	{
		return 0;
	}
}
