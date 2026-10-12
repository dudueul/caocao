using UnityEngine;

public class PanelArenaIntroLobby : PanelSelfRegister2UIComponentsMonobehaviour
{
	public const string popupName = "PanelArenaIntroLobby";

	public const int TICKET_ID = 3100291;

	public const int TICKET_MISSION = 4970058;

	[SerializeField]
	private UILabelLocalized centerMessage;

	[SerializeField]
	private UILabelLocalized ticketNum;

	[SerializeField]
	private GameObject enterButton;

	[SerializeField]
	private GameObject payButton;

	private bool enter;

	protected override void Awake()
	{
	}

	public static void Show()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	private void UpdateUI()
	{
	}

	public void OnBackButton()
	{
	}

	public void OnBuyTicketButton()
	{
	}

	public void OnBattleInfoButton()
	{
	}

	public void OnEnterButton()
	{
	}

	private void OpenGate()
	{
	}

	private void GotoWorldMap()
	{
	}

	private void OnEndOpenAnimation()
	{
	}

	protected override void OnDestroy()
	{
	}
}
