using System;
using System.Collections.Generic;
using LitJson;

public class OpponentUserData : UserData
{
	public static OpponentUserData instance
	{
		get
		{
			return null;
		}
	}

	[AppSealingSecurityMethod]
	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	private void CheckMatchPreset(int townId)
	{
	}

	private void OverrideMatchPreset(MatchPreset match)
	{
	}

	public void SerializeGuildSiegeWarResult(JsonWriter writer)
	{
	}

	private void SerializeUnitDataList(List<UnitData> unitDatas, string name, JsonWriter writer, Func<UnitData, bool> targetFilter)
	{
	}
}
