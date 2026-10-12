using System;
using UnityEngine;

public class PanelPopup3Buttons : CaocaoUIModalPopup
{
	private GameObject m_centerCoin;

	private GameObject m_centerFootNSilver;

	private GameObject m_btn1;

	private GameObject m_btn2;

	private GameObject m_btn3;

	private GameObject btnSliderPrevBtn;

	private GameObject btnSliderNextBtn;

	public Action Btn1Pressed;

	public Action Btn2Pressed;

	public Action Btn3Pressed;

	private string m_titleText;

	private string m_labelText;

	private string m_btn2Text;

	private string m_btn3Text;

	private int m_btn2Value;

	private int m_btn3Value;

	private const float SLIDER_SOUND_GAP = 0.05f;

	private float lastSliderSoundTime;

	private UISlider slider;

	private UILabelLocalized sliderLabelMin;

	private UILabelLocalized sliderLabelMax;

	private UILabelLocalized sliderLabelCur;

	private UILabelLocalized labelBtn2;

	private UILabelLocalized labelBtn3;

	private int sliderMinVal;

	private int sliderMaxVal;

	private int sliderCurVal;

	private int sliderEachVal1;

	private int sliderEachVal2;

	private int sliderLimitVal1;

	private int sliderLimitVal2;

	private int prevSliderValue;

	public bool autoClose;

	public Action onSetContext;

	private GameObject titleLabel
	{
		get
		{
			return null;
		}
	}

	protected GameObject centerTextOneLine
	{
		get
		{
			return null;
		}
	}

	protected GameObject centerTextTwoLine
	{
		get
		{
			return null;
		}
	}

	public string titleText
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string labelText
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string btn2Text
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string btn3Text
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int btn2Value
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int btn3Value
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public EnumData.GoodsType goodsType { get; set; }

	public string title
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static PanelPopup3Buttons CreateInstance()
	{
		return null;
	}

	protected override void OnDestroy()
	{
	}

	private void FindChildObjects()
	{
	}

	private void SetParentUIRoot()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected virtual void OnSetContext()
	{
	}

	private void UpdateUI()
	{
	}

	private void SetButton(GameObject button, EnumData.GoodsType goodsType, int goodsValue, string text)
	{
	}

	private void SetButton(GameObject button, int itemID, string itemIconName, int itemCount, string text)
	{
	}

	private void SetButton(GameObject button, int itemID, Texture itemTexture, int itemCount, string text)
	{
	}

	private void SetButton(GameObject button, int itemId, int amount, string text)
	{
	}

	private void SetButton(GameObject button, string text)
	{
	}

	public void SetTitleContext(string key, params object[] args)
	{
	}

	public void SetLabel1(string key, params object[] args)
	{
	}

	public void SetLabel2(string key, params object[] args)
	{
	}

	public void SetButton2(EnumData.GoodsType goodsType, int goodsValue, string text)
	{
	}

	public void SetButton2(int itemID, string itemIconName, int itemCount, string text)
	{
	}

	public void SetButton2(int amount, int itemId, string text)
	{
	}

	public void SetButton2(string text)
	{
	}

	public void SetButton3(EnumData.GoodsType goodsType, int goodsValue, string text)
	{
	}

	public void SetButton3(int itemID, string itemIconName, int itemCount, string text)
	{
	}

	public void SetButton3(int itemID, Texture itemTexture, int itemCount, string text)
	{
	}

	public void SetCenterGoldResource(int amount)
	{
	}

	public void SetCenterFoodSilverResource(int food, int silver)
	{
	}

	public void SetCenterItemResource(int itemId, int amount)
	{
	}

	private void OnBtn1Click()
	{
	}

	private void OnBtn2Click()
	{
	}

	private void OnBtn3Click()
	{
	}

	public void SetEnableBtn1(bool enable)
	{
	}

	public void SetEnableBtn2(bool enable)
	{
	}

	public void SetEnableBtn3(bool enable)
	{
	}

	public void SetSlider(int min, int max, int cur, int each1, int each2, int limit1, int limit2, string key, params object[] args)
	{
	}

	private void SetPressedBtns(Transform tr, Action<int> callback)
	{
	}

	public void OnSliderPrevButton()
	{
	}

	public void OnSliderPrevButtonImpl(int count)
	{
	}

	public void OnSliderNextButton()
	{
	}

	public void OnSliderNextButtonImpl(int count)
	{
	}

	private void OnUpdateSliderControl()
	{
	}

	private void UpdateSlider()
	{
	}

	private void UpdateSliderUi()
	{
	}

	public int GetSliderValue()
	{
		return 0;
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
