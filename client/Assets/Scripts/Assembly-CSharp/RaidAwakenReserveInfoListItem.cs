using System;
using UnityEngine;

public class RaidAwakenReserveInfoListItem : MonoBehaviour
{
	private UILabelLocalized m_label;

	private UIButton m_btnDelete;

	private Action<ulong> m_callback;

	private ulong m_srl;

	private void Awake()
	{
	}

	public void UpdateUI(UserRaidAwakenReserve info, Action<ulong> callback)
	{
	}

	private void OnDeleteClicked()
	{
	}
}
