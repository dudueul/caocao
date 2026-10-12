using WorldMapMenu;

public class GuildUINavigatorBtn : PageButtonBase
{
	public enum ButtonType
	{
		FamousInvition = 0,
		Present = 1,
		Support = 2,
		Mission = 3,
		Reinforcements = 4,
		BossRaid = 5,
		GuildWar = 6,
		GuildWarMng = 7,
		Donate = 8
	}

	public ButtonType buttonType;

	protected override void Awake()
	{
	}

	public override void OnClickImpl()
	{
	}

	private void OnEnable()
	{
	}

	private void SetButtonEnable(bool enable)
	{
	}
}
