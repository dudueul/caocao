using System;
using LitJson;

public class GuildApplicant : ITransmittable
{
	public int srl;

	public ulong guildId;

	public ulong uid;

	public int state;

	public char memberType;

	public DateTime regDate;

	public DateTime updDate;

	public int rank;

	public string name;

	public int combatPower;

	public DateTime lastPlayedDate;

	public GuildApplicant()
	{
	}

	public GuildApplicant(JsonData jsonData)
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void DeserializeDetail(JsonData jsonData)
	{
	}
}
