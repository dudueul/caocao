using UnityEngine;

public class PopupReadMail : PanelSelfRegister2UIComponentsMonobehaviour
{
	private MailData m_mailData;

	public PrefabInstance unitInst;

	public UILabelLocalized titleShort;

	public UILabelLocalized titleLong;

	public UILabelLocalized from;

	private UILabelLocalized title;

	public UILabelLocalized message;

	public GameObject itemsObj;

	public UILabelLocalized sendTime;

	public UILabelLocalized leftTime;

	public GameObject getItemBtn;

	public GameObject btnDelete;

	public GameObject btnReply;

	public GameObject customerCenterBtn;

	private PanelMail panelMail;

	public MailData mailData
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	protected override void Awake()
	{
	}

	public void ReadMail(PanelMail panelMail, MailData mailData)
	{
	}

	public void UpdateSenderInfo()
	{
	}

	public override void ClosePanel()
	{
	}

	public void UpdateUI()
	{
	}

	public void GetItems()
	{
	}

	public void PrevMail()
	{
	}

	public void NextMail()
	{
	}

	public void GoToCustomerCenter()
	{
	}

	public void CheckRemoveMail()
	{
	}

	public void RemoveMail()
	{
	}

	public void ReplyMail()
	{
	}

	private void Update()
	{
	}
}
