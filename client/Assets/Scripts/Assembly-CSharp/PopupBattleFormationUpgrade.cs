using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class PopupBattleFormationUpgrade : CaocaoUIModalPopup
{
	public UILabelLocalized nameLabel;

	public UILabelLocalized levelLabel;

	public UILabelLocalized frontEffectLabel;

	public UILabelLocalized frontFromValueLabel;

	public UILabelLocalized frontToValueLabel;

	public UILabelLocalized middleEffectLabel;

	public UILabelLocalized middleFromValueLabel;

	public UILabelLocalized middleToValueLabel;

	public UILabelLocalized rearEffectLabel;

	public UILabelLocalized rearFromValueLabel;

	public UILabelLocalized rearToValueLabel;

	public UISlider levelSlider;

	public UILabelLocalized sliderLevelLabel;

	public UILabelLocalized toLevelLabel;

	public UIButton increaseButton;

	public UIButton decreaseButton;

	public UILabelLocalized minLevelLabel;

	public UILabelLocalized maxLevelLabel;

	public UISprite currentBarSprite;

	public UISprite limitBarSprite;

	public UISprite goodsType1Sprite;

	public UISprite goodsType2Sprite;

	public UILabelLocalized cost1Label;

	public UILabelLocalized cost2Label;

	public GameObject oneBtn;

	public GameObject twoBtn;

	public GameObject dimBtn;

	public UILabelLocalized dimLabel;

	public UILabelLocalized[] effectLabels;

	public UILabelLocalized[] effectFromValues;

	public UILabelLocalized[] effectToValues;

	private Action onClose;

	private int m_minLv;

	private int m_maxLv;

	private int m_limitLv;

	private bool m_tweenEnd;

	private EnumData.GoodsType[] m_goodsTypes;

	private int[] m_costs;

	private int m_currrentTargetLevel;

	public UserBattleFormation userBattleFormation { get; set; }

	public int currentTargetLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public static void Show(UserBattleFormation userBattleFormation, Action onClose)
	{
	}

	private void Init()
	{
	}

	public void OnFinishedTween()
	{
	}

	private void UpdateLevelUi(int targetLevel)
	{
	}

	private int GetMaxLevel()
	{
		return 0;
	}

	private int GetLimitLevel()
	{
		return 0;
	}

	private void GetLevelUpCost(int targetLevel, int goodsTypeIndex, out EnumData.GoodsType goodsType, out int cost)
	{
		goodsType = default(EnumData.GoodsType);
		cost = default(int);
	}

	public void OnClickUpgrade()
	{
	}

	private void Upgrade()
	{
	}

	[DebuggerHidden]
	private IEnumerator PlayUpgradeCompleteEffect()
	{
		return null;
	}

	public void OnChangeTargetLevel()
	{
	}

	public void OnClickIncrease()
	{
	}

	public void OnClickDecrease()
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
