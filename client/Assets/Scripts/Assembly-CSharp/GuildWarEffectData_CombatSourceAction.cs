using GuildWar;

public class GuildWarEffectData_CombatSourceAction : GuildWarEffectData
{
	public string EffectName;

	public GuildWarEffectData_CombatSourceAction(ulong sourceUserId, ulong targetUserId, string effectName)
		: base(GuildWarEffectDataType.None, 0uL, 0uL)
	{
	}
}
