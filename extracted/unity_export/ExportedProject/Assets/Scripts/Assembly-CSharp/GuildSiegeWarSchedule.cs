using System;
using LitJson;

public class GuildSiegeWarSchedule : ITransmittable
{
	private DateTime m_begin;

	private DateTime m_end;

	private int[] m_terms;

	public int m_autoStartMinutes;

	public float m_autoChangePer;

	private string m_limitDay;

	private string m_nextLimitDay;

	private bool m_isAppliedNext;

	public DateTime begin
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime end
	{
		get
		{
			return default(DateTime);
		}
	}

	public int[] termMins
	{
		get
		{
			return null;
		}
	}

	public int autoStartMinutes
	{
		get
		{
			return 0;
		}
	}

	public float autoChangePer
	{
		get
		{
			return 0f;
		}
	}

	public string limitDay
	{
		get
		{
			return null;
		}
	}

	public string nextLimitDay
	{
		get
		{
			return null;
		}
	}

	public bool isAppliedNext
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isValid
	{
		get
		{
			return false;
		}
	}

	public int sequence
	{
		get
		{
			return 0;
		}
	}

	public int warOrder
	{
		get
		{
			return 0;
		}
	}

	private int m_termsSum
	{
		get
		{
			return 0;
		}
	}

	public bool isSetDefenceSequnce
	{
		get
		{
			return false;
		}
	}

	public bool seqenceisAttackSequence
	{
		get
		{
			return false;
		}
	}

	public DateTime GetWarBegin(int warOrder)
	{
		return default(DateTime);
	}

	public DateTime GetWarEnd(int warOrder)
	{
		return default(DateTime);
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData data)
	{
	}
}
