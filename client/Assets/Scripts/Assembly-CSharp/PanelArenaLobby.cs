using System.Collections.Generic;
using Caocao;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

public class PanelArenaLobby : PanelSelfRegister2UIComponentsMonobehaviour
{
	public const string popupName = "PanelArenaLobby";

	[SerializeField]
	private UILabelLocalized stageName;

	[SerializeField]
	private UITexture stageImage;

	[SerializeField]
	private UILabelLocalized battleMapName;

	[SerializeField]
	private MissionInfoUI missionDesc;

	[SerializeField]
	private UIGrid weathers;

	[SerializeField]
	private UIGrid passiveList;

	[SerializeField]
	private UIGrid faces;

	[SerializeField]
	private UIGrid deckFaces;

	[SerializeField]
	private UITexture boxImage;

	[SerializeField]
	private UILabelLocalized boxName;

	[SerializeField]
	private GameObject btnShop;

	[SerializeField]
	private PopupArenaScenario arenaScenarioPopup;

	private ArenaLevel pdArenaLevel;

	private ArenaStage pdStage;

	private ArenaNPCFormation pdNpcFormation;

	private UserArena userArena;

	private UserArenaLevel userArenaLevel;

	private List<UserUnit> npcUnits;

	private List<SpActionBase> tempPassives;

	private static bool endIntro;

	private bool showResult;

	private bool showClosedByAdmin;

	private bool endArena;

	private bool openSupply;

	private bool openScenario;

	private bool opened;

	private List<EnumData.MISSION_STATUS> missionStates;

	protected override void Awake()
	{
	}

	public static void Show(bool waitIntro)
	{
	}

	public static void OnEndIntro()
	{
	}

	private void OnDeserializeArena()
	{
	}

	private void UpdateShopButton()
	{
	}

	private void ShowResult()
	{
	}

	private void ShowClosedByAdmin()
	{
	}

	private void GotoIntro()
	{
	}

	public override void ShowPanel()
	{
	}

	private void OnDisable()
	{
	}

	private void StartOpenEffect()
	{
	}

	private void MakeSpawnInfos()
	{
	}

	public override void ClosePanel()
	{
	}

	private void UpdateMyDeckUI()
	{
	}

	private void UpdateUI()
	{
	}

	private List<EnumData.MISSION_STATUS> GetMissionStates(List<ObscuredInt> missions, int unitLevel)
	{
		return null;
	}

	public void OnBackButton()
	{
	}

	public void OnFaceButton()
	{
	}

	public void OnDeckSettingButton()
	{
	}

	private void UpdatePickInfo(bool prepareBattleMap)
	{
	}

	public void OnMyInfoButton()
	{
	}

	public void OnBattleInfoButton()
	{
	}

	public void OnMatchInfoButton()
	{
	}

	public void OnGiveUpButton()
	{
	}

	public void OnGoBattleButton()
	{
	}

	public void OnCommandButton()
	{
	}

	public void OnShopButton()
	{
	}

	private void GotoWorldMap()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void ApplyGlobalPassive(UserUnit unit, UnitSide unitSide, ArenaStage pdStage, List<SpActionBase> storage)
	{
	}
}
