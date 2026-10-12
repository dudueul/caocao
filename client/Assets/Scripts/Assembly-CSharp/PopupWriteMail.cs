public class PopupWriteMail : PanelSelfRegister2UIComponentsMonobehaviour
{
	public UILabelLocalized to;

	public UILabelLocalized title;

	public UILabelLocalized message;

	public UIInput toInput;

	public UIInput titleInput;

	public UIInput messageInput;

	private PanelMail panelMail;

	private bool m_sendBtnSelected;

	public MailData mailData { get; private set; }

	public void WriteMail(PanelMail panelMail)
	{
	}

	public void WriteMail(PanelMail panelMail, MailData mailData)
	{
	}

	public void WriteReplyMail(PanelMail panelMail, MailData mailData)
	{
	}

	public override void ClosePanel()
	{
	}

	public void SendMail()
	{
	}

	private bool CheckMailValid()
	{
		return false;
	}

	public void OnSubmitTo()
	{
	}

	public void OnSubmitTitle()
	{
	}

	public void OnSubmitMessage()
	{
	}

	private void Update()
	{
	}
}
