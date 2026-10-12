using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserUnitPassiveInven : ITransmittable
{
	protected ObscuredULong m_srl;

	protected ObscuredULong m_user_id;

	protected ObscuredInt m_unit_id;

	protected ObscuredInt m_passive_list_id;

	protected ObscuredBool m_purchased;

	protected ObscuredInt m_purchase_price;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong uesr_id
	{
		get
		{
			return 0uL;
		}
	}

	public int unit_id
	{
		get
		{
			return 0;
		}
	}

	public int passive_list_id
	{
		get
		{
			return 0;
		}
	}

	public bool purchased
	{
		get
		{
			return false;
		}
	}

	public int purchase_price
	{
		get
		{
			return 0;
		}
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
