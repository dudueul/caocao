using System;
using System.Collections.Generic;
using LitJson;

public class GuildMission : ITransmittable
{
	public class Contribution
	{
		public ulong userId;

		public int count;

		public DateTime updDate;

		public Contribution(ulong uid, int cnt, DateTime ud)
		{
		}
	}

	public int srl;

	public ulong guildId;

	public int missionId;

	public int count;

	public int slot;

	public string mondayOfWeek;

	public DateTime regDate;

	public DateTime updDate;

	public bool received;

	public List<Contribution> contributions;

	public static Comparison<Contribution> comparer;

	public GuildMission()
	{
	}

	public GuildMission(JsonData jsonData)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void AddUserContribution(JsonData userData)
	{
	}

	public void SortContribution()
	{
	}

	public bool IsComplete()
	{
		return false;
	}

	public int GetContributionCount(ulong uid)
	{
		return 0;
	}
}
