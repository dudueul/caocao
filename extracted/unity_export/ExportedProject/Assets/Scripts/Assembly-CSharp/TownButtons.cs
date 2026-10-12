using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class TownButtons : MonoBehaviour
{
	private static TownButtons self;

	public GameObject m_BtnTotalFood;

	public GameObject m_BtnTotalSilver;

	public GameObject m_BtnMoveCapital;

	public GameObject m_BtnUpRight;

	public GameObject m_leftBtn;

	public GameObject m_rightBtn;

	private UserTown prev;

	private UserTown next;

	private void Awake()
	{
	}

	private void Start()
	{
	}

	private void OnDestroy()
	{
	}

	public void UpdateButtonsUI()
	{
	}

	public void GoBack()
	{
	}

	public void SetDefenseDeck()
	{
	}

	public void UpgradeTotalFood()
	{
	}

	public void UpgradeTotalSilver()
	{
	}

	private void UpdateTotalFoodAndSilverLevel(GameObject obj, int level, bool show)
	{
	}

	public static void UpdateTotalFoodAndSilverLabel(EnumData.TownBuildingType buildingType)
	{
	}

	[DebuggerHidden]
	private IEnumerator ChangeScene()
	{
		return null;
	}

	public void GoRecon()
	{
	}

	public void ShowSoldierUI()
	{
	}

	public void ShowTownList()
	{
	}

	public void PrevTown()
	{
	}

	public void NextTown()
	{
	}

	public void ChangeTown(int townId)
	{
	}

	public void ShowTownInfoList()
	{
	}

	public void OnClickTownManagement()
	{
	}
}
