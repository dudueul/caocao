using GuildWar;

public class GuildWarEffectData_CombatTargetAction : GuildWarEffectData
{
	public string EffectName;

	public GuildWarEffectData_CombatTargetAction(ulong sourceUserId, ulong targetUserId, string effectName)
		: base(GuildWarEffectDataType.None, 0uL, 0uL)
	{
	}
}
