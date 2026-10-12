using System;
using UnityEngine;

public class PopupScenarioStart : CaocaoUIModalPopup
{
	private string scenarioName;

	private string scenarioImgName;

	private string desc;

	private Action startBattleCallback;

	private UILabelLocalized m_titleLabel;

	private UILabelLocalized m_descLabel;

	private UITexture m_scenarioImg;

	private GameObject m_btnGoBattle;

	private UILabelLocalized titleLabel
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized descLabel
	{
		get
		{
			return null;
		}
	}

	private UITexture scenarioImg
	{
		get
		{
			return null;
		}
	}

	private GameObject btnGoBattle
	{
		get
		{
			return null;
		}
	}

	public static void Show(string scenarioName, string scenarioImgName, string desc, Action startBattleCallback)
	{
	}

	private void UpdateUI()
	{
	}

	public void OnGoBattleButton()
	{
	}

	protected override void OnDestroy()
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
