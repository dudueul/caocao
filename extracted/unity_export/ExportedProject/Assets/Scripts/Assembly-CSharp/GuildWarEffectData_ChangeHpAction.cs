using GuildWar;

public class GuildWarEffectData_ChangeHpAction : GuildWarEffectData
{
	public int StartHp;

	public int EndHP;

	public GuildWarEffectData_ChangeHpAction(ulong sourceUserId, ulong targetUserId, int startHp, int endHP)
		: base(GuildWarEffectDataType.None, 0uL, 0uL)
	{
	}
}
