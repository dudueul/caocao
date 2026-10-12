using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class BingoEventUser : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredULong m_eventIngameId;

	private ObscuredInt m_bingoBoard;

	private ObscuredInt m_bingoPoint;

	private ObscuredInt m_bingoLine;

	private DateTime m_updDate;

	private DateTime m_regDate;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong userId
	{
		get
		{
			return 0uL;
		}
	}

	public ulong eventIngameId
	{
		get
		{
			return 0uL;
		}
	}

	public int bingoBoard
	{
		get
		{
			return 0;
		}
	}

	public int bingoPoint
	{
		get
		{
			return 0;
		}
	}

	public int bingoLine
	{
		get
		{
			return 0;
		}
	}

	public DateTime updDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public DateTime regDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public bool checkOpen(int index)
	{
		return false;
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
