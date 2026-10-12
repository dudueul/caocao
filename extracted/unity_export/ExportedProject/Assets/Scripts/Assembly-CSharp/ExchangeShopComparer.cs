using System.Collections.Generic;

public class ExchangeShopComparer : IEqualityComparer<ExchangeShop>
{
	public bool Equals(ExchangeShop x, ExchangeShop y)
	{
		return false;
	}

	public int GetHashCode(ExchangeShop obj)
	{
		return 0;
	}
}
