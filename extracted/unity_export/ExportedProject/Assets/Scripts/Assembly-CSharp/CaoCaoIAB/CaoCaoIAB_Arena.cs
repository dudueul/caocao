using System.Collections.Generic;

namespace CaoCaoIAB
{
	public class CaoCaoIAB_Arena : ICaoCaoIAB
	{
		private enum ArenaErrorCode
		{
			UNDEFINED_ERROR = -200000,
			CANCEL_TRANSACTION = -200001,
			INVALID_REQUEST = 4000001,
			MISSING_PAREMETER = 4000002,
			INVALID_AUTHENTICATION_HEADER = 4000003,
			IMPROPER_TRANSACTION_STATUS = 4000005,
			INVALID_CURRENCY = 4000007,
			INVALID_AUTHORIZATION_TOKEN = 4010001,
			UNSUPPORTED_HTTP_METHOD = 4030001,
			SANDBOX_ONLY = 4030002,
			RESOURCE_NOT_FOUND = 4040001,
			INTERNAL_SERVER_ERROR = 5000001
		}

		private const string m_marketName = "NXA";

		private const string m_marketDescription = "Nexon Arena";

		private string internalFormat;

		public string ArenaToken
		{
			get
			{
				return null;
			}
		}

		public EnumData.NXAMembershipNo MembershipNo
		{
			get
			{
				return (EnumData.NXAMembershipNo)0;
			}
		}

		public EnumData.NXACurrencyType CurrencyType
		{
			get
			{
				return (EnumData.NXACurrencyType)0;
			}
		}

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

		public bool CheckArguments()
		{
			return false;
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

		private Dictionary<string, string> QueryUrl(string url)
		{
			return null;
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
