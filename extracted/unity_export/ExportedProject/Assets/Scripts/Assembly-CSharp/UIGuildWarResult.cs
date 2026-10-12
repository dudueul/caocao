using LitJson;
using UnityEngine;

public class UIGuildWarResult : CaocaoUIModalPopup
{
	[SerializeField]
	private UILabel GuildName_1st;

	[SerializeField]
	private UILabel GuildScore_1st;

	[SerializeField]
	private UITexture GuildTexture_1st;

	[SerializeField]
	private UILabel GuildName_2st;

	[SerializeField]
	private UILabel GuildScore_2st;

	[SerializeField]
	private UITexture GuildTexture_2st;

	[SerializeField]
	private UILabel GuildName_3st;

	[SerializeField]
	private UILabel GuildScore_3st;

	[SerializeField]
	private UITexture GuildTexture_3st;

	[SerializeField]
	private UILabel MyGuildName;

	[SerializeField]
	private GameObject MyGuildDim;

	[SerializeField]
	private GameObject MyGuildInfo;

	[SerializeField]
	private UILabel MyGuildRank;

	[SerializeField]
	private UILabel MyGuildScore;

	[SerializeField]
	private UITexture MyGuildTexture;

	private void Start()
	{
	}

	private void Update()
	{
	}

	public static void Show()
	{
	}

	private void SetActiveContents(bool active)
	{
	}

	private void OnGetGuildWarRankingList(JsonData response)
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
