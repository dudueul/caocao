using System;

public interface IPvpStat
{
	ulong srl { get; }

	ulong userId { get; }

	int attackWin { get; }

	int attackLose { get; }

	int defenseWin { get; }

	int defenseLose { get; }

	DateTime regDate { get; }

	DateTime updDate { get; }
}
