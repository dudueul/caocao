using UnityEngine;

public class MailListItem : UIReuseScrollViewCell
{
	public class MailItemData : IReuseCellData
	{
		public int Index { get; set; }

		public MailData mailData { get; set; }
	}

	private MailItemData m_mailItemData;

	private MailData m_mailData;

	public PrefabInstance unitInst;

	public GameObject shortDesc;

	public GameObject longDesc;

	public UILabelLocalized fromShort;

	public UILabelLocalized titleShort;

	public UILabelLocalized messageShort;

	public UILabelLocalized fromLong;

	public UILabelLocalized titleLong;

	public UILabelLocalized messageLong;

	private UILabelLocalized from;

	private UILabelLocalized title;

	private UILabelLocalized message;

	public GameObject itemsObj;

	public GameObject customerCenterButton;

	public UILabelLocalized sendTime;

	public UILabelLocalized leftTime;

	public GameObject dim;

	public GameObject getItemBtn;

	public MailItemData mailItemData
	{
		get
		{
			return null;
		}
	}

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

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void OnDestroy()
	{
	}

	public void SetMail(MailData mailData)
	{
	}

	public void UpdateSenderInfo()
	{
	}

	private void Update()
	{
	}

	public void GetItems()
	{
	}

	public void GoToCustomerCenter()
	{
	}

	private void OnClick()
	{
	}

	public void UpdateUI()
	{
	}
}
