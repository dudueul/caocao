using UnityEngine;

public class RenderQueueModifier : MonoBehaviour
{
	public enum RenderType
	{
		FRONT = 0,
		BACK = 1
	}

	public UIWidget target;

	public RenderType renderType;

	public string targetLayer;

	private Renderer[] _renderers;

	private int _lastQueue;

	private void Start()
	{
	}

	private void FixedUpdate()
	{
	}
}
