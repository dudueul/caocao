using UnityEngine;

public class SpringPosition : MonoBehaviour
{
	public delegate void OnFinished();

	public static SpringPosition current;

	public Vector3 target;

	public float strength;

	public bool worldSpace;

	public bool ignoreTimeScale;

	public bool updateScrollView;

	public OnFinished onFinished;

	[SerializeField]
	[HideInInspector]
	private GameObject eventReceiver;

	[SerializeField]
	[HideInInspector]
	public string callWhenFinished;

	private Transform mTrans;

	private float mThreshold;

	private UIScrollView mSv;

	private void Start()
	{
	}

	private void Update()
	{
	}

	private void NotifyListeners()
	{
	}

	public static SpringPosition Begin(GameObject go, Vector3 pos, float strength)
	{
		return null;
	}
}
