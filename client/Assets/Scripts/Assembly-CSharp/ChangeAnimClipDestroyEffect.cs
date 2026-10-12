using System.Collections.Generic;
using Caocao;
using UnityEngine;

public class ChangeAnimClipDestroyEffect : MonoBehaviour
{
	[SerializeField]
	private List<string> m_changeClipNames;

	[SerializeField]
	private UnitSide m_changeUnitSide;

	private string m_initClipName;

	private tk2dSpriteAnimator m_parentAnimator;

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnAnimationClipChanged(tk2dSpriteAnimator animator, tk2dSpriteAnimationClip clip)
	{
	}

	private void DestroyEffect()
	{
	}

	private void AddEventHandler()
	{
	}

	private void RemoveEventHandler()
	{
	}

	private bool ContainsClipName(string clipName)
	{
		return false;
	}
}
