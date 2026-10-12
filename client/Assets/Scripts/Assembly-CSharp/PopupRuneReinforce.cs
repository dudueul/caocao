using System;
using UnityEngine;

public class PopupRuneReinforce : CaocaoUIModalPopup
{
	[SerializeField]
	private UILabelLocalized m_titleLabel;

	[SerializeField]
	private UILabelLocalized m_passiveNameLabel;

	[SerializeField]
	private UILabelLocalized m_passiveNowLevelLabel;

	[SerializeField]
	private UISprite m_passiveExpGauge;

	[SerializeField]
	private RunePassiveUI m_runePassiveUI;

	[SerializeField]
	private GameObject m_targetStateObj;

	[SerializeField]
	private GameObject m_maxStateObj;

	[SerializeField]
	private UILabelLocalized m_maxPassiveValueLabel;

	[SerializeField]
	private UILabelLocalized m_prevPassiveValueLabel;

	[SerializeField]
	private UILabelLocalized m_nextPassiveValueLabel;

	[SerializeField]
	private UISlider m_slider;

	[SerializeField]
	private UILabelLocalized m_sliderMinLabel;

	[SerializeField]
	private UILabelLocalized m_sliderMaxLabel;

	[SerializeField]
	private UILabelLocalized m_sliderLabel;

	[SerializeField]
	private UISprite m_limitSliderBar;

	[SerializeField]
	private UISprite m_currSliderBar;

	[SerializeField]
	private UILabelLocalized m_soulValueLabel;

	[SerializeField]
	private UISprite m_soulSprite;

	[SerializeField]
	private GameObject m_1btnObj;

	[SerializeField]
	private GameObject m_2btnObj;

	[SerializeField]
	private TweenAlpha m_bgTweenAlpha;

	private bool m_tweenEnd;

	private int m_sliderLevelValue;

	private int m_beforeExp;

	private int m_targetExp;

	private RuneItemSlot m_runeItemSlot;

	private UserRuneSlot m_userRuneSlot;

	private UserUnit m_userUnit;

	private Action m_callback;

	private int sliderStep
	{
		get
		{
			return 0;
		}
	}

	private int sliderMaxValue
	{
		get
		{
			return 0;
		}
	}

	private int sliderMinValue
	{
		get
		{
			return 0;
		}
	}

	private int sliderCurrentValue
	{
		get
		{
			return 0;
		}
	}

	private int currentLevel
	{
		get
		{
			return 0;
		}
	}

	public static void ShowPopup(UserUnit userUnit, int runeSlotIndex, int runeSlotNum, Action callback = null)
	{
	}

	public void Apply(UserUnit userUnit, int runeSlotIndex, int runeSlotNum, Action callback)
	{
	}

	private void ReinforceRune()
	{
	}

	private void ApplyValue()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateFixedUI()
	{
	}

	private void UpdateChangeUI()
	{
	}

	public void OnBtnClickNextLevel()
	{
	}

	public void OnBtnClickPrevLevel()
	{
	}

	public void OnSliderValueChange()
	{
	}

	public void OnBtnClickReinforce()
	{
	}

	private void Update()
	{
	}

	private void SetBtnState()
	{
	}

	private int GetLimitLevel()
	{
		return 0;
	}

	protected virtual void AnimateGauge()
	{
	}

	private void OnUpdateAnimateRunePassiveExp(float value)
	{
	}

	private void OnCompleteAnimateRunePassiveExp()
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
