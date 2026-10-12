using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserInteractive : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_storybookId;

	private ObscuredInt m_modeLevel;

	private Dictionary<int, InteractiveSaveflag> m_saveFlags;

	private ObscuredInt m_curScenarioId;

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

	public int storybookId
	{
		get
		{
			return 0;
		}
	}

	public int modeLevel
	{
		get
		{
			return 0;
		}
	}

	public Dictionary<int, InteractiveSaveflag> saveFlags
	{
		get
		{
			return null;
		}
	}

	public int curScenarioId
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
