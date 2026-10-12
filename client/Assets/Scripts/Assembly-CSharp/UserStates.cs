using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserStates : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserState> m_id2State;

	private List<int> m_ids;

	public ObscuredInt lastConqueredState;

	public Action m_deserializeCallback;

	public Dictionary<int, UserState> id2State
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public List<int> ids
	{
		get
		{
			return null;
		}
	}

	[DoNotJsonfy]
	public List<UserState> states
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserState get_Item(int id)
	{
		return null;
	}

	public bool isAllStatesConquered
	{
		get
		{
			return false;
		}
	}

	public bool didLostState
	{
		get
		{
			return false;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public UserState GetStateWithTownID(int townID)
	{
		return null;
	}

	public int GetTotalPopularity()
	{
		return 0;
	}

	public int GetTotalPopularityDenominator()
	{
		return 0;
	}

	public float GetNationPopularityPercent()
	{
		return 0f;
	}

	public int GetStateIdForNotReceivedConquerReward()
	{
		return 0;
	}
}
