using System.Xml;
using UnityEngine;

public class EventUnitCell : EventCellPatterns
{
	public int m_nCodeIndex;

	private long m_lSerial;

	private EnumData.EventCellTypes m_nClassCode;

	private bool m_bEnable;

	private EnumData.EventSteps m_eStep;

	public bool m_bFlagTemp;

	public int m_nVariableTemp;

	public string m_sStringTemp;

	private const float m_fModify = 0.1f;

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

	public EnumData.EventCellTypes ClassCode
	{
		get
		{
			return EnumData.EventCellTypes._NONE;
		}
		set
		{
		}
	}

	public bool Enable
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public EnumData.EventSteps Step
	{
		get
		{
			return EnumData.EventSteps.STAND_BY;
		}
		set
		{
		}
	}

	public void ConvertClassCode(int nIndex)
	{
	}

	public EnumData.EventUnitState OnUpdate(bool IsSkip = false)
	{
		return EnumData.EventUnitState.RUNNING;
	}

	public void SetImportedXmlData(ref XmlNode xmlNode, long lSerial)
	{
	}

	public void _SetImportedXmlData(EnumData.EventCellTypes ClassCode, ref XmlNode xmlNode)
	{
	}

	private void GetUnitInfoFromXmlData(ref int nIndex, ref string sNickName, ref XmlNode xmlNode)
	{
	}

	private void GetPositionFromXmlData(ref Vector2 vPos, ref XmlNode xmlNode)
	{
	}

	public int ConvertToUnitIndex(int nUnitID)
	{
		return 0;
	}
}
