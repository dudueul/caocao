using System.Runtime.InteropServices;
using LitJson;

[StructLayout((LayoutKind)0, Size = 20)]
public struct TimeBoxDecide
{
	public EnumData.GoodsType goodsType;

	public int goodsValue;

	public int itemId;

	public int itemAmount;

	public int cardTier;

	public bool IsGoodsReward()
	{
		return false;
	}

	public static TimeBoxDecide GetTimeBoxDecideFromJson(JsonData decide)
	{
		return default(TimeBoxDecide);
	}

	public void Serialize(JsonWriter writer)
	{
	}
}
