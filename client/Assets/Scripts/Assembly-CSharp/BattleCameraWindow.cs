using UnityEngine;

public class BattleCameraWindow : MonoBehaviour
{
	public int cameraBorderOffset;

	public float camBorder2WindowOffset
	{
		get
		{
			return 0f;
		}
	}

	public bool need2ActivateCameraWindow
	{
		get
		{
			return false;
		}
	}

	private void LateUpdate()
	{
	}
}
