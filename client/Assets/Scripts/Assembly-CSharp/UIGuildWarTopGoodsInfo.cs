using GuildWar;

public class UIGuildWarTopGoodsInfo : SelfRegister2UIComponentsMonobehaviour
{
	private UILabel Food;

	private UILabel Silver;

	private UILabel Fund;

	private UILabel Contribution;

	private UILabel Entry;

	private GuildWar.GuildData myGuildData;

	private GuildWar.UserData myUserData;

	private UserTradingPostData myTrandingPostData;

	public static UIGuildWarTopGoodsInfo self;

	private void InitUI()
	{
	}

	private void OnUpdateUserTradingPost(UserTradingPostData userTradingPostData)
	{
	}

	private void OnUpdateUserData(GuildWar.UserData userData)
	{
	}

	private void OnDataRefAdded(UserKey userKey, GuildWar.UserData userData)
	{
	}

	public void UpdateUI()
	{
	}

	protected override void Awake()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}

	public static void Show()
	{
	}

	public static void Hide()
	{
	}

	public void Update()
	{
	}
}
