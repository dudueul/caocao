using System;
using System.Collections.Generic;
using LitJson;

public class UserRaidAwakenReserves : ITransmittable, iTransmitContainer
{
	private Dictionary<ulong, UserRaidAwakenReserve> m_srl2Info;

	private List<ulong> m_srls;

	private List<UserRaidAwakenReserve> m_sortedList;

	public List<UserRaidAwakenReserve> SortedList
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool CheckCalibrateEnabled()
	{
		return false;
	}

	public int GetEmptyIndex()
	{
		return 0;
	}

	public bool CheckDuplicatedDayOfWeek(int dayofweek)
	{
		return false;
	}

	public UserRaidAwakenReserve GetReserveBySrl(ulong srl)
	{
		return null;
	}

	public UserRaidAwakenReserve GetValidReserve()
	{
		return null;
	}

	private void UpdateSortedList()
	{
	}
}
