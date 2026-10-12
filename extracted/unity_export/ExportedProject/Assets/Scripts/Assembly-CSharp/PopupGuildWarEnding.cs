using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class PopupGuildWarEnding : CaocaoUIModalPopup
{
	private static PopupGuildWarEnding _self;

	[SerializeField]
	private UIScrollView LogScrollView;

	[SerializeField]
	private UITable LogTable;

	[SerializeField]
	private GameObject Info;

	[SerializeField]
	private string bgmName;

	[SerializeField]
	private bool isLoop;

	public float m_moveSpeed;

	public float m_startPosY;

	public float m_endPosY;

	private string lastBgmName;

	private float MoveHeight;

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	public static void Show(int seasonKey, ulong guildId)
	{
	}

	private void Init(int seasonKey, ulong guildId)
	{
	}

	[DebuggerHidden]
	private IEnumerator OnTableMove()
	{
		return null;
	}

	public void BGMClose()
	{
	}

	public void Closed()
	{
	}
}
