using System;
using System.Collections.Generic;
using LitJson;

public class TreasurePurchasePopup
{
	public enum PopupMode
	{
		Free = 0,
		TokenOrGoods = 1,
		Goods = 2,
		Token = 3,
		None = 4
	}

	public class TreasureBoxResult
	{
		public ulong srl { get; private set; }

		public int id { get; private set; }

		public int count { get; private set; }

		public EnumData.TreasureBoxItemType type { get; private set; }

		public int replaceItemId { get; private set; }

		public int replaceItemCount { get; private set; }

		public int cardTier { get; private set; }

		public TreasureBoxResult(JsonData jsonData)
		{
		}

		public void Deserialize(JsonData jsonData)
		{
		}

		public static List<TreasureBoxResult> DeserializeList(JsonData jsonData)
		{
			return null;
		}

		public RewardInfo ParseToRewardInfo()
		{
			return null;
		}
	}

	public abstract class PurchaseNotiPopupBase<PanelPopupFormat> where PanelPopupFormat : CaocaoUIModalPopup
	{
		protected PanelPopupFormat m_popup;

		protected TreasureBoxItemInfo m_boxInfo;

		protected int m_costIdx;

		protected BoxShop boxPd
		{
			get
			{
				return null;
			}
		}

		protected EnumData.GoodsType goodsCostType
		{
			get
			{
				return EnumData.GoodsType.None;
			}
		}

		protected int cost
		{
			get
			{
				return 0;
			}
		}

		protected int needItemId
		{
			get
			{
				return 0;
			}
		}

		protected int needItemCount
		{
			get
			{
				return 0;
			}
		}

		protected void ShowYesNoPopup(string title, string label1, string label2, Func<PanelPopupBase.PopupState> onYes, Func<PanelPopupBase.PopupState> onCancel)
		{
		}

		protected void ShowGoodsConfirmPopup(Func<PanelPopupBase.PopupState> onYes, Func<PanelPopupBase.PopupState> onCancel)
		{
		}

		public void ShowPanel()
		{
		}
	}

	public class FreeBoxNotiPopup : PurchaseNotiPopupBase<PanelPopup2Buttons>
	{
		public FreeBoxNotiPopup(TreasureBoxItemInfo boxInfo)
		{
		}

		protected void EnablePurchaseButton(bool enable)
		{
		}

		private void CreateFreePopup()
		{
		}

		protected void OnPressedFree(Action<TreasureBoxItemInfo, string, int, Action> requestBuyingFunc)
		{
		}
	}

	public class GoodsBoxNotiPopup : PurchaseNotiPopupBase<PanelPopup2Buttons>
	{
		public GoodsBoxNotiPopup(TreasureBoxItemInfo boxInfo, int costIdx)
		{
		}

		protected void EnablePurchaseButton(bool enable)
		{
		}

		private void CreateNormalOrPackagePopup()
		{
		}

		private void CreatePackagePopup()
		{
		}

		private void CreateSliderPopup()
		{
		}

		private void CreateFreePopup()
		{
		}

		protected void OnPressedStaticGoods(Action<TreasureBoxItemInfo, string, int, Action> requestBuyingFunc)
		{
		}

		protected void OnPressedSliderGoods()
		{
		}

		protected void OnPressedFree(Action<TreasureBoxItemInfo, string, int, Action> requestBuyingFunc)
		{
		}
	}

	public class TokenOrGoodsNotiPopup : PurchaseNotiPopupBase<PanelPopup3Buttons>
	{
		public TokenOrGoodsNotiPopup(TreasureBoxItemInfo boxInfo, int costIdx)
		{
		}

		protected void EnablePurchaseButton(bool enable)
		{
		}

		public void CreateSliderPopup()
		{
		}

		public void CreateNormalOrPackagePopup()
		{
		}

		public void CreatePackagePopup()
		{
		}

		protected void OnPressedStaticGoods(Action<TreasureBoxItemInfo, string, int, Action> requestBuyingFunc)
		{
		}

		protected void OnPressedStaticToken(Action<TreasureBoxItemInfo, string, int, Action> requestBuyingFunc)
		{
		}

		protected void OnPressedSliderGoods()
		{
		}

		protected void OnPressedSliderToken()
		{
		}
	}

	public class TokenNotiPopup : PurchaseNotiPopupBase<PanelPopup2Buttons>
	{
		public TokenNotiPopup(TreasureBoxItemInfo boxInfo, int costIdx)
		{
		}

		protected void EnablePurchaseButton(bool enable)
		{
		}

		private void CreateNormalOrPackagePopup()
		{
		}

		protected void OnPressedStaticGoods(Action<TreasureBoxItemInfo, string, int, Action> requestBuyingFunc)
		{
		}

		protected void OnPressedStaticToken(Action<TreasureBoxItemInfo, string, int, Action> requestBuyingFunc)
		{
		}
	}

	private static Dictionary<int, HashSet<EnumData.TreasureBoxItemType>> resultTypes;

	public static void CreateTreasurePurchasePopup(TreasureBoxItemInfo boxItemInfo, PopupMode mode, int costIdx)
	{
	}

	private static bool CheckBoxItemType(BoxShop boxShop, EnumData.TreasureBoxItemType itemType)
	{
		return false;
	}

	public static bool CheckInvenMaxAmount(BoxShop boxShop)
	{
		return false;
	}

	private static void RequestNormalBuying(TreasureBoxItemInfo boxItemInfo, string openCostType, int costIdx, Action callback)
	{
	}

	public static void RequestPackageBuying(TreasureBoxItemInfo boxItemInfo, string openCostType, int costIdx, Action callback)
	{
	}

	private static void RequestHeavyBuying(BoxShop boxShop, string openCostType, int costIdx, int buyCount, Action callback)
	{
	}

	private static void CheckAllMissions(BoxShop boxShop, string openCostType, int costIdx, int openCount, TreasureBoxResult result)
	{
	}

	private static void CheckAllMissions(BoxShop boxShop, string openCostType, int costIdx, int openCount, List<TreasureBoxResult> results)
	{
	}

	public static void ShowNeedGoldPopup()
	{
	}

	public static void ShowNeedTicketPopup(string name)
	{
	}
}
