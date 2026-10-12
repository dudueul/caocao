using System.Runtime.InteropServices;
using UnityEngine;

public abstract class DuelActionBase : MonoBehaviour
{
	[StructLayout((LayoutKind)0, Size = 16)]
	public struct MoveParam
	{
		public Vector2 toWorldPos;

		public int currentStep;

		public bool isLastStep;
	}

	protected EnumData.DuelAction m_action;

	protected bool m_isCompleted;

	protected bool m_isPlaying;

	protected float m_animationTime;

	protected bool m_playSound;

	public bool isCompleted
	{
		get
		{
			return false;
		}
	}

	public bool isPlaying
	{
		get
		{
			return false;
		}
	}

	public float animationTime
	{
		get
		{
			return 0f;
		}
	}

	public bool playSound
	{
		get
		{
			return false;
		}
	}

	public abstract void SetData(EnumData.DuelAction action, params string[] args);

	public abstract void StartAnimation();
}
