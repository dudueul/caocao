using System;
using UnityEngine;

public abstract class tk2dUILayoutContainer : tk2dUILayout
{
	protected Vector2 innerSize;

	private Action OnChangeContent__BackingField;

	public event Action OnChangeContent
	{
		add
		{
		}
		remove
		{
		}
	}

	public Vector2 GetInnerSize()
	{
		return default(Vector2);
	}

	protected abstract void DoChildLayout();

	public override void Reshape(Vector3 dMin, Vector3 dMax, bool updateChildren)
	{
	}

	public void AddLayout(tk2dUILayout layout, tk2dUILayoutItem item)
	{
	}

	public void AddLayoutAtIndex(tk2dUILayout layout, tk2dUILayoutItem item, int index)
	{
	}

	public void RemoveLayout(tk2dUILayout layout)
	{
	}
}
