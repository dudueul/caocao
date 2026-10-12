using System.Collections.Generic;
using System.Text;
using UnityEngine;

public class tk2dEventHandler : MonoBehaviour
{
	private static StringBuilder ms_sb;

	protected string m_camShakePrefix;

	protected string m_camMovePrefix;

	protected string m_camZoomPrefix;

	protected string m_camTimeePrefix;

	protected string m_cachedItweenName;

	protected Dictionary<string, GameObject> m_tweenName2Target;

	protected string m_ObjID;

	protected int m_sequenceNum;

	private tk2dCustomTrigger m_overrideTrigger;

	private string m_tweenNameLastCompleted;

	private static int ms_timeScaleDirectingCnt;

	private static float ms_prevTimeScale;

	private int m_timeScaleLocalDirectingCnt;

	private static int ms_zoomDirectingCnt;

	private static float ms_prevZoom;

	private int m_zoomLocalDirectingCnt;

	private static int ms_camPosDirectingCnt;

	private int m_camPosLocalDirectingCnt;

	protected float directingTimeScale
	{
		set
		{
		}
	}

	public bool isUnderDirectingTimeScale
	{
		get
		{
			return false;
		}
	}

	protected float directingZoom
	{
		set
		{
		}
	}

	public bool isUnderDirectingZoom
	{
		get
		{
			return false;
		}
	}

	protected Vector3 directingCamPos
	{
		set
		{
		}
	}

	public bool isUnderDirectingCamPos
	{
		get
		{
			return false;
		}
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnAnimationCompleted(tk2dSpriteAnimator animator, tk2dSpriteAnimationClip clip)
	{
	}

	private void OnDestroy()
	{
	}

	private void OnAnimationEventTriggered(tk2dSpriteAnimator animator, tk2dSpriteAnimationClip clip, int currentFrameIdx)
	{
	}

	private void OnUpdateCameraZoom(float theSize)
	{
	}

	private void OnItweenCompleted(itDic hash)
	{
	}

	private void ResetEffects(tk2dSpriteAnimator animator, tk2dSpriteAnimationClip clip)
	{
	}

	public void EndDirectingTimeScale()
	{
	}

	public void EndDirectingZoom()
	{
	}

	public void EndDirectingCamPos()
	{
	}
}
