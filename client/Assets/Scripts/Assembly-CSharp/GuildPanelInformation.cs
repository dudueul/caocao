using UnityEngine;

public class GuildPanelInformation : PanelSelfRegister2UIComponentsMonobehaviour
{
	private enum Tab
	{
		NotSet = -1,
		Member = 0,
		History = 1,
		CompetititonRanking = 2,
		Applicant = 3
	}

	public CaocaoTabButtons tabButtons;

	public GameObject btnApplicant;

	public GameObject objApplicantMark;

	public GameObject objMasterMember;

	public GameObject objMember;

	public GameObject objHistory;

	public GameObject objApplicant;

	public GameObject objCompetitionRanking;

	private Tab currentTab;

	private EnumData.GuildJopType prevJob;

	private ulong selectedUserId;

	private Transform m_btnLordInfo;

	private Transform btnLordInfo
	{
		get
		{
			return null;
		}
	}

	protected override void Awake()
	{
	}

	private void SetActiveBtnLordInfo(bool active)
	{
	}

	public void OnBtnUserInfo()
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	public static void Show()
	{
	}

	private void SwitchTab(CaocaoButton tabButton)
	{
	}

	private void SwitchTab(Tab tab)
	{
	}

	private void OnUpdateMe()
	{
	}

	private void OnUpdateMember()
	{
	}

	private void UpdateApplicantMark()
	{
	}

	private void OnUpdateApplicants()
	{
	}

	private void OnGuildError(string error, ref bool handled)
	{
	}

	private void OnEnable()
	{
	}

	protected override void OnDestroy()
	{
	}
}
