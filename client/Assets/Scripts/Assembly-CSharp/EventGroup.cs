using System.Xml;

public class EventGroup
{
	private int m_nGroupID;

	private EventUnitPool m_cEventUnitPool;

	public int GroupID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public EventGroup()
	{
	}

	public EventGroup(int nID)
	{
	}

	public void DeleteEventGroup()
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
