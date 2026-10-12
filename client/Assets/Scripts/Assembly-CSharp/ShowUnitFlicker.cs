using UnityEngine;

public class ShowUnitFlicker : MonoBehaviour
{
	private const float minAlpha = 0f;

	private const float maxAlpha = 127f / 255f;

	private float m_time;

	private float originShaderAlpha;

	private int m_maxLoopCount;

	private int m_currentLoopCnt;

	private UnitAnimation unitAnimation
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool StartAnimation(float time, int maxLoopCount)
	{
		return false;
	}

	private void OnFlickerStarted()
	{
	}

	private void OnFlickerUpdated(float value)
	{
	}

	private void OnFlickerCompleted()
	{
	}

	private void OnDestroy()
	{
	}
}
