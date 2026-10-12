using UnityEngine;

public class PanelUserInfo : MonoBehaviour
{
	public UILabelLocalized m_level;

	public UILabelLocalized m_userName;

	public UISprite m_exp;

	public UITexture m_unitFaceTex;

	public UILabelLocalized m_unionName;

	public GameObject m_btnUserInfo;

	private bool m_init;

	private NationFlagUI nationFlagUI
	{
		get
		{
			return null;
		}
	}

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	public void UpdateSkillEffect()
	{
	}

	public void UpdateUI()
	{
	}

	public void UpdateUI(MyUserData userData)
	{
	}

	public void UpdateUI(OpponentUserData userData)
	{
	}

	public void UpdateUI(UserInfo info, UserUnits userUnits, int maxUnitCost, string unionName, string nationCode)
	{
	}

	private void UpdateLordEffect(IUserDataBase userData)
	{
	}

	private void UpdateLeaderSkillBtn(UserUnit lordUnit)
	{
	}

	private void UpdateMayorEffect(IUserDataBase userData)
	{
	}

	private void UpdateNationName(string nationName)
	{
	}

	public void OnChangeKing()
	{
	}

	public void OnChangeFace()
	{
	}

	public static void Toggle(bool isVisible)
	{
	}

	public static void RefreshUI()
	{
	}

	public void OnBtnProfile()
	{
	}

	public void OnBtnUserInfo()
	{
	}
}
