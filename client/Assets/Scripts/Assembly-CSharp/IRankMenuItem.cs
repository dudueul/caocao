using System;

public interface IRankMenuItem
{
	int DefaultRankId { get; }

	EnumData.rankGroup rankGroup { get; }

	EnumData.rankType rankType { get; }

	string rankName { get; }

	void OnClick();

	void SetOnClickHandler(Action<int> onClick);
}
