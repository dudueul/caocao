using System;
using LitJson;

public class BattleResult
{
	public int townId { get; private set; }

	public int stateId { get; set; }

	public long invaderId { get; private set; }

	public bool fallen { get; private set; }

	public DateTime time { get; set; }

	public BattleResult(JsonData jsonData)
	{
	}
}
