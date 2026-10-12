using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserDailyCount : ITransmittable
{
	public ObscuredULong userId { get; private set; }

	public ObscuredString yyyymmdd { get; private set; }

	public ObscuredInt loginCount { get; private set; }

	public ObscuredInt consequent { get; private set; }

	public ObscuredInt vipPointEarnBattleCount { get; private set; }

	public ObscuredInt vipPointEarnLoginCount { get; private set; }

	public ObscuredInt freeUndo { get; private set; }

	public ObscuredInt generalShopReset { get; private set; }

	public ObscuredInt vipShopReset { get; private set; }

	public ObscuredInt guildShopReset { get; private set; }

	public ObscuredInt secretShopReset { get; private set; }

	public ObscuredInt freeFastforward { get; private set; }

	public ObscuredInt vigorCharge { get; private set; }

	public ObscuredInt shamBattleReset { get; private set; }

	public ObscuredInt guildBattleReset { get; private set; }

	public ObscuredInt bossBattleReset { get; private set; }

	public ObscuredInt autoClearInstance { get; private set; }

	public ObscuredInt missionResetCount { get; private set; }

	public ObscuredInt freeEnhanceCount { get; private set; }

	public ObscuredInt freeChangePassiveCount { get; private set; }

	public DateTime GetyyyymmddAdd1Day
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
}
