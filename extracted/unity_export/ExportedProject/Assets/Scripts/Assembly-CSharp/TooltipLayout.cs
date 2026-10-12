using System.Collections.Generic;
using UnityEngine;

public class TooltipLayout : MonoBehaviour
{
	private UISprite m_bgSprite;

	public bool showBody;

	public bool showTail;

	public bool showCloseBtn;

	public bool showCloseColliderSprite;

	public bool forInPanel;

	public int bodyHeightInPanel;

	public Object headPrefab;

	public List<Object> bodyPrefabs;

	public Object tailPrefab;

	private bool repositionNow;

	public UISprite bgSprite
	{
		get
		{
			return null;
		}
	}

	public TooltipHead head
	{
		get
		{
			return null;
		}
	}

	public TooltipBody body
	{
		get
		{
			return null;
		}
	}

	public TooltipTail tail
	{
		get
		{
			return null;
		}
	}

	public UIButton closeBtn
	{
		get
		{
			return null;
		}
	}

	public UITable contents
	{
		get
		{
			return null;
		}
	}

	public Transform scrollBarTransf
	{
		get
		{
			return null;
		}
	}

	private void OnDestroy()
	{
	}

	public void Close()
	{
	}

	public void UpdatePosition()
	{
	}

	public void LateUpdate()
	{
	}

	private void SetTooltipCenterAlign(int tooltipHeight)
	{
	}
}
