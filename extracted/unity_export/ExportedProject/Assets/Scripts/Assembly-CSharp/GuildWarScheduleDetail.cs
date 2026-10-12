using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildWarScheduleDetail : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredInt m_seasonKey;

	private ObscuredInt m_seasonDetailKey;

	private ObscuredInt m_p_battleworld_id;

	private DateTime m_adjacent_town_attend_start;

	private DateTime m_adjacent_town_attend_end;

	private DateTime m_season_detail_start;

	private DateTime m_season_detail_end;

	private DateTime m_normal_attend_start;

	private DateTime m_normal_attend_end;

	private DateTime m_first_come_attend_start;

	private DateTime m_first_come_attend_end;

	private DateTime m_combat_start;

	private DateTime m_combat_end;

	private int m_reward_grade;

	private DateTime m_updDate;

	private DateTime m_regDate;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public int seasonKey
	{
		get
		{
			return 0;
		}
	}

	public int seasonDetailKey
	{
		get
		{
			return 0;
		}
	}

	public int pBattleWorldId
	{
		get
		{
			return 0;
		}
	}

	public DateTime adjacentTownAttendStart
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime adjacentTownAttendEnd
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime seasonDetailStart
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime seasonDetailEnd
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime normalAttendStart
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime normalAttendEnd
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime firstComeAttendStart
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime firstComeAttendEnd
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime combatStart
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime combatEnd
	{
		get
		{
			return default(DateTime);
		}
	}

	public int rewardGrade
	{
		get
		{
			return 0;
		}
	}

	public DateTime updDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime regDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public Action deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
