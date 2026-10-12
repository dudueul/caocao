using System;
using System.Collections.Generic;

public class ConsumingItemsPopupController : Singleton<ConsumingItemsPopupController>
{
	private struct Context
	{
		public bool inited;

		public string title;

		public string textKey;

		public string[] textArgs;

		public string btnText;

		public bool skipStartingPopup;

		public Dictionary<Item, int> itemsCost;

		public HttpRequest.HttpRequestContext requestContext;

		public Action onFail;

		public void SetContext(string title, string textKey, string[] textArgs, string btnText, Item[] costType, int[] cost, HttpRequest.HttpRequestContext requestContext, Action onFail, bool skipStartingPopup)
		{
		}

		private void SetPopupContext(string title, string text, string[] textArgs, string btnText, bool skipStartingPopup)
		{
		}

		private void SetCostContext(Item[] costType, int[] cost)
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

	private Dictionary<Item, int> itemsLackCost;

	private bool startingPopupShown;

	private bool processing;

	public void StartConsumingItemsPopup(string title, string textKey, string[] textArgs, string btnText, Item[] costType, int[] cost, HttpRequest.HttpRequestContext requestContext, Action onFail = null, bool skipStartingPopup = false)
	{
	}

	private void CalculateLackCosts()
	{
	}

	private bool CanPurchase()
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

	private void ShowCantConsumePopup()
	{
	}

	private void OnFail()
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
}
