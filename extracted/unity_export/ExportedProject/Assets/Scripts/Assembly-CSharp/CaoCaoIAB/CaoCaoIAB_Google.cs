using System.Collections.Generic;
using LitJson;
using UnityEngine;

namespace CaoCaoIAB
{
	public class CaoCaoIAB_Google : ICaoCaoIAB
	{
		private static AndroidJavaObject iabActivity;

		public static readonly string m_marketName;

		public static readonly string m_marketDescription;

		private static string m_publicKey;

		private string internalFormat;

		public string GetMarketName()
		{
			return null;
		}

		public string GetMarketDescription()
		{
			return null;
		}

		private void CallJavaMethod(string methodName, params object[] args)
		{
		}

		public void Init()
		{
		}

		private void OnGetPublicKey(JsonData response)
		{
		}

		public void InitImpl()
		{
		}

		public void QueryInventory()
		{
		}

		public void QueryInventory(string[] skus)
		{
		}

		private void QueryInventory(string[] inAppSkus, string[] subscriptionSkus)
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

		public void PurchaseProduct(string itemId, string payload)
		{
		}

		public void PurchaseSubscription(string itemId, string payload)
		{
		}

		public string GetErrorMessage(string errorMsg)
		{
			return null;
		}
	}
}
