using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserBanContent : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_contentsType;

	private ObscuredString m_comment;

	private ObscuredBool m_status;

	private DateTime m_ban_date;

	private DateTime m_updDate;

	private DateTime m_regDate;

	public ulong srl
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

	public int contentsType
	{
		get
		{
			return 0;
		}
	}

	public string comment
	{
		get
		{
			return null;
		}
	}

	public bool status
	{
		get
		{
			return false;
		}
	}

	public DateTime banDate
	{
		get
		{
			return default(DateTime);
		}
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
