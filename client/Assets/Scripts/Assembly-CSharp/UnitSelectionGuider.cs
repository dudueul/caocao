using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class UnitSelectionGuider : SceneSingleton<UnitSelectionGuider>
{
	private float m_startedTime;

	private bool m_isChecking;

	private GameObject unit2focus;

	public Coroutine guidingCoroutine;

	private void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void CheckUnitSelectionGuiderState()
	{
	}

	public void Reset()
	{
	}

	public void StopCheckTime()
	{
	}

	public void StartCheckTime()
	{
	}

	private void FixedUpdate()
	{
	}

	[DebuggerHidden]
	private IEnumerator StartGuide()
	{
		return null;
	}

	private GameObject FindOwnOneUnit()
	{
		return null;
	}

	private void MoveCamera2FocusUnit(GameObject unit)
	{
	}

	private void FlickerUnit(GameObject unit)
	{
	}
}
