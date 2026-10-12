using System;
using System.Collections.Generic;
using LitJson;

public class GuildDonations : ITransmittable
{
	private Dictionary<ulong, GuildDonation> m_srl2Donation;

	private List<ulong> m_srls;

	private Dictionary<string, List<GuildDonation>> m_expr2Donations;

	private List<string> m_exprs;

	public Dictionary<string, List<GuildDonation>> expr2Donations
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public List<GuildDonation> get_Item(string expireDate)
	{
		return null;
	}

	public List<string> exprs
	{
		get
		{
			return null;
		}
	}

	public int Count
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void Clear()
	{
	}
}
