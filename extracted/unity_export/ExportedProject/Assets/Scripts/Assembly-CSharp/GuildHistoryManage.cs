using System.Collections.Generic;
using LitJson;

public class GuildHistoryManage : GuildHistory
{
	public enum How
	{
		none = 0,
		leave = 1,
		kick = 2,
		join = 3,
		changeJob = 4,
		create = 5,
		levelup = 6,
		raidSummon = 7,
		raidSuccess = 8,
		raidFailed = 9,
		changeName = 10,
		guildWarResult = 11
	}

	public enum GuildWarHistoryResults
	{
		None = 0,
		AttackerWin = 1,
		AttackerLose = 2,
		DefenderWin = 3,
		DefenderLose = 4,
		DiscardTown = 5
	}

	private How how;

	public string time
	{
		get
		{
			return null;
		}
	}

	public override string contextString
	{
		get
		{
			return null;
		}
	}

	public override List<string> contextParams
	{
		get
		{
			return null;
		}
	}

	public bool ShowRaidRankingButton
	{
		get
		{
			return false;
		}
	}

	public bool ShowGuildWarHistoryButton
	{
		get
		{
			return false;
		}
	}

	public GuildHistoryManage(JsonData jsonData)
	{
	}
}
