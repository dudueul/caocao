using UnityEngine;

public class FxLifeTime : MonoBehaviour
{
	[SerializeField]
	private float m_lifeTime;

	[SerializeField]
	private bool m_useLifeTime;

	[SerializeField]
	private float m_fadeOutTime;

	public float lifeTime
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public bool useLifeTime
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public float fadeOutTime
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}
}
