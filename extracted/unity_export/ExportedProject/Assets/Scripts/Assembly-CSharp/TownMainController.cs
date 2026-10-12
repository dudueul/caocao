using UnityEngine;

public class TownMainController : MonoBehaviour
{
	private const int maxShowSoldierCount = 3;

	public UILabelLocalized m_townName;

	private GameObject m_townMapObj;

	private GameObject m_buildingLabels;

	private GameObject m_townButtons;

	private GameObject m_profile;

	private GameObject m_townNameObj;

	private Town m_town;

	private UserTown m_myTown;

	public static TownMainController instance { get; private set; }

	public TownMap townMap { get; private set; }

	private void Awake()
	{
	}

	private void Start()
	{
	}

	public void Init()
	{
	}

	private void InitTown()
	{
	}

	private void InitTownBuildings()
	{
	}

	public void UpdateBuildingLabels()
	{
	}

	private void SetDefenseBtn(int townId)
	{
	}

	public void ShowBattleDeckUI(bool turnOn, bool forcing = false)
	{
	}

	public void UpgradeBuilding(EnumData.TownBuildingType buildingType)
	{
	}

	public void UpdateNextPrevTownName()
	{
	}

	public void ChangeTown(UserTown town)
	{
	}
}
