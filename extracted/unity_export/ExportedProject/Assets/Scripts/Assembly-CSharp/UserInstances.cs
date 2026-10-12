using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using UnityEngine;

public class UserInstances : ITransmittable, iTransmitContainer
{
	private Dictionary<int, UserInstance> m_id2Instance;

	private List<UserInstance> _Instances;

	public Dictionary<int, UserInstance> id2Instance
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
	public List<UserInstance> instances
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserInstance get_Item(int id)
	{
		return null;
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public List<UserInstance> GetWorldmapNewUserInstances()
	{
		return null;
	}

	public List<UserInstance> GetWorldmapActiveUserInstances()
	{
		return null;
	}

	public List<UserInstance> GetWorldmapRemovedUserInstances()
	{
		return null;
	}

	public List<UserInstance> GetNewUserInstances()
	{
		return null;
	}

	public List<UserInstance> GetActiveUserInstances()
	{
		return null;
	}

	public List<UserInstance> GetRemovedUserInstances()
	{
		return null;
	}

	public List<UserInstance> GetSpecialUserInstances(bool ignoreRepeatable = true)
	{
		return null;
	}

	public List<UserInstance> GetLimitedUserInstances(bool ignorePackman = true)
	{
		return null;
	}

	public List<UserInstance> GetExpeditionUserInstances()
	{
		return null;
	}

	public int GetEnableExpeditionCount()
	{
		return 0;
	}

	public List<ObscuredInt> GetIDsOnWorldMap()
	{
		return null;
	}

	public List<ObscuredInt> GetIDsOnInstance()
	{
		return null;
	}

	public List<ObscuredInt> GetNewOrActiveIDs()
	{
		return null;
	}

	public List<ObscuredInt> GetNewInstanceIDs(int type, bool forcibly = false)
	{
		return null;
	}

	public List<int> GetActiveInstanceIDs()
	{
		return null;
	}

	public UserInstance GetInstanceOverriding(int townId)
	{
		return null;
	}

	private bool IsNewInstanceValid(int instID, List<ObscuredInt> coolTimeInsts = null)
	{
		return false;
	}

	private bool IsMatchingCoolTimeSaveFlag(Instance instance, List<ObscuredInt> coolTimeInsts)
	{
		return false;
	}

	[DebuggerHidden]
	public IEnumerator CheckInstances(List<ObscuredInt> newInstIDs, HttpRequest.ResponseCallback requestCallback, MonoBehaviour behaviour, bool forcibly = false)
	{
		return null;
	}

	public bool IsOnCoolTimeOnPedlarInstance()
	{
		return false;
	}

	private List<ObscuredInt> GetCoolTimeInstances()
	{
		return null;
	}

	private List<ObscuredInt> GetCandidatesInstances(List<ObscuredInt> cooltimedInsts, List<ObscuredInt> newInsts)
	{
		return null;
	}

	public UserInstance GetAvailableInstanceOfState(int stateId)
	{
		return null;
	}
}
