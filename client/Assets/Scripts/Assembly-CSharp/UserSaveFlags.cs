using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserSaveFlags : ITransmittable, iTransmitContainer
{
	[StructLayout((LayoutKind)0, Size = 8)]
	private struct OrderedIDSet
	{
		public int seq;

		public int id;
	}

	private Dictionary<int, UserSaveFlag> m_id2SaveFlags;

	private List<int> m_ids;

	public Dictionary<int, UserSaveFlag> id2SaveFlags
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public UserSaveFlag get_Item(int id)
	{
		return null;
	}

	[DoNotJsonfy]
	public List<int> ids
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public Action registerCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool IsUsedSaveFlag(int scenarioId)
	{
		return false;
	}

	public int GetAmbition(int scenarioId, bool include = true, int storybookId = 6100002, int scenarioFlagId = 0)
	{
		return 0;
	}

	public int GetCurrentAmbition(int storybookId = 6100002, int scenarioFlagId = 0)
	{
		return 0;
	}

	private List<UserSaveFlag> GetSaveFlagsUntilScenario(int scenarioId, bool include = true, int storybookId = 6100002, int scenarioFlagId = 0)
	{
		return null;
	}

	private int CalculateAmbitions(List<UserSaveFlag> saveFlags, int scenarioFlagId = 0)
	{
		return 0;
	}

	public int GetAmbientBySaveFlag(int saveFlagId, bool include = true)
	{
		return 0;
	}

	public void RegisterSaveFlag(int flagID, int saveValue)
	{
	}

	public void UnregisterSaveFlag(int flagID)
	{
	}

	public int GetSaveFlagValue(int flagID)
	{
		return 0;
	}

	public bool CheckNoneZeroValue(List<ObscuredInt> flags)
	{
		return false;
	}

	public void RegisterClientSaveFlag(int flagID, int saveValue)
	{
	}

	public void SaveLocalSaveFlags()
	{
	}

	private bool DeserializeLocalSaveFlags()
	{
		return false;
	}
}
