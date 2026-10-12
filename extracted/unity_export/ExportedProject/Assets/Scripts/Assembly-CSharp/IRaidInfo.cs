using System;

public interface IRaidInfo
{
	ulong Srl { get; }

	ulong GuildId { get; }

	ulong RaidId { get; }

	int RaidType { get; }

	int Difficulty { get; }

	int SummonCount { get; }

	int SummonRefreshCount { get; }

	int Rage { get; }

	DateTime LastSummonDate { get; }

	DateTime ExpireDate { get; }

	bool IsActive();

	bool Cleard();

	int GetMaxRes(EnumData.UnitResType resType);

	int GetRes(EnumData.UnitResType resType);

	int GetBattleStartRes(EnumData.UnitResType resType);

	int GetGoalValue();
}
