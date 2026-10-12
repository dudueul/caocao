using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserState : ITransmittable
{
	private ObscuredInt m_stateId;

	private List<int> m_townIds;

	public int stateId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public State stateData { get; private set; }

	public bool isConquered { get; private set; }

	public bool everConquered { get; private set; }

	public bool didLost { get; set; }

	public int conqueredCount
	{
		get
		{
			return 0;
		}
	}

	public int conqueredCastleCount
	{
		get
		{
			return 0;
		}
	}

	public int notConqueredCount
	{
		get
		{
			return 0;
		}
	}

	public int popularity
	{
		get
		{
			return 0;
		}
	}

	public int maxPopularity
	{
		get
		{
			return 0;
		}
	}

	public float popularityPercent
	{
		get
		{
			return 0f;
		}
	}

	private int popularityGrade
	{
		get
		{
			return 0;
		}
	}

	public string popularityState
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

	public bool AllInstancesCleared()
	{
		return false;
	}

	public int GetAnyConqueredTownId()
	{
		return 0;
	}

	public bool CanAutoClearState()
	{
		return false;
	}

	public List<UserInstance> GetAutoClearInstanceList()
	{
		return null;
	}
}
