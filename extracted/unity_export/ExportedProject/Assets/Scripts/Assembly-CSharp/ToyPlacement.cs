using System.Collections.Generic;
using CaoCaoIAB;
using LitJson;
using ToyUI;
using UnityEngine;

public class ToyPlacement : CaocaoUIModalPopup
{
	private static Queue<Banner> ms_bannerQueue;

	public static void ShowPlacement(string placementId)
	{
	}

	private static void Deserialize(JsonData data)
	{
	}

	private static void DequeueBanner()
	{
	}

	private static void ShowBanner(Banner banner)
	{
	}

	private static bool CheckDoNotShowToday(Banner banner)
	{
		return false;
	}

	private void UpdateUI(Banner banner)
	{
	}

	private string GetButtonName(int btnType)
	{
		return null;
	}

	private Texture2D GetButtonTexture(int btnType)
	{
		return null;
	}

	private void OnButtonClicked(Banner banner, Button btn)
	{
	}

	private void OnBGClicked(Banner banner, Button btn)
	{
	}

	private void SetDoNotShowToday(Banner banner)
	{
	}

	public void OnPurchaseSucceed(Purchase purchase)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}
}
