using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using UnityEngine;

[Serializable]
public class UserInstance : ITransmittable
{
	private ObscuredInt m_instId;

	private ObscuredInt m_posX;

	private ObscuredInt m_posY;

	private ObscuredBool m_active;

	private ObscuredBool m_isNew;

	private DateTime m_startDate;

	private DateTime m_endDate;

	public int instId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	[DoNotJsonfy]
	public Instance instData { get; private set; }

	public bool needUpdate { get; private set; }

	public bool active
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isNew
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	[DoNotJsonfy]
	public Vector3 position
	{
		get
		{
			return default(Vector3);
		}
	}

	public DateTime openDate { get; private set; }

	public DateTime closedDate { get; private set; }

	[DoNotJsonfy]
	public TimeSpan remainedTime
	{
		get
		{
			return default(TimeSpan);
		}
	}

	[DoNotJsonfy]
	public string remainedTimeString
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public TimeSpan remainedCoolTime
	{
		get
		{
			return default(TimeSpan);
		}
	}

	public bool isExpired
	{
		get
		{
			return false;
		}
	}

	public bool isOnCoolTime
	{
		get
		{
			return false;
		}
	}

	public int dayCnt { get; private set; }

	public DateTime lastPlay { get; private set; }

	public int totalCnt { get; private set; }

	public int tryCnt { get; private set; }

	public int stamp { get; private set; }

	public bool hasTimeLimit { get; private set; }

	public DateTime startDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime endDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public bool isDateValid
	{
		get
		{
			return false;
		}
	}

	public string remainEndDateString
	{
		get
		{
			return null;
		}
	}

	public GameObject instanceObj { get; private set; }

	public GameObject bossEffect { get; private set; }

	public Action deserializeCallback { get; set; }

	public UserInstance()
	{
	}

	public UserInstance(int instId)
	{
	}

	public override string ToString()
	{
		return null;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool TrySetPosition()
	{
		return false;
	}

	private bool IsValidPositionForTown(int townID)
	{
		return false;
	}

	private bool IsValidPositionForState(int stateID)
	{
		return false;
	}

	public void Allocate(bool isNewInst = false)
	{
	}

	public void HideOverridenTown()
	{
	}

	public void Deallocate()
	{
	}

	public void AddBossAppearEffect()
	{
	}

	public GameObject AddBossRetreatEffect()
	{
		return null;
	}

	public void ClickInstance()
	{
	}

	public void EnterInstance()
	{
	}

	public void EnterRenewalInstance()
	{
	}

	private void ShowBattleDeck()
	{
	}

	private bool FindPositionOnTown(int townID)
	{
		return false;
	}

	private bool FindPositionOnState(int stateID)
	{
		return false;
	}

	private bool IsValidPosition(Vector2 pos)
	{
		return false;
	}

	private void SetInstancePosition()
	{
	}

	private void OnSetPosition(JsonData response)
	{
	}

	public bool NeedToUpdateCoolTimeFlag()
	{
		return false;
	}

	public string GetAutoClearState()
	{
		return null;
	}

	public bool IsAbleAutoClear()
	{
		return false;
	}

	public bool IsCleared()
	{
		return false;
	}

	public bool IsAbleExpedition()
	{
		return false;
	}
}
