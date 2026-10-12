using System.Runtime.InteropServices;

[StructLayout((LayoutKind)0, Size = 8)]
public struct GoodsType2TotalPrice
{
	public EnumData.GoodsType goodsType;

	public int totalPrice;

	public GoodsType2TotalPrice(EnumData.GoodsType type, int total)
	{
		goodsType = EnumData.GoodsType.None;
		totalPrice = 0;
	}
}
