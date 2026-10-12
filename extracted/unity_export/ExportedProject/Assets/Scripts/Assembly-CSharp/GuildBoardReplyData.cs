using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildBoardReplyData
{
	private ObscuredULong m_srl;

	private ObscuredULong m_guildBoardSrl;

	private ObscuredULong m_userId;

	private ObscuredString m_content;

	private DateTime m_updDate;

	private DateTime m_regDate;

	public bool isEmoticon;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong guildBoardSrl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong userId
	{
		get
		{
			return 0uL;
		}
	}

	public string content
	{
		get
		{
			return null;
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

	public GuildBoardReplyData(JsonData jsonData)
	{
	}
}
