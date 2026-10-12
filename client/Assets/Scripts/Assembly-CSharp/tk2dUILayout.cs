using System;
using System.Collections.Generic;
using UnityEngine;

public class tk2dUILayout : MonoBehaviour
{
	public Vector3 bMin;

	public Vector3 bMax;

	public List<tk2dUILayoutItem> layoutItems;

	public bool autoResizeCollider;

	private Action<Vector3, Vector3> OnReshape__BackingField;

	public int ItemCount
	{
		get
		{
			return 0;
		}
	}

	public event Action<Vector3, Vector3> OnReshape
	{
		add
		{
		}
		remove
		{
		}
	}

	private void Reset()
	{
	}

	public virtual void Reshape(Vector3 dMin, Vector3 dMax, bool updateChildren)
	{
	}

	public void SetBounds(Vector3 pMin, Vector3 pMax)
	{
	}

	public Vector3 GetMinBounds()
	{
		return default(Vector3);
	}

	public Vector3 GetMaxBounds()
	{
		return default(Vector3);
	}

	public void Refresh()
	{
	}
}
