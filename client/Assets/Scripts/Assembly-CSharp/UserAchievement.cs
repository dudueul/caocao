using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserAchievement : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_uid;

	private ObscuredInt m_achievementId;

	private ObscuredInt m_value;

	private DateTime m_reward_date;

	private ObscuredInt m_goal;

	private ObscuredBool m_noticed;

	private Achievement m_achievementInfo;

	private ObscuredBool m_isValid;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public ulong uid
	{
		get
		{
			return 0uL;
		}
		set
		{
		}
	}

	public int achievementId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int value
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public DateTime reward_date
	{
		get
		{
			return default(DateTime);
		}
		set
		{
		}
	}

	public int goal
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool noticed
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public int requestedValue { get; set; }

	public bool isValid
	{
		get
		{
			return false;
		}
	}

	public Achievement achievement
	{
		get
		{
			return null;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool IsCompleted()
	{
		return false;
	}

	public bool IsCleared()
	{
		return false;
	}

	public bool IsFinished()
	{
		return false;
	}

	public bool IsUserStatusMission()
	{
		return false;
	}

	public bool IsVipStatusMission()
	{
		return false;
	}

	public string GetGPGSId()
	{
		return null;
	}

	private int GetFinalCountValueOfGoup(int id)
	{
		return 0;
	}

	private void SetNotice(bool isNewOne, int oldVal, int newVal)
	{
	}
}
