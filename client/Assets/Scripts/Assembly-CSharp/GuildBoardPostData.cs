using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildBoardPostData
{
	public enum Type
	{
		All = 0,
		Chat = 1,
		Info = 2,
		GuildWar = 3
	}

	private ObscuredULong m_srl;

	private ObscuredULong m_guildId;

	private ObscuredULong m_userId;

	private ObscuredInt m_type;

	private ObscuredString m_title;

	private ObscuredString m_content;

	private ObscuredInt m_replyCount;

	private ObscuredInt m_recommendCount;

	private DateTime m_updDate;

	private DateTime m_regDate;

	public bool isNotice;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong guildId
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public ulong userId
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public int type
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string title
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string content
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int replyCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int recommendCount
	{
		get
		{
			return 0;
		}
		set
		{
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

	public GuildBoardPostData(JsonData jsonData, bool isNotice)
	{
	}

	public GuildBoardPostData(ulong guildId, ulong userId, Type type)
	{
	}

	public void Deserialize(JsonData jsonData)
	{
	}
}
