using UnityEngine;

[ExecuteInEditMode]
public class PrefabInstance : MonoBehaviour
{
	[SerializeField]
	private string m_prefabPath;

	[SerializeField]
	private int m_depth;

	[SerializeField]
	private GameObject m_inst;

	[SerializeField]
	private Vector3 m_scale;

	[SerializeField]
	private Vector3 m_rotation;

	public string prefabPath
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int depth
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public GameObject inst
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	public Vector3 scale
	{
		get
		{
			return default(Vector3);
		}
		set
		{
		}
	}

	public Vector3 rotation
	{
		get
		{
			return default(Vector3);
		}
		set
		{
		}
	}

	private void OnDestroy()
	{
	}
}
