using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class PanelMail : CaocaoUIModalPopup
{
	private enum MailDataItemType
	{
		General = 0,
		Treasure = 1,
		Rune = 2,
		TimeBoxSlot = 3,
		Retrieve = 4
	}

	public UIReuseScrollView scrollView;

	public UIReuseGrid uiGrid;

	public PopupWriteMail writeMail;

	public PopupReadMail readMail;

	public PopupGetMailItems itemPopup;

	private List<MailData> m_mails;

	private MailDataItemType mailDataItemType;

	private int m_runeAmount;

	private Item m_levelUpItem;

	private MailData m_levelUpMailData;

	private UserUnit m_levelUpUnit;

	private int prevUnitLv;

	private int prevReinforceLv;

	private int prevJobLv;

	public GameObject m_hideCheckBox;

	private bool m_hideItemReceivedMail;

	private MailData bckMailData;

	protected override void Awake()
	{
	}

	public void Init()
	{
	}

	public void OnClosePanel()
	{
	}

	public static void ShowMailPanel()
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

	private void InitPanel()
	{
	}

	private void InitMails(JsonData mails)
	{
	}

	private void UpdateUI()
	{
	}

	private bool HaveNewMail()
	{
		return false;
	}

	public void PrevMail(MailData mailData)
	{
	}

	public void NextMail(MailData mailData)
	{
	}

	private int GetIndex(MailData mailData)
	{
		return 0;
	}

	private void SetReadMail(int srl)
	{
	}

	private void SetGotItem(int srl)
	{
	}

	private MailData GetMailItemData(int srl)
	{
		return null;
	}

	public void ShowList()
	{
	}

	public void HideList()
	{
	}

	private void RequestMails()
	{
	}

	private void OnGetMails(JsonData response)
	{
	}

	public void ReadMail(MailData mailData)
	{
	}

	public void SendReadMail(MailData mailData)
	{
	}

	private void OnReadMail(JsonData response)
	{
	}

	public void GetItems(MailData mailData)
	{
	}

	private void OnGetItem(JsonData response)
	{
	}

	public void RemoveMail(MailData mailData)
	{
	}

	private void OnRemoveMail(JsonData response)
	{
	}

	public void RemoveMail(int srl)
	{
	}

	public void SendMail(MailData mailData)
	{
	}

	private void OnSendMail(JsonData response)
	{
	}

	public void WriteMail()
	{
	}

	public void ReplyMail(MailData mailData)
	{
	}

	private void ShowBattleDeck(Item item, MailData mailData)
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowUnitSelectAsync()
	{
		return null;
	}

	public void OnSelectUnitCallback(UserUnit userUnit)
	{
	}

	private void OnLevelUpUnit(JsonData response)
	{
	}

	private void UnitLevelUpEffect()
	{
	}

	public void OnHideButtonClicked()
	{
	}
}
