using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserBattleDeckFormation : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredInt m_userId;

	private ObscuredInt m_fieldId;

	private ObscuredString m_info;

	public List<KeyValuePair<int, int>> areaId2PositionList;

	public Dictionary<int, List<int>> areaId2PositionDic;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public int userId
	{
		get
		{
			return 0;
		}
	}

	public int fieldId
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

	private void ParseInfo()
	{
	}
}
