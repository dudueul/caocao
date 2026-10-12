using System.Collections.Generic;

namespace CaoCaoIAB
{
	public interface ICaoCaoIAB
	{
		string GetMarketName();

		string GetMarketDescription();

		void Init();

		void QueryInventory();

		void QueryInventory(string[] skus);

		void ConsumePurchase(Purchase purchase);

		void ConsumePurchase(List<Purchase> purchases);

		void ConsumeSubscription(Purchase purchase);

		void PurchaseProduct(string itemId, string payload = "");

		void PurchaseSubscription(string itemId, string payload = "");

		string GetErrorMessage(string errorMsg);
	}
}
