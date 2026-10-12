using System.Xml;

public class EventUnit
{
	private EnumData.EventUnitTypes m_eClassCode;

	private EnumData.EventUnitValueClasses m_eClassValue;

	private long m_lPreLinkedUnitSerial;

	private long m_lNextLinkedUnitSerial;

	private EnumData.EventUnitState m_eState;

	private long m_lSerial;

	private EventUnitCellPool m_cEventUnitCellPool;

	public EnumData.EventUnitTypes ClassCode
	{
		get
		{
			return EnumData.EventUnitTypes.START;
		}
		set
		{
		}
	}

	public EnumData.EventUnitValueClasses ClassValue
	{
		get
		{
			return EnumData.EventUnitValueClasses.NONE;
		}
		set
		{
		}
	}

	public long PreLinkedUnitSerial
	{
		get
		{
			return 0L;
		}
		set
		{
		}
	}

	public long NextLinkedUnitSerial
	{
		get
		{
			return 0L;
		}
		set
		{
		}
	}

	public EnumData.EventUnitState UnitState
	{
		get
		{
			return EnumData.EventUnitState.RUNNING;
		}
	}

	public long Serial
	{
		get
		{
			return 0L;
		}
		set
		{
		}
	}

	public EventUnit()
	{
	}

	public EventUnit(long lSerial, long lPreSerial, long lNextSerial, EnumData.EventUnitTypes eType, EnumData.EventUnitValueClasses eValue)
	{
	}

	public EventUnit(long lSerial, ref EventUnit targetEventUnit, EnumData.EventUnitTypes eType, EnumData.EventUnitValueClasses eValue)
	{
	}

	public void DeleteEventUnitCellPool()
	{
	}

	public void SetUnitInformation(long lSerial, long lPreSerial, long lNextSerial, EnumData.EventUnitTypes eType, EnumData.EventUnitValueClasses eValue)
	{
	}

	public EnumData.EventUnitState OnUpdate()
	{
		return EnumData.EventUnitState.RUNNING;
	}

	public void SetImportedXmlData(ref XmlNode xmlNode)
	{
	}

	public int GetNextAddUnit(int addIndex)
	{
		return 0;
	}
}
