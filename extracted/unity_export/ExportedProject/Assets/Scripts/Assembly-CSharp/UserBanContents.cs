using System.Collections.Generic;
using LitJson;

public class UserBanContents : ITransmittable, iTransmitContainer
{
	private Dictionary<EnumData.BanContentsType, UserBanContent> m_dicBanContents;

	// C# has no syntax for parameterized property 'Item'.
	public UserBanContent get_Item(EnumData.BanContentsType type)
	{
		return null;
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public bool IsBan(EnumData.BanContentsType type)
	{
		return false;
	}

	public bool IsUnlimitedBan(EnumData.BanContentsType type)
	{
		return false;
	}
}
