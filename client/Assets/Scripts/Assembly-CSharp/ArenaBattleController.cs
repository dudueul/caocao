using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using Caocao;
using Eppy;
using UnityEngine;

public class ArenaBattleController : CaocaoUIPanel
{
	private class ArrangeUnitInfo
	{
		public UserUnit userUnit;

		public UserArenaUnit userArenaUnit;

		public int order;

		public int x;

		public int y;

		public bool arranged
		{
			get
			{
				return false;
			}
		}

		public ArrangeUnitInfo(UserArenaUnit userArenaUnit)
		{
		}

		public ArrangeUnitInfo(UserUnit userUnit, int order)
		{
		}
	}

	private enum ArrangeMode
	{
		Arrange = 0,
		Order = 1
	}

	private static Color MPCOLOR;

	private static Color EPCOLOR;

	private static string MPICON;

	private static string EPICON;

	private static Color OWN_COLOR;

	private static Color ENEMY_COLOR;

	private static Color TILE_GRAY_COLOR;

	[SerializeField]
	private GameObject objDetail;

	[SerializeField]
	private UILabelLocalized labelOrder;

	[SerializeField]
	private UISprite spriteOrderBg;

	[SerializeField]
	private UILabelLocalized labelName;

	[SerializeField]
	private UILabelLocalized labelJobType;

	[SerializeField]
	private UILabelLocalized labelHp;

	[SerializeField]
	private UILabelLocalized labelMp;

	[SerializeField]
	private UISprite spriteMp;

	[SerializeField]
	private UISprite spriteMpIcon;

	[SerializeField]
	private PrefabInstance prefabInstWeapon;

	[SerializeField]
	private PrefabInstance prefabInstArmor;

	[SerializeField]
	private PrefabInstance prefabInstKit;

	[SerializeField]
	private UIGrid gridPassive;

	[SerializeField]
	private UILabelLocalized labelTileName;

	[SerializeField]
	private UILabelLocalized labelTileVal;

	[SerializeField]
	private CaocaoTabButtons tabButton;

	[SerializeField]
	private UIGrid gridFaces;

	[SerializeField]
	private ConditionStatusPopup popupPassive;

	[SerializeField]
	private UnitAbilityPopup popupUnitAbility;

	[SerializeField]
	private UIButton btnRefresh;

	[SerializeField]
	private UIButton btnAutoArrange;

	[SerializeField]
	private UILabelLocalized labelDesc;

	private int selectedIdx;

	private List<ArrangeUnitInfo> ownArrangeInfo;

	private List<ArrangeUnitInfo> enemyArrangeInfo;

	private ArrangeMode arrangeMode;

	private bool enemyMode;

	private Dictionary<SpActionBase.Origin, int> passiveWeights;

	private bool started;

	private bool initiated;

	private List<SpActionBase> tempPassives;

	private Dictionary<string, Tuple<EnumData.spAction, int>> passiveIds;

	private Dictionary<int, List<ReconSlot>> lines;

	private int topLine;

	private int bottomLine;

	private int leftLine;

	private GameObject slotCanvas;

	private List<ArrangeUnitInfo> arrangeInfo
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

	private void InitUI()
	{
	}

	private void InitUnits()
	{
	}

	private void SetEnemyMode(bool enemyMode)
	{
	}

	private void UpdateFaces()
	{
	}

	private void UpdateSlotCanvasDepth()
	{
	}

	private void SelectUnit(int idx, bool toggle = true)
	{
	}

	private void UpdateUI()
	{
	}

	private ArrangeUnitInfo GetArrangeInfoByY(int y)
	{
		return null;
	}

	private ArrangeUnitInfo GetArrangeInfoByY(List<ArrangeUnitInfo> arrangeInfo, int y)
	{
		return null;
	}

	private SlotInfo GetSlotInfo(int bfIndex)
	{
		return null;
	}

	private SlotInfo GetSlotInfo(int x, int y)
	{
		return null;
	}

	private int GetArrangeIndex(int x, int y)
	{
		return 0;
	}

	private UserUnit GetLineUnit(int yPos)
	{
		return null;
	}

	private int GetArrangeIndex(UserUnit unit)
	{
		return 0;
	}

	private void UpdateTile()
	{
	}

	private void UpdateTopMessage()
	{
	}

	[DebuggerHidden]
	private IEnumerator InitTile()
	{
		return null;
	}

	private void InitSynergy(List<SpActionBase> storage)
	{
	}

	private void ApplyGlobalPassive(UserUnit unit, UnitSide unitSide, ArenaStage pdStage, List<SpActionBase> storage)
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitForShowMoveSelection(UnitData unitData)
	{
		return null;
	}

	private void DeployUnit(int arrangeIdx, SlotInfo slotInfo, bool updateUI = true, bool selectUnit = false)
	{
	}

	private void OnClickReconSlot(Vector2 gridPos, ReconSlot reconSlot)
	{
	}

	public void OnFaceButton()
	{
	}

	private void SetOrder(int idx, bool updateUI = true)
	{
	}

	private void OnTabChanged(CaocaoButton btn)
	{
	}

	public void OnRefreshButton()
	{
	}

	public void OnAutoArrangeButton()
	{
	}

	private void UpdateOrderLabel()
	{
	}

	public void OnGoBattleButton()
	{
	}

	private void StartBattle()
	{
	}

	private void ApplyPassiveEffect()
	{
	}

	private void OnControllingUnitChanged()
	{
	}

	public void OnUnitInfoButton()
	{
	}

	public void OnPassiveInfoButton()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	protected override void OnDestroy()
	{
	}
}
