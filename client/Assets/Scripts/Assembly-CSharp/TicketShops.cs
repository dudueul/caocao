using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

public class TicketShops : PermanentBase
{
	public List<TicketShop> data;

	[NonSerialized]
	private Dictionary<ObscuredInt, TicketShop> m_ticketShops;

	[NonSerialized]
	private List<ObscuredInt> m_ids;

	public Dictionary<ObscuredInt, TicketShop> dic
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public TicketShop get_Item(ObscuredInt id)
	{
		return null;
	}

	public List<ObscuredInt> ids
	{
		get
		{
			return null;
		}
	}

	private void RefineData(_TicketShops src)
	{
	}

	public static void Load(ref TicketShops res)
	{
	}
}
