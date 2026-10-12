using System;
using System.Collections.Generic;
using LitJson;

public class GuildWarUserBaseDatas : ITransmittable
{
	private Dictionary<ulong, GuildWarUserBaseData> m_datas;

	// C# has no syntax for parameterized property 'Item'.
	public GuildWarUserBaseData get_Item(ulong userId)
	{
		return null;
	}

	public Action<HashSet<GuildWarUserBaseData>> deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void Clear()
	{
	}
}
