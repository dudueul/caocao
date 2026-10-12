using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using CaoCaoIAB;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public static class CaoCaoIABManager
{
	private struct PurchaseInfo
	{
		public ObscuredULong userID;

		public ObscuredString itemID;

		public void Init()
		{
		}

		public void Init(ulong userID, string itemID)
		{
		}

		public bool IsPurchasing()
		{
			return false;
		}
	}

	private static PurchaseInfo m_purchaseInfo;

	private static List<RewardInfo> m_rewards;

	public static string initErrMsg;

	private static ICaoCaoIAB _plugin;

	private static Inventory inventory;

	private static Purchase purchased;

	private static List<string> ms_productIds;

	private static List<string> ms_nonConsumables;

	private static List<string> ms_autoRenewalSubscriptions;

	public static List<LinkedList<string>> productRelation;

	public static Action onInitSucceedForGF;

	public static Action onInitFailedForGF;

	private static Dictionary<string, string> m_argumentDic;

	public static bool m_init { get; private set; }

	public static bool m_processing { get; private set; }

	public static CaoCaoIABEventManager m_eventManager { get; private set; }

	public static string market
	{
		get
		{
			return null;
		}
	}

	public static string marketDescription
	{
		get
		{
			return null;
		}
	}

	public static string[] productIds
	{
		get
		{
			return null;
		}
	}

	public static bool IsNonConsumables(string productId)
	{
		return false;
	}

	public static bool IsAutoRenewalSubscription(string productId)
	{
		return false;
	}

	public static bool HasProduct(string productId)
	{
		return false;
	}

	public static List<Purchase> GetPurchaseMap()
	{
		return null;
	}

	public static List<SkuDetails> GetAllSkuDetails()
	{
		return null;
	}

	public static SkuDetails GetSkuDetails(string pid)
	{
		return null;
	}

	private static void BuildPackageProductRelation(JsonData relations)
	{
	}

	public static void Init()
	{
	}

	public static void AttachEventCallback()
	{
	}

	public static void DetachEventCallback()
	{
	}

	private static void OnGetProductIds(JsonData response)
	{
	}

	public static void QueryInventory()
	{
	}

	public static void QueryInventory(string[] skus)
	{
	}

	public static void ConsumePurchase(Purchase purchase)
	{
	}

	public static void ConsumePurchase(List<Purchase> purchases)
	{
	}

	public static void StartPurchaseItem(string itemId, string itemType)
	{
	}

	private static void PurchaseItem(string itemId, string itemType, string payload)
	{
	}

	public static void PurchaseProduct(string itemId)
	{
	}

	public static void PurchaseSubscription(string itemId)
	{
	}

	private static void OnBillingInitSucceed()
	{
	}

	private static void OnBillingInitFailed(string err)
	{
	}

	private static void OnQueryInventorySucceed(Inventory inv)
	{
	}

	private static void OnQueryInventoryFailed(string err)
	{
	}

	private static void OnPurchaseSucceed(Purchase purchase)
	{
	}

	private static void OnPurchaseFailed(string errMsg)
	{
	}

	private static void OnConsumePurchaseSucceed(Purchase purchase)
	{
	}

	private static void OnConsumePurchaseFailed(string err)
	{
	}

	private static void OnStartPurchase(JsonData response)
	{
	}

	private static void OnPurchaseProduct(JsonData response)
	{
	}

	private static void OnConsumePurchase(JsonData response)
	{
	}

	private static void OnCompletePurchase()
	{
	}

	private static void ShowCompletePopup()
	{
	}

	private static void ShowPurchaseErrorPopup()
	{
	}

	[DebuggerHidden]
	private static IEnumerator CheckQueriedPurchase(List<Purchase> purchases)
	{
		return null;
	}

	public static void SetArgument(string key, string val)
	{
	}

	public static string GetArgument(string key)
	{
		return null;
	}

	public static bool ConstainsArgument(string key)
	{
		return false;
	}
}
