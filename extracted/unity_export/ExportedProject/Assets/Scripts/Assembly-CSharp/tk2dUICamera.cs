using UnityEngine;

public class tk2dUICamera : MonoBehaviour
{
	public enum tk2dRaycastType
	{
		Physics3D = 0,
		Physics2D = 1
	}

	[SerializeField]
	private LayerMask raycastLayerMask;

	[SerializeField]
	private tk2dRaycastType raycastType;

	public tk2dRaycastType RaycastType
	{
		get
		{
			return tk2dRaycastType.Physics3D;
		}
	}

	public LayerMask FilteredMask
	{
		get
		{
			return default(LayerMask);
		}
	}

	public Camera HostCamera
	{
		get
		{
			return null;
		}
	}

	public void AssignRaycastLayerMask(LayerMask mask)
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}
}
