using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class UIThousandBattleTreasurePiece : MonoBehaviour
{
	public UILabelLocalized acquiredRewardCountLabel;

	public float animationTime;

	private int m_totalRewardCount;

	private int m_acquiredRewardCount;

	private void OnEnable()
	{
	}

	[DebuggerHidden]
	private IEnumerator AnimateRewardCount()
	{
		return null;
	}
}
