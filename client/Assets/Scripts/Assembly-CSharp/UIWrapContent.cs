using System.Collections.Generic;
using UnityEngine;

public class UIWrapContent : MonoBehaviour
{
	public delegate void OnInitializeItem(GameObject go, int wrapIndex, int realIndex);

	public int itemSize;

	public bool cullContent;

	public int minIndex;

	public int maxIndex;

	public OnInitializeItem onInitializeItem;

	private Transform mTrans;

	private UIPanel mPanel;

	private UIScrollView mScroll;

	private bool mHorizontal;

	private bool mFirstTime;

	private List<Transform> mChildren;

	protected virtual void Start()
	{
	}

	protected virtual void OnMove(UIPanel panel)
	{
	}

	public void SortBasedOnScrollMovement()
	{
	}

	public void SortAlphabetically()
	{
	}

	protected bool CacheScrollView()
	{
		return false;
	}

	private void ResetChildPositions()
	{
	}

	public void WrapContent()
	{
	}

	private void OnValidate()
	{
	}

	protected virtual void UpdateItem(Transform item, int index)
	{
	}
}
