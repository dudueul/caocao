using UnityEngine;

[ExecuteInEditMode]
public class UIViewport : MonoBehaviour
{
	public Camera sourceCamera;

	public Transform topLeft;

	public Transform bottomRight;

	public float fullSize;

	private Camera mCam;

	private void Start()
	{
	}

	private void LateUpdate()
	{
	}
}
