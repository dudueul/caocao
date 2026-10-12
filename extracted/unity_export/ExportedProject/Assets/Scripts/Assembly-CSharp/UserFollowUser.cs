using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserFollowUser : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredULong m_followUserId;

	private ObscuredInt m_followUserFace;

	private ObscuredInt m_followUserFaceUnitSkin;

	private ObscuredString m_followUserName;

	private ObscuredBool m_deleted;

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

	public ulong followUserId
	{
		get
		{
			return 0uL;
		}
	}

	public int followUserFace
	{
		get
		{
			return 0;
		}
	}

	public int followUserFaceUnitSkin
	{
		get
		{
			return 0;
		}
	}

	public string followUserName
	{
		get
		{
			return null;
		}
	}

	public bool deleted
	{
		get
		{
			return false;
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

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void DeserializeDetail(JsonData jsonData)
	{
	}
}
