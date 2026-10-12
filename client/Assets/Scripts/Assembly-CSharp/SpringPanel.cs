using UnityEngine;

public class SpringPanel : MonoBehaviour
{
	public delegate void OnFinished();

	public static SpringPanel current;

	public Vector3 target;

	public float strength;

	public OnFinished onFinished;

	private UIPanel mPanel;

	private Transform mTrans;

	private UIScrollView mDrag;

	private void Start()
	{
	}

	private void Update()
	{
	}

	protected virtual void AdvanceTowardsPosition()
	{
	}

	public static SpringPanel Begin(GameObject go, Vector3 pos, float strength)
	{
		return null;
	}
}
