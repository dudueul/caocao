using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserTreasureBox : ITransmittable
{
	public ObscuredInt m_boxId { get; private set; }

	public ObscuredInt m_mileage { get; private set; }

	public DateTime m_unlockDateTime { get; private set; }

	public int boxId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int mileage
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public DateTime unlockDateTime
	{
		get
		{
			return default(DateTime);
		}
		set
		{
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool IsAvailable()
	{
		return false;
	}
}
