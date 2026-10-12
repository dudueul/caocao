using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserRetrieveData : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private List<RetrieveJsonData> m_goodsJsonData;

	private List<RetrieveJsonData> m_itemsJsonData;

	private List<RetrieveJsonData> m_runesJsonData;

	private Action deserializeCallback__BackingField;

	public List<RetrieveJsonData> goodsJsonData
	{
		get
		{
			return null;
		}
	}

	public List<RetrieveJsonData> itemsJsonData
	{
		get
		{
			return null;
		}
	}

	public List<RetrieveJsonData> runesJsonData
	{
		get
		{
			return null;
		}
	}

	public event Action deserializeCallback
	{
		add
		{
		}
		remove
		{
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
