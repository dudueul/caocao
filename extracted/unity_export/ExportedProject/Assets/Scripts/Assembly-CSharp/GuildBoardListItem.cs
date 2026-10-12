using UnityEngine;

public class GuildBoardListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public GuildBoardPostData postData { get; set; }
	}

	[SerializeField]
	private UserCommonProfileSmall m_profile;

	[SerializeField]
	private Transform m_guildJobParent;

	[SerializeField]
	private UILabel m_nationName;

	[SerializeField]
	private UILabel m_regDate;

	[SerializeField]
	private GameObject m_normalObj;

	[SerializeField]
	private UILabelLocalized m_normalCategory;

	[SerializeField]
	private UILabel m_normalTitle;

	[SerializeField]
	private GameObject m_noticeObj;

	[SerializeField]
	private UILabelLocalized m_noticeCategory;

	[SerializeField]
	private UILabel m_noticeTitle;

	[SerializeField]
	private UILabelLocalized m_replayCount;

	[SerializeField]
	private UILabelLocalized m_recommendCount;

	public string nationName
	{
		get
		{
			return null;
		}
	}

	public CellData cellData
	{
		get
		{
			return null;
		}
		protected set
		{
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	public void UpdateUI()
	{
	}

	public void OnUpdateUserInfo(UserInfo userInfo)
	{
	}

	public void OnClick()
	{
	}

	public void OnClickMenu()
	{
	}
}
