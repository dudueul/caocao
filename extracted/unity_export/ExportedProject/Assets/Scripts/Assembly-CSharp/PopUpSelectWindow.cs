using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

internal class PopUpSelectWindow : MonoBehaviour
{
	public UITexture m_unitFaceTex;

	public GameObject m_SelectGridItemPrefab;

	private const float m_fadeTimeInterval = 0.1f;

	public static PopUpSelectWindow CreateInstance(int unitID, int faceId, List<string> selections, int saveFlagID, string question, Action<int, bool> onSelect)
	{
		return null;
	}

	private void ShowSelectWindow(int unitID, int faceId, List<string> lSelectList, int saveFlagID, string question, Action<int, bool> onSelect)
	{
	}

	private void CreateGridItem(UIGrid grid, string splited, int savedFlagValue, int id, Action<int, bool> onSelect)
	{
	}

	private void Close()
	{
	}

	[DebuggerHidden]
	private IEnumerator ClosePopupWindow(float waitingTime)
	{
		return null;
	}
}
