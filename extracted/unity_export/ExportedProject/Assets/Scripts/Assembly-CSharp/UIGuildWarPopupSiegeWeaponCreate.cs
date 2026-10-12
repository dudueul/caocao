using System;
using GuildWar;
using UnityEngine;

public class UIGuildWarPopupSiegeWeaponCreate : CaocaoUIModalPopup
{
	private enum SiegeWeaponId
	{
		Catapult = 15470001,
		BatteringRam = 15470002
	}

	[SerializeField]
	private UIButton m_Catapult_Use_Btn;

	[SerializeField]
	private UIButton m_Catapult_Purchase_Btn;

	[SerializeField]
	private UILabelLocalized m_Catapult_Purchase_Text;

	[SerializeField]
	private GameObject m_Catapult_Image;

	[SerializeField]
	private UIButton m_BatteringRam_Use_Btn;

	[SerializeField]
	private UIButton m_BatteringRam_Purchase_Btn;

	[SerializeField]
	private UILabelLocalized m_BatteringRam_Purchase_Text;

	[SerializeField]
	private GameObject m_BatteringRam_Image;

	[SerializeField]
	private UIButton m_Close_Btn;

	private Action<int> m_onSelectSiegeWeapon;

	private UserTradingPostData myTrandingPostData;

	private GuildWar.GuildData myGuildData;

	private MaterialPropertyBlock _matProp;

	public void Start()
	{
	}

	public void UiUpdate()
	{
	}

	public void ChangeGuildWarImage(GameObject spriteGameObj, int HPGrade, string imageName)
	{
	}

	public static void Show(Action<int> onSelectSiegeWeapon)
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

	protected override void ClosePanelImpl()
	{
	}

	protected override void ShowPanelImpl()
	{
	}
}
