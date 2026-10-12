using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class StickerManager : SceneSingleton<StickerManager>
{
	private Dictionary<string, BattleCommunicationUI> m_stickerUis;

	private Dictionary<string, Coroutine> m_coroutines;

	private Queue<DateTime> m_emitTime;

	private float m_blockTime;

	private void Awake()
	{
	}

	public void AddStickerUi(string player, BattleCommunicationUI stickerUi)
	{
	}

	public void ShowSticker(string player, int stickerId)
	{
	}

	public void OnStickerCallback(ulong uid, int stickerId)
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowSticker(BattleCommunicationUI stickerUi, int stickerId)
	{
		return null;
	}

	public void EmitSticker(int stickerId)
	{
	}
}
