using UnityEngine;

public class UnitTreeZoom : MonoBehaviour
{
	public GameObject[] targetGameObj;

	private bool isStartTouched;

	private Vector3[] m_initScales;

	private float m_initDist;

	private float m_curDist;

	private float m_scale;

	private const string mouseScrollWheel = "Mouse ScrollWheel";

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void Update()
	{
	}
}
