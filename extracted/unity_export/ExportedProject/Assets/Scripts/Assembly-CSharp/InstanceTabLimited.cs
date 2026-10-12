using System.Collections.Generic;
using UnityEngine;

public class InstanceTabLimited : MonoBehaviour
{
	public Transform stateTrans;

	[ObjectWeakRef]
	public string limitPrefab;

	private PanelInstanceLobby m_parent;

	private List<InstanceSlot> m_slotList;

	private InstanceSlot m_lastSlot;

	private bool m_initialize;

	private GameObject m_stateLimit;

	private GameObject stateLimit
	{
		get
		{
			return null;
		}
	}

	private void OnDestroy()
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

	public void UpdateUI()
	{
	}

	private void InstanceSlotClicked(InstanceSlot slot)
	{
	}

	private void InstanceEnterButtonClicked()
	{
	}

	public void ForceSelectInstance(int instanceId)
	{
	}
}
