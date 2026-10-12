using System;
using System.Collections.Generic;
using UnityEngine;

public class InstanceTabNormal : MonoBehaviour
{
	[Serializable]
	public class StatePosition
	{
		public int stateId;

		public Vector3 pos;
	}

	public List<StatePosition> statePositions;

	public Transform stateTrans;

	private static InstanceTabNormal self;

	private PanelInstanceLobby m_parent;

	private List<InstanceSlot> m_slotList;

	private int m_curStateId;

	private InstanceSlot m_lastSlot;

	private UILabelLocalized m_stateName;

	private UILabelLocalized m_stateLevel;

	private UIButton m_stateButtonList;

	private bool m_initialize;

	private tk2dSprite m_eventLobbyState;

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	private void SetEventLobbyState(int stateId)
	{
	}

	public void Initialize(PanelInstanceLobby parent)
	{
	}

	public bool BackbuttonClicked()
	{
		return false;
	}

	public void SetVisible(bool visible)
	{
	}

	private void SetVisibleExpeditionButton(bool enable)
	{
	}

	public void UpdateUI()
	{
	}

	public void InstanceSlotClicked(InstanceSlot slot)
	{
	}

	private void InstanceEnterButtonClicked()
	{
	}

	public void StateMoveLeftClicked()
	{
	}

	public void StateMoveRightClicked()
	{
	}

	public void OnStateChanged(int newSelected)
	{
	}

	private int GetPrevStateId(int stateId)
	{
		return 0;
	}

	private int GetNextStateId(int stateId)
	{
		return 0;
	}

	private void ListButtonClicked()
	{
	}

	private void ListItemClicked(InstanceEventListItem listItem)
	{
	}

	private void SequenceButtonClicked()
	{
	}

	private void ExpeditionButtonClicked()
	{
	}

	public void ForceSelectInstance(int stateId, int instanceId)
	{
	}

	public static void ContinueSequantial()
	{
	}

	public void StartSequantial()
	{
	}

	private void Continue()
	{
	}

	private UserInstance getAutoInstanceMoveStateUserInstance()
	{
		return null;
	}

	private void ShowBattleDeck(int instId)
	{
	}

	private void CheckEnergy()
	{
	}

	private void StartWaitingForRecoveringEnergy()
	{
	}

	private void ClearAutoProcessTimer()
	{
	}

	public static void HoldRecoveringEnergyTimer(bool hold)
	{
	}

	public static void CallRecoveringEnergyTimer()
	{
	}
}
