using System;
using System.Collections.Generic;
using LitJson;

public class UserSubScriptions : ITransmittable
{
	private Dictionary<int, UserSubScription> m_dicSubScriptions;

	public Action updateCallback;

	public Dictionary<int, UserSubScription> dicSubScriptions
	{
		get
		{
			return null;
		}
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool ActiveSelf(EnumData.SubScriptionType type)
	{
		return false;
	}

	public bool ActiveSelf(int cashShopId)
	{
		return false;
	}

	public TimeSpan RemainTime(EnumData.SubScriptionType type)
	{
		return default(TimeSpan);
	}

	public TimeSpan RemainTime(int cashShopId)
	{
		return default(TimeSpan);
	}

	public int GetCashShopId(EnumData.SubScriptionType type)
	{
		return 0;
	}

	public float GetInstanceBonus(EnumData.EventType instanceType)
	{
		return 0f;
	}
}
