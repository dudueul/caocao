using System;
using UnityEngine;

public class PopupShareBattle : CaocaoUIModalPopup
{
	private const int LIMIT_MINUTE = 5;

	private const int LIMIT_NUM = 3;

	private const string PREFS_KEY_TIME_FORMAT = "SHARE_TIME_{0}_{1}";

	private const string PREFS_KEY_IDX_FORMAT = "SHARE_IDX_{0}";

	[SerializeField]
	private UIButton btnGuild;

	private string playerName1;

	private string playerName2;

	private string battleKey;

	private ulong hostUid;

	private EnumData.MultiplayShareMode shareMode;

	private string whisperNationName;

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

	public static void Show(string playerName1, string playerName2, string battleKey, ulong hostUid, EnumData.MultiplayShareMode shareMode)
	{
	}

	private int GetShareTermIndex()
	{
		return 0;
	}

	private bool CheckShareTerm()
	{
		return false;
	}

	private void WriteShareTerm()
	{
	}

	public void OnBtnGuildChat()
	{
	}

	public void OnBtnWhisper()
	{
	}

	private void OnWhisperResponded(object sender, EventArgs e)
	{
	}

	private void OnWhisperNotFoundResponded(object sender, EventArgs e)
	{
	}

	protected override void OnDestroy()
	{
	}
}
