using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine.SceneManagement;

public class ConsumingGoodsPopupController : Singleton<ConsumingGoodsPopupController>
{
	private struct Context
	{
		public bool inited;

		public string title;

		public string textKey;

		public string[] textArgs;

		public string btnText;

		public bool skipStartingPopup;

		public Dictionary<EnumData.GoodsType, int> goodsType2Cost;

		public HttpRequest.HttpRequestContext requestContext;

		public Action onOK;

		public Action onFail;

		public void SetContext(string title, string textKey, string[] textArgs, string btnText, EnumData.GoodsType[] costType, int[] cost, HttpRequest.HttpRequestContext requestContext, Action onFail, bool skipStartingPopup)
		{
		}

		private void SetPopupContext(string title, string text, string[] textArgs, string btnText, bool skipStartingPopup)
		{
		}

		private void SetCostContext(EnumData.GoodsType[] costType, int[] cost)
		{
		}

		private void SetRequestContext(HttpRequest.HttpRequestContext requestContext)
		{
		}

		private void SetFailContext(Action onFail)
		{
		}

		public void Clear()
		{
		}
	}

	private PanelPopup2Buttons popup;

	private Context m_context;

	private Action<PanelPopup2Buttons> m_customPopup;

	private bool m_customPopupUsePurchase;

	private IDepthManagerblePopup m_parent;

	private bool harvested;

	private Dictionary<EnumData.GoodsType, int> goodsType2LackCost;

	private int exchGold;

	private int lackExchGold;

	private bool startingPopupShown;

	private bool controlling;

	private Action m_onIfHarvest;

	private bool processing;

	public void StartConsumingGoodsPopup(string title, string textKey, string[] textArgs, string btnText, EnumData.GoodsType[] costType, int[] cost, HttpRequest.HttpRequestContext requestContext, Action onFail = null, bool skipStartingPopup = false, IDepthManagerblePopup parent = null, Action onIfHarvest = null)
	{
	}

	public void StartConsumingGoodsCustomPopup(string title, string textKey, string[] textArgs, string btnText, Action<PanelPopup2Buttons> setPopupContext, bool usePurchase, EnumData.GoodsType[] costType, int[] cost, HttpRequest.HttpRequestContext requestContext, Action onFail = null, bool skipStartingPopup = false, Action onIfHarvest = null)
	{
	}

	public void StartConsumingGoodsPopupWithCallback(string title, string textKey, string[] textArgs, string btnText, EnumData.GoodsType[] costType, int[] cost, Action onOK, Action onFail = null, bool skipStartingPopup = false, IDepthManagerblePopup parent = null, Action onIfHarvest = null)
	{
	}

	private void CalculateLackCosts()
	{
	}

	private bool CanPurchase()
	{
		return false;
	}

	private bool CanHarvest()
	{
		return false;
	}

	private bool CanExchange()
	{
		return false;
	}

	private bool NotEnoughGold()
	{
		return false;
	}

	private bool NotEnoughStamp()
	{
		return false;
	}

	private bool CanShowStartingPopup()
	{
		return false;
	}

	private void _StartPopup()
	{
	}

	private void ShowGoGainStampPopup()
	{
	}

	private void ShowHarvestPopup()
	{
	}

	private bool CheckHarvestAllTown(ref List<int> townIds)
	{
		return false;
	}

	private void HarvestResponse(JsonData response)
	{
	}

	private void ShowExchangePopup()
	{
	}

	private void ExchangeGoods()
	{
	}

	private void ShowCantConsumePopup()
	{
	}

	private void OnFail()
	{
	}

	private void ShowGoCashshopPopup()
	{
	}

	private void ShowStartingPopup()
	{
	}

	private void ShowPurchasePopup()
	{
	}

	private void ProcessMainWork()
	{
	}

	private void ProcessURI()
	{
	}

	private void FinishPopup()
	{
	}

	private void ClearContext()
	{
	}

	public void ClickYes()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnLevelFinishedLoading(Scene scene, LoadSceneMode mode)
	{
	}
}
