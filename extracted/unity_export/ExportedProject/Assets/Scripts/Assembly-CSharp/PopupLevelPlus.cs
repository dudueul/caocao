using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PopupLevelPlus : CaocaoUIModalPopup, PopupLevelPlusMethod.IParentLevelPlusMethod
{
	protected UILabelLocalized m_name;

	protected UILabelLocalized m_level;

	protected UISprite m_levelBar;

	private UILabelLocalized m_minLevelLabel;

	private UILabelLocalized m_maxLevelLabel;

	private UILabelLocalized m_targetLevelLabel;

	private UISlider m_levelGauge;

	private UISprite m_limitBar;

	private UISprite m_currBar;

	private UIButton m_prevBtn;

	private UIButton m_nextBtn;

	private GameObject m_oneBtn;

	protected GameObject m_threeBtn;

	protected UILabelLocalized m_threeBtnCost;

	protected UITexture m_itemIcon;

	private TweenAlpha m_bgTweenAlpha;

	protected int m_itemId;

	protected int m_minLv;

	protected int m_maxLv;

	private int m_limitLv;

	private int m_beforeExp;

	private int m_beforeLevel;

	protected int m_currTargetLevel;

	protected Transform _trsUnitInfo;

	public Action OnUpdatedLev;

	private bool m_tweenEnd;

	protected virtual Transform trsUnitInfo
	{
		get
		{
			return null;
		}
	}

	public string methodTitle
	{
		get
		{
			return null;
		}
	}

	protected virtual int needItemId
	{
		get
		{
			return 0;
		}
	}

	protected virtual string targetName
	{
		get
		{
			return null;
		}
	}

	protected virtual int curLv
	{
		get
		{
			return 0;
		}
	}

	protected virtual int expSum
	{
		get
		{
			return 0;
		}
	}

	protected virtual int needExp
	{
		get
		{
			return 0;
		}
	}

	protected virtual int curExp
	{
		get
		{
			return 0;
		}
	}

	protected virtual int expOnCurrentLevToShow
	{
		get
		{
			return 0;
		}
	}

	protected virtual bool isMaxLevel
	{
		get
		{
			return false;
		}
	}

	protected virtual EnumData.GoodsType goods
	{
		get
		{
			return EnumData.GoodsType.None;
		}
	}

	public virtual int currTargetLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	Transform PopupLevelPlusMethod.IParentLevelPlusMethod.transform
	{
		get
		{
			return IParentLevelPlusMethod_002Eget_transform();
		}
	}

	protected virtual int ExpCost(int lv)
	{
		return 0;
	}

	protected virtual int tartgetExpSum(int lv)
	{
		return 0;
	}

	protected virtual bool CanUseGoods(int cost)
	{
		return false;
	}

	private void Update()
	{
	}

	protected virtual void InitUI()
	{
	}

	public virtual List<PopupLevelPlusMethod.ItemData> GetItemDatas(out PopupLevelPlusMethod.ItemData selected)
	{
		selected = null;
		return null;
	}

	public virtual void SetItemData(PopupLevelPlusMethod.ItemData itemData)
	{
	}

	protected virtual void UpdateMinMaxLv()
	{
	}

	protected override void OnDestroy()
	{
	}

	protected virtual void UpdateUI()
	{
	}

	protected virtual void ItemIconUpdate()
	{
	}

	protected virtual void UpdateLevelUI(int targetLevel)
	{
	}

	protected virtual int GetLevelUpCost(int toLevel)
	{
		return 0;
	}

	public virtual void OnPopupLevelPlusMethod()
	{
	}

	public void PrevLevel()
	{
	}

	public void NextLevel()
	{
	}

	public void OnChangeTargetLevel()
	{
	}

	public void PlusUnitLevel()
	{
	}

	protected virtual void StartConsuming(int cost)
	{
	}

	protected virtual void StartConsuming(EnumData.GoodsType goodsType, int itemId, int cost)
	{
	}

	protected virtual void OnResponse(JsonData response)
	{
	}

	protected virtual void AnimateGauge()
	{
	}

	private void OnUpdateAnimateUnitExp(float value)
	{
	}

	private void OnCompleteAnimateUnitExp()
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

	private Transform IParentLevelPlusMethod_002Eget_transform()
	{
		return null;
	}
}
