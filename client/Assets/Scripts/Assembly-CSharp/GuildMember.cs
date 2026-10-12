using System;
using System.Collections.Generic;
using LitJson;

public class GuildMember : ITransmittable
{
	public class SiegeWarUserInfo
	{
		public int defenceWin;

		public int defenceLose;

		public int annihilationRank;

		public int annihilationRankTotal;

		public void Deserialize(JsonData jsonData)
		{
		}
	}

	public class CompetitionUserInfo
	{
		public int tier_grade;

		public int rank;

		public int point;

		public void Deserialize(JsonData jsonData)
		{
		}
	}

	public ulong uid;

	public int exp;

	public int rank;

	public string name;

	public ulong guildId;

	public char guildJob;

	public int guildPoint;

	public int oldGuildPoint;

	public DateTime leaveDate;

	public DateTime regDate;

	public DateTime updDate;

	public DateTime joinDate;

	public int totalContribution;

	public int totalActivityPoint;

	public DateTime lastPlayedDate;

	public int combatPower;

	private int daily_raid_rage;

	private DateTime rage_added_date;

	private int weekly_raid_rage;

	private string weekly_raid_rage_mow;

	private int before_weekly_raid_rage;

	private string before_weekly_raid_rage_mow;

	private int weekly_contribution;

	private string weekly_contribution_mow;

	private int before_weekly_contribution;

	private string before_weekly_contribution_mow;

	private int weekly_competition_point;

	private string weekly_competition_point_wow;

	private int weekly_activity_point;

	private string weekly_activity_point_mow;

	private int before_weekly_activity_point;

	private string before_weekly_activity_point_mow;

	private string m_mowTimestamp;

	private string m_lastDayOfWeek;

	private string m_thisDayOfWeek;

	private string m_thisWednesdayOfWeek;

	public Dictionary<EnumData.GoodsType, int> donationInfo;

	public string lastDayOfWeek
	{
		get
		{
			return null;
		}
	}

	public string thisDayOfWeek
	{
		get
		{
			return null;
		}
	}

	public string thisWednesdayOfWeek
	{
		get
		{
			return null;
		}
	}

	public int weeklyCompetitionPoint
	{
		get
		{
			return 0;
		}
	}

	public int weeklyContribution
	{
		get
		{
			return 0;
		}
	}

	public int beforeWeeklyContribution
	{
		get
		{
			return 0;
		}
	}

	public int dailyRaidRage
	{
		get
		{
			return 0;
		}
	}

	public int weeklyRaidRage
	{
		get
		{
			return 0;
		}
	}

	public int beforeWeeklyRaidRage
	{
		get
		{
			return 0;
		}
	}

	public int weeklyActivityPoint
	{
		get
		{
			return 0;
		}
	}

	public int beforeWeeklyActivityPoint
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	public Action<GuildMember> deserializeDefenceDeckInfo { get; set; }

	public bool isIndependent
	{
		get
		{
			return false;
		}
	}

	public bool isApplicant
	{
		get
		{
			return false;
		}
	}

	public bool isGuildMember
	{
		get
		{
			return false;
		}
	}

	public bool isCommander
	{
		get
		{
			return false;
		}
	}

	public bool isNpc
	{
		get
		{
			return false;
		}
	}

	public GuildSiegeWarSupportDeck supportDeck { get; protected set; }

	public GuildSiegeWarAttackDeck siegeWarAttackDeck { get; protected set; }

	public GuildSiegeWarDefenceDecks siegeWarDefenceDecks { get; protected set; }

	public GuildSiegeWarUserStat stat { get; protected set; }

	public GuildSiegeWarWarStat siegeWarWarStat { get; protected set; }

	public SiegeWarUserInfo siegeWarUserInfo { get; protected set; }

	public CompetitionUserInfo competitionUserInfo { get; protected set; }

	public bool didParticipateCompetition
	{
		get
		{
			return false;
		}
	}

	public GuildSiegeWarRankInfo atkInfo { get; protected set; }

	public GuildSiegeWarRankInfo defInfo { get; protected set; }

	public bool hasDefenceDeckAllSlot
	{
		get
		{
			return false;
		}
	}

	public bool hasValidAttackEntry
	{
		get
		{
			return false;
		}
	}

	public GuildMember()
	{
	}

	public GuildMember(ulong uid, ulong guildId)
	{
	}

	public GuildMember(JsonData jsonData)
	{
	}

	public GuildMember(UserInfo userInfo, GuildApplicant applicant)
	{
	}

	public GuildMember(GuildApplicant applicant)
	{
	}

	private void makeDayOfWeek()
	{
	}

	public EnumData.GuildJopType GetGuildJob()
	{
		return (EnumData.GuildJopType)0;
	}

	public void SetUserInfo(string name, int rank)
	{
	}

	private void Init()
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void DeserializeDetail(JsonData jsonData)
	{
	}

	public void DeserializeSiegeWarDefenceEtcInfo(JsonData jsonData)
	{
	}

	public void DeserializeOneSiegeWarDefenceDeck(JsonData jsonData)
	{
	}

	public void DeserializeOneSiegeWarWarStat(JsonData jsonData)
	{
	}

	public void DeserializeOneSiegeWarUserStat(JsonData jsonData)
	{
	}

	public void DeserializeDonateInfo(JsonData jsonData)
	{
	}

	public void DeserializeCompetiitonUserInfo(JsonData jsonData)
	{
	}

	public int GetPresentLimit(GuildPresent presentData)
	{
		return 0;
	}

	public void SetSupportDeck(GuildSiegeWarSupportDeck deck)
	{
	}

	public void SetSiegeWarAttackDeck(GuildSiegeWarAttackDeck deck)
	{
	}
}
