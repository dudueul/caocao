using Caocao;
using UnityEngine;

public class PanelTopUserSpellGauge : CaocaoUIModalPopup
{
	[SerializeField]
	private UILabelLocalized labelLeftWallet;

	[SerializeField]
	private UILabelLocalized labelRightWallet;

	[SerializeField]
	private UISlider sliderLeftWallet;

	[SerializeField]
	private UISlider sliderRightWallet;

	[SerializeField]
	private GameObject[] leftBattleMapObjs;

	[SerializeField]
	private GameObject[] rightBattleMapObjs;

	[SerializeField]
	private UISprite sprLeftBar;

	[SerializeField]
	private UISprite sprRightBar;

	[SerializeField]
	private UITexture texLeftThumb;

	[SerializeField]
	private UITexture texRightThumb;

	protected override void Awake()
	{
	}

	private void Start()
	{
	}

	public void UpdateUI()
	{
	}

	private void UpdateWallet(UILabelLocalized label, UISlider slider, int walletCash)
	{
	}

	private void UpdateMapObjs()
	{
	}

	private void ActiveMapObjs(UnitSide unitSide)
	{
	}

	private void InitUI()
	{
	}

	private UILabelLocalized GetLabelWallet(UnitSide unitSide)
	{
		return null;
	}

	private UISlider GetSliderWallet(UnitSide unitSide)
	{
		return null;
	}

	private GameObject[] GetMapObjs(UnitSide unitSide)
	{
		return null;
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
