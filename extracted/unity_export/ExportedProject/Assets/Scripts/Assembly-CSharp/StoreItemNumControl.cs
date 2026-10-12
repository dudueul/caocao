using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

public class StoreItemNumControl : MonoBehaviour
{
	private StoreItem m_selected;

	private UIInput m_numInput;

	private int m_minCount;

	private const int MAX_PURCHASE_EQUIPMENT_AMOUNT = 99;

	private const int MAX_PURCHASE_CONSUMABLE_AMOUNT = 999;

	private const int MAX_PURCHASE_MATERIAL_AMOUNT = 99999;

	private const int MAX_PURCHASE_EXCHANGE_AMOUNT = 9999;

	private int m_maxCount;

	public Action<StoreItem, int> OnSelectedCountChanged;

	private ObscuredInt m_itemCount;

	private bool m_init;

	public StoreItem selected
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public UIInput numInput
	{
		get
		{
			return null;
		}
	}

	public PressableButton minusBtn
	{
		get
		{
			return null;
		}
	}

	public PressableButton plusBtn
	{
		get
		{
			return null;
		}
	}

	public UISlider countSlider
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized minLabel
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized maxLabel
	{
		get
		{
			return null;
		}
	}

	private int minCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	private int maxCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int itemCount
	{
		get
		{
			return 0;
		}
		private set
		{
		}
	}

	public void Init()
	{
	}

	private void OnDestroy()
	{
	}

	public void SetSelectedStoreItem(StoreItem storeItem)
	{
	}

	public void ResetUI()
	{
	}

	private void _resetExchangeUI(ExchangeStoreItem selectedItem)
	{
	}

	public void OnNumSubmit()
	{
	}

	public void OnSliderUpdated()
	{
	}

	public void OnPlus(int count)
	{
	}

	public void OnMinus(int count)
	{
	}

	private bool ValidCheck(int newItemCount)
	{
		return false;
	}
}
