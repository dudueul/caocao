using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class PopupStaffRoll : CaocaoUIModalPopup
{
	public Transform tr_Table;

	public GameObject go_BGM1;

	public GameObject go_BGM2;

	public PlayMakerFSM playMakerFsm;

	private bool m_init;

	private bool m_lastView;

	private float m_tablePosY;

	private float m_tableMaxPosY;

	public float m_moveSpeed;

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	public static GameObject Show()
	{
		return null;
	}

	public void ShowPanel(bool init)
	{
	}

	public void Closed()
	{
	}

	[DebuggerHidden]
	private IEnumerator OnTableMove()
	{
		return null;
	}

	private void Init(bool init = false)
	{
	}

	private void SetBGMActive()
	{
	}

	[DebuggerHidden]
	private IEnumerator BGM1Disable()
	{
		return null;
	}

	private bool ObjectCheck()
	{
		return false;
	}

	private void UpdateUI()
	{
	}
}
