using System;
using System.Collections.Generic;
using UnityEngine;

public class PanelTabRuneCompose : MonoBehaviour
{
	private enum NoticeType
	{
		NeedMaterial = 0,
		NeedRuneStat = 1,
		InvalidMaterial = 2,
		InvalidRuneStat = 3,
		MaterialIsFull = 4
	}

	private class RuneInventory : IDisposable
	{
		private UIReuseGrid m_runeInven;

		private Transform m_btnRuneType;

		private Transform m_runeTypeList;

		private Comparison<RuneListItem.CellData> m_runeCellComparision;

		private Action<RuneListItem.CellData> m_onClickHandler;

		private Action<EnumData.RuneType> m_onLoadHandler;

		private EnumData.RuneType m_currentRuneType;

		private const int m_BlueDragonBoxId = 13040001;

		private const int m_VermilionBirdBoxId = 13040002;

		private const int m_WhiteTigerBoxId = 13040003;

		private const int m_BlackTortoiseBoxId = 13040004;

		public EnumData.RuneType CurrentRuneType
		{
			get
			{
				return EnumData.RuneType.BLUE;
			}
			private set
			{
			}
		}

		public bool ShowFilterList
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public RuneInventory(Transform transform, Comparison<RuneListItem.CellData> runeCellComparision = null)
		{
		}

		public void Dispose()
		{
		}

		public void LoadCategory(EnumData.RuneType runeType)
		{
		}

		public void Unload()
		{
		}

		private void OpenFilter()
		{
		}

		private void CloseFilter()
		{
		}

		private bool CheckEnableFilter(EnumData.RuneType runeType)
		{
			return false;
		}

		public void DisableSlots(bool disable, Func<int, bool> ignoreFunc = null)
		{
		}

		public void SetOnClickHandler(Action<RuneListItem.CellData> callback)
		{
		}

		public void SetOnLoadHandler(Action<EnumData.RuneType> callback)
		{
		}
	}

	private class MaterialSlots : IDisposable
	{
		private RuneListItem.CellData[] m_slotDatas;

		private Transform[] m_slots;

		private GameObject m_nextStepNotice;

		private Action<RuneListItem.CellData> m_onClickHandler;

		private Action<UserRuneItem> m_addMaterialCallback;

		private Action<UserRuneItem> m_removeMaterialCallback;

		public const int InvalidSlotIndex = -1;

		public const int MaxSlotCount = 3;

		public int Count
		{
			get
			{
				return 0;
			}
		}

		public bool IsFull
		{
			get
			{
				return false;
			}
		}

		public bool IsEmpty
		{
			get
			{
				return false;
			}
		}

		public MaterialSlots(Transform transform)
		{
		}

		public void Dispose()
		{
		}

		public void Initialize()
		{
		}

		public bool AddMaterial(int slotIndex, RuneListItem.CellData cellData)
		{
			return false;
		}

		public void RemoveMaterial(int slotIndex)
		{
		}

		public int GetSlotIndex(RuneListItem.CellData cellData)
		{
			return 0;
		}

		private int GetSlotCount(ulong runeItemSrl)
		{
			return 0;
		}

		private void RemoveAllMaterials()
		{
		}

		public int GetEmptySlot()
		{
			return 0;
		}

		public void SetRemoveMaterialHandler(Action<UserRuneItem> callback)
		{
		}

		public void SetAddMaterialHandler(Action<UserRuneItem> callback)
		{
		}

		public void SetOnClickHandler(Action<RuneListItem.CellData> callback)
		{
		}

		public List<ulong> GetMaterialSrls()
		{
			return null;
		}

		public void SetNextStepAnimation(bool enable)
		{
		}
	}

	private class StatSelector : IDisposable
	{
		private RuneUI[] m_slotDatas;

		private Transform[] m_targets;

		private Action<int> m_onClickHandler;

		private Action m_selectHandler;

		private Action m_deselectHandler;

		private Action m_onLoadedHandler;

		public const int MaxSlotCount = 7;

		public const int InvalidSelectIndex = -1;

		public Rune Selected
		{
			get
			{
				return null;
			}
		}

		public int SelectedIndex { get; private set; }

		public bool IsSelected
		{
			get
			{
				return false;
			}
		}

		// C# has no syntax for parameterized property 'Item'.
		public RuneUI get_Item(int slotIndex)
		{
			return null;
		}

		public StatSelector(Transform transform)
		{
		}

		public void Dispose()
		{
		}

		public void Initialize(EnumData.RuneType runeType)
		{
		}

		public void RemoveAllSlot()
		{
		}

		private Rune GetPdRuneData(EnumData.RuneType runeType, int runeTier, int mainStat)
		{
			return null;
		}

		public void Toggle(int slotIndex)
		{
		}

		private void Select(int slotIndex)
		{
		}

		private void Deselect(int slotIndex)
		{
		}

		public void EnableSlots(bool enable, Func<int, bool> ignoreFunc = null)
		{
		}

		public void SetOnLoadedHandler(Action callback)
		{
		}

		public void SetSelectedHandler(Action callback)
		{
		}

		public void SetDeselectHandler(Action callback)
		{
		}

		public void SetOnClickHandler(Action<int> callback)
		{
		}
	}

	private class RuneSelectTooltipController : ITooltipController
	{
		public enum Mode
		{
			Select = 0,
			Deselect = 1
		}

		private MaterialSlots m_materials;

		private Mode m_tooltipMode;

		private RuneListItem.CellData m_cellData;

		private UserRuneItem m_userRuneItem;

		public TooltipComponent.Type componentType
		{
			get
			{
				return TooltipComponent.Type.Item;
			}
		}

		public GameObject tooltipPrefab
		{
			get
			{
				return null;
			}
		}

		public RuneSelectTooltipController(MaterialSlots materials, RuneListItem.CellData cellData, Mode tooltipMode)
		{
		}

		public void Update(TooltipComponent comp, TooltipContentsGenerator generator)
		{
		}

		private void UpdateHead(TooltipRuneHead comp)
		{
		}

		private void UpdateStat(TooltipItemStat comp)
		{
		}

		private void UpdatePassive(TooltipRunePassive comp)
		{
		}

		private void UpdateExplain(TooltipExplanation comp)
		{
		}

		private void UpdateTail(TooltipTailRuneBtn comp)
		{
		}

		public void OnSelect(TooltipTailRuneBtn comp)
		{
		}

		public void OnDeselect(TooltipTailRuneBtn comp)
		{
		}
	}

	private UIButton m_btnCompose;

	private RuneInventory m_runeInven;

	private MaterialSlots m_materials;

	private StatSelector m_statSelector;

	private readonly Comparison<RuneListItem.CellData> m_runeComparison;

	private const int ComposePrice = 1000000;

	public bool EnableTab
	{
		get
		{
			return false;
		}
	}

	protected void Awake()
	{
	}

	protected void OnEnable()
	{
	}

	protected void OnDisable()
	{
	}

	protected void OnDestroy()
	{
	}

	private void AddMaterialCallback(UserRuneItem runeItem)
	{
	}

	private void RemoveMaterialCallback(UserRuneItem runeItem)
	{
	}

	private void UpdateComposeButton()
	{
	}

	private void ShowSelectTooltip(RuneListItem.CellData cellData)
	{
	}

	private void ShowDeselectTooltip(RuneListItem.CellData cellData)
	{
	}

	public void OnClickCompose()
	{
	}

	private HttpRequest.HttpRequestContext CreateComposeRuneContext(List<ulong> materials, int itemId)
	{
		return null;
	}

	private static void SendNotificationText(NoticeType noticeType)
	{
	}
}
