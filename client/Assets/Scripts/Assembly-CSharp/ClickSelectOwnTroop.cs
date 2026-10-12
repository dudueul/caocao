using System;
using UnityEngine;

public class ClickSelectOwnTroop : MonoBehaviour
{
	[SfxWeakRef]
	public string successClickAudioClip;

	[SfxWeakRef]
	public string failClickAudioClip;

	private AudioClip m_successClickAudioClip;

	private AudioClip m_failClickAudioClip;

	private Func<UnitDataDevolved, object, bool> m_clickHandler;

	private object m_arg;

	private UnitDataDevolved m_unitData;

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	public void SetClickHandler(Func<UnitDataDevolved, object, bool> clickHandler, object arg)
	{
	}

	private void OnClick()
	{
	}
}
