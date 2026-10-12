using System;
using LitJson;

public class BattleData : ITransmittable
{
	private UserInfo m_opponent;

	public int srl { get; private set; }

	public ulong atk { get; private set; }

	public ulong def { get; private set; }

	public int townID { get; private set; }

	public bool win { get; private set; }

	public int food { get; private set; }

	public int silver { get; private set; }

	public int honor { get; private set; }

	private string m_message { get; set; }

	public string message
	{
		get
		{
			return null;
		}
	}

	public bool gotReward { get; set; }

	public DateTime time { get; private set; }

	public BattleResultType battleResult { get; private set; }

	public bool didIWon
	{
		get
		{
			return false;
		}
	}

	public bool didIAttack
	{
		get
		{
			return false;
		}
	}

	public UserInfo opponent
	{
		get
		{
			return null;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
