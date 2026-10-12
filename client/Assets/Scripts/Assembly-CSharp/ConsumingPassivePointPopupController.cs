using System;
using UnityEngine.SceneManagement;

public class ConsumingPassivePointPopupController : Singleton<ConsumingPassivePointPopupController>
{
	private struct Context
	{
		public bool inited;

		public string title;

		public string textKey;

		public string[] textArgs;

		public string btnText;

		public bool skipStartingPopup;

		public int cost;

		public HttpRequest.HttpRequestContext requestContext;

		public Action onFail;

		public void SetContext(string title, string textKey, string[] textArgs, string btnText, int cost, HttpRequest.HttpRequestContext requestContext, Action onFail, bool skipStartingPopup)
		{
		}

		private void SetPopupContext(string title, string text, string[] textArgs, string btnText, bool skipStartingPopup)
		{
		}

		private void SetCostContext(int cost)
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

	private IDepthManagerblePopup m_parent;

	private int lackCost;

	private int exchGold;

	private int lackExchGold;

	private bool startingPopupShown;

	private bool controlling;

	private bool processing;

	public void StartConsumingGoodsPopup(string title, string textKey, string[] textArgs, string btnText, int cost, HttpRequest.HttpRequestContext requestContext, Action onFail = null, bool skipStartingPopup = false, IDepthManagerblePopup parentPopup = null)
	{
	}

	private void CalculateLackCosts()
	{
	}

	private bool CanPurchase()
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

	private bool CanShowStartingPopup()
	{
		return false;
	}

	private void _StartPopup()
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
