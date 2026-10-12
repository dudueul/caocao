using System;
using UnityEngine;

public class PanelPopupSlider2Buttons : PanelPopupBase
{
	private Func<PopupState> OnPurchase__BackingField;

	private Func<PopupState> OnCancel__BackingField;

	private Func<PopupState> OnConfirm__BackingField;

	private Func<PopupState> OnChangeValue__BackingField;

	private const float SLIDER_SOUND_GAP = 0.05f;

	private float lastSliderSoundTime;

	private UISlider slider;

	private UILabelLocalized sliderLabelMin;

	private UILabelLocalized sliderLabelMax;

	private UILabelLocalized sliderLabelCur;

	private UILabelLocalized labelPrice;

	private int sliderMinValue;

	private int sliderMaxValue;

	private int sliderGagueCurVal;

	private int sliderGagueMinVal;

	private int sliderGagueMaxVal;

	private int prevSliderValue;

	private EnumData.GoodsType costGoodsType;

	private int[] sliderCostVal;

	private bool costMultiplicationBool;

	private int costMultiplicationVal;

	private bool costMultiplicationFuncBool;

	private Func<int, int> costMultiplicationFunc;

	public int IntervalCount;

	public Action onSetContext;

	protected GameObject titleLabel
	{
		get
		{
			return null;
		}
	}

	protected GameObject descType1
	{
		get
		{
			return null;
		}
	}

	protected GameObject _1btn
	{
		get
		{
			return null;
		}
	}

	protected GameObject _2btn
	{
		get
		{
			return null;
		}
	}

	protected GameObject btnPurchase
	{
		get
		{
			return null;
		}
	}

	protected GameObject btnCancel
	{
		get
		{
			return null;
		}
	}

	protected GameObject _purchaseIcon
	{
		get
		{
			return null;
		}
	}

	protected GameObject _purchasePrice
	{
		get
		{
			return null;
		}
	}

	protected GameObject _slider
	{
		get
		{
			return null;
		}
	}

	protected Transform tr_SliderPrevBtn
	{
		get
		{
			return null;
		}
	}

	protected Transform tr_SliderNextBtn
	{
		get
		{
			return null;
		}
	}

	protected Transform tr_sliderControl
	{
		get
		{
			return null;
		}
	}

	protected Transform tr_sliderControlMin
	{
		get
		{
			return null;
		}
	}

	protected Transform tr_sliderControlMax
	{
		get
		{
			return null;
		}
	}

	protected Transform tr_sliderControlThumb
	{
		get
		{
			return null;
		}
	}

	protected GameObject blackBG
	{
		get
		{
			return null;
		}
	}

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

	public string cancel
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int GetSliderValue
	{
		get
		{
			return 0;
		}
	}

	public event Func<PopupState> OnPurchase
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Func<PopupState> OnCancel
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Func<PopupState> OnConfirm
	{
		add
		{
		}
		remove
		{
		}
	}

	public event Func<PopupState> OnChangeValue
	{
		add
		{
		}
		remove
		{
		}
	}

	public static PanelPopupSlider2Buttons CreateInstance()
	{
		return null;
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

	private void Init()
	{
	}

	public void SetDescType1(string key, params object[] args)
	{
	}

	public void SetSlider(int minValue, int maxValue, int curGague, int minGague, int maxGague, EnumData.GoodsType goodsType, int costMultiplication)
	{
	}

	public void SetSlider(int minValue, int maxValue, int curGague, int minGague, int maxGague, EnumData.GoodsType goodsType, int[] cost)
	{
	}

	public void SetSlider(int minValue, int maxValue, int curGague, int minGague, int maxGague, EnumData.GoodsType goodsType, Func<int, int> MultiplicationFunc)
	{
	}

	private void SetPressedBtns(Transform tr, Action<int> callback)
	{
	}

	public void OnSliderPrevButton(int count)
	{
	}

	public void OnSliderNextButton(int count)
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

	public int GetTotalCost()
	{
		return 0;
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

	public void OnBtnClick()
	{
	}

	protected override void OnDestroy()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
