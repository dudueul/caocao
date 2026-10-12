using System;

public interface ICompetitionStat
{
	ulong srl { get; }

	ulong userId { get; }

	int win { get; }

	int lose { get; }

	DateTime regDate { get; }

	DateTime updDate { get; }
}
