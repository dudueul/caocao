using GuildWar;

public class GuildWarEffectData_AttackCheck : GuildWarEffectData
{
	public string EffectName;

	public GuildWarEffectData_AttackCheck(ulong sourceUserId, ulong targetUserId, string effectName)
		: base(GuildWarEffectDataType.None, 0uL, 0uL)
	{
	}
}
