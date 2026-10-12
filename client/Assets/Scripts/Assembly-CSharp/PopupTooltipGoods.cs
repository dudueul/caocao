using System;
using UnityEngine;

public class PopupTooltipGoods : SelfRegister2UIComponentsMonobehaviour
{
	private ItemLocker itemLocker;

	private int itemId;

	private EnumData.RaidStuffType stuffType;

	private Action shortCutCallback;

	private UISprite m_icon;

	private UILabelLocalized m_labelName;

	private UILabelLocalized m_desc;

	private UIButton m_gainButton;

	private UIButton m_useButton;

	private UIGrid m_gridButton;

	private UISprite icon
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelName
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized desc
	{
		get
		{
			return null;
		}
	}

	private UIButton gainButton
	{
		get
		{
			return null;
		}
	}

	private UIButton useButton
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridButton
	{
		get
		{
			return null;
		}
	}

	public static void Show(int itemLockerId, Vector3 basePos, Action shortCutCallback = null)
	{
	}

	private void Start()
	{
	}

	private bool Initiate()
	{
		return false;
	}

	private void UpdateUI(int itemLockerId, Vector3 basePos)
	{
	}

	public void OnGainButton()
	{
	}

	public void OnUseButton()
	{
	}

	private void CheckShortCut()
	{
	}

	private void DoShortCut(Shortcut shortCut)
	{
	}

	protected override void OnDestroy()
	{
	}
}
