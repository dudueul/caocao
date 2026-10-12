using System;
using System.Collections.Generic;
using UnityEngine;

public class UIComponents : SceneSingleton<UIComponents>
{
	public enum UIStateByCF
	{
		ShowVictoryPanel = 0,
		EventOnGoing = 1,
		ShowTileInfo = 2,
		ShowOwnTroopInfo = 3,
		ShowUndoAction = 4,
		DoSomething = 5,
		NotOwnTurn = 6,
		Idle = 7,
		AIMode = 8,
		TurnStartDirection = 9,
		SelectUnit = 10,
		Moved = 11,
		Moving = 12,
		AttackOrPincer = 13,
		SelectArt = 14,
		ArtOrMultiArt = 15,
		UseItem = 16,
		ActionDone = 17,
		ReplayMode = 18,
		WatchMode = 19,
		ReplayModeShowUnitInfo = 20,
		WatchModeShowUnitInfo = 21,
		ReplayModeTurnDirection = 22,
		WatchModeTurnDirection = 23,
		ArenaDeckSetting = 24
	}

	private class UIStateByCFComparer : IEqualityComparer<UIStateByCF>
	{
		bool IEqualityComparer<UIStateByCF>.Equals(UIStateByCF x, UIStateByCF y)
		{
			return false;
		}

		int IEqualityComparer<UIStateByCF>.GetHashCode(UIStateByCF obj)
		{
			return 0;
		}
	}

	private List<UIGroups> m_uiGroups;

	protected Dictionary<string, GameObject> m_name2UIComponets;

	private bool isDirty;

	public List<GameObject> ms_buttons;

	private Dictionary<UIStateByCF, Dictionary<string, bool>> m_CFStatus2DefaultButtonStates;

	public Action OnUIStateChanged;

	private UIStateByCF m_lastUIState;

	private bool turnOffedAllUIs;

	private Action OnUpdateButtonStates__BackingField;

	// C# has no syntax for parameterized property 'Item'.
	public GameObject get_Item(string key)
	{
		return null;
	}

	public List<GameObject> buttons
	{
		get
		{
			return null;
		}
	}

	public UIStateByCF lastUIState
	{
		get
		{
			return UIStateByCF.ShowVictoryPanel;
		}
		set
		{
		}
	}

	private bool isUsedBtnUnitInfo
	{
		get
		{
			return false;
		}
	}

	public event Action OnUpdateButtonStates
	{
		add
		{
		}
		remove
		{
		}
	}

	protected UIComponents()
	{
	}

	private void Start()
	{
	}

	public void Register(GameObject uiComponent)
	{
	}

	public void Unregister(GameObject uiComponent)
	{
	}

	public bool ChangeKey(string oldKey, string newKey)
	{
		return false;
	}

	protected override void OnDestroy()
	{
	}

	public void SetVisible(string targetUIName, bool flag)
	{
	}

	private void SetVisible(GameObject target, bool flag)
	{
	}

	public void UpdateButtonStates()
	{
	}

	public void UpdateButtonStatesForClosePanel()
	{
	}

	public void UpdateButtonStatesByCFStatus(ControlFlow.statusType statusType)
	{
	}

	private void __UpdateButtonStatesByCFStatus(ControlFlow.statusType statusType, bool forceUpdate = false)
	{
	}

	private UIStateByCF GetUIStateByCFStatus(ControlFlow.statusType statusType)
	{
		return UIStateByCF.ShowVictoryPanel;
	}

	private Dictionary<string, bool> GetDefaultButtonStates(UIStateByCF UIState)
	{
		return null;
	}

	private void EventOnGoingDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void VictoryDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void TileInfoDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void NotOwnTurnDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void IdleDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void UndoActionDefaultbuttonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void AIModeDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void OwnTurnStartDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void SelectUnitDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void MovedDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void DoSomethingDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void MovingDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void AttackPincerDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void SelectArtificeDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void ArtificeDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void UseItemDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void ActionDoneDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void WatchReplayModeDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void ArenaDeckSettingButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	private void OwnTroopPanelDefaultButtonStates(ref Dictionary<string, bool> defaultButtonStates)
	{
	}

	public void TurnOffAllButtons()
	{
	}

	public bool IsExist(string uiName)
	{
		return false;
	}

	public bool IsActiveSelf(string uiName)
	{
		return false;
	}

	public bool IsActiveInHierarchy(string uiName)
	{
		return false;
	}

	private void UpdateMultiplayUIStates(UIStateByCF UIState)
	{
	}
}
