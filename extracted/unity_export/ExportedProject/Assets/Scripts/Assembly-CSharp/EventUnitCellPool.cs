using System.Collections.Generic;
using System.Xml;

public class EventUnitCellPool
{
	private List<EventUnitCell> m_EventUnitCellPool;

	private int m_nCurrentCellIndex;

	private EnumData.EventUnitState m_eState;

	private List<EnumData.EventDepthType> m_lEventDepth;

	public List<EventUnitCell> GetEventUnitCellPool
	{
		get
		{
			return null;
		}
	}

	public int CurrentCellIndex
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public void AddNewEventCell(int nIndex)
	{
	}

	public void AddEventCell(ref EventUnitCell eventCell)
	{
	}

	public void RemoveEventCell(int nIndex)
	{
	}

	public void DeleteAllEventPool()
	{
	}

	public EnumData.EventUnitState OnUpdate()
	{
		return EnumData.EventUnitState.RUNNING;
	}

	public bool IsEmpty()
	{
		return false;
	}

	public void SetImportedXmlData(ref XmlNode xmlNode, long lSerial)
	{
	}

	public int GetNextAddUnit(int addIndex)
	{
		return 0;
	}
}
