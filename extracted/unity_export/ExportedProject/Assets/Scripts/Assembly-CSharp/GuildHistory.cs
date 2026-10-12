using System;
using System.Collections.Generic;
using LitJson;

public class GuildHistory : ITransmittable
{
	public enum Type
	{
		manage = 0,
		mission = 1
	}

	public ulong _srl;

	public ulong _userId;

	public ulong _guildId;

	public string _type;

	public ulong _who;

	public ulong _what;

	public string _how;

	public string _param;

	public DateTime _regDate;

	public string dateString
	{
		get
		{
			return null;
		}
	}

	public virtual string contextString
	{
		get
		{
			return null;
		}
	}

	public virtual List<string> contextParams
	{
		get
		{
			return null;
		}
	}

	public GuildHistory()
	{
	}

	public GuildHistory(JsonData jsonData)
	{
	}

	public static GuildHistory Construct(JsonData jsonData)
	{
		return null;
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
