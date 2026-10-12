using System.Collections.Generic;

namespace CaoCaoIAB
{
	public class CaoCaoIAB_Steam : ICaoCaoIAB
	{
		public string GetMarketName()
		{
			return null;
		}

		public string GetMarketDescription()
		{
			return null;
		}

		public void Init()
		{
		}

		public void QueryInventory()
		{
		}

		public void QueryInventory(string[] skus)
		{
		}

		public void ConsumePurchase(Purchase purchase)
		{
		}

		public void ConsumePurchase(List<Purchase> purchases)
		{
		}

		public void ConsumeSubscription(Purchase purchase)
		{
		}

		public void PurchaseProduct(string itemId, string payload = "")
		{
		}

		public void PurchaseSubscription(string itemId, string payload = "")
		{
		}

		public string GetErrorMessage(string errorMsg)
		{
			return null;
		}
	}
}
