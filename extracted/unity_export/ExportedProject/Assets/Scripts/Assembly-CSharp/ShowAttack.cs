using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using Caocao;
using UnityEngine;

public class ShowAttack : MonoBehaviour
{
	private bool m_playing;

	private bool m_isCompleted;

	private UnitData m_targetUnitData;

	private List<UnitData> m_targetUnitDatas;

	public bool isPlaying
	{
		get
		{
			return false;
		}
	}

	public bool isCompleted
	{
		get
		{
			return false;
		}
	}

	public List<UnitData> targetUnitDatas
	{
		get
		{
			return null;
		}
	}

	public void StartAnimation(UnitData targetUnitData, AnimationType animationType, bool isCriticalAttackOccured, bool isDoubleAttack)
	{
	}

	public void StartAnimation(List<UnitData> targetUnitDatas, AnimationType animationType, bool isCriticalAttackOccured, bool isDoubleAttack)
	{
	}

	public void StartAnimation(AnimationType animationType, bool isCriticalAttackOccured, bool isDoubleAttack)
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate(bool isCriticalAttackOccured, bool isDoubleAttack, AnimationType animationType)
	{
		return null;
	}
}
