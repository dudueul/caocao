using UnityEngine;

public class TooltipBody : MonoBehaviour
{
	private const int maxHeight = 395;

	public UIScrollView scrollView
	{
		get
		{
			return null;
		}
	}

	public UITable uiTable
	{
		get
		{
			return null;
		}
	}

	public int height
	{
		get
		{
			return 0;
		}
	}

	private IntVector2 tableSize
	{
		get
		{
			return default(IntVector2);
		}
	}

	public int GetMaxHeight()
	{
		return 0;
	}

	public void UpdatePosition()
	{
	}

	public void UpdatePanelSize()
	{
	}

	public void UpdatePanelSize(int fixedHeight)
	{
	}

	public TooltipComponent[] GetTooltipComponents()
	{
		return null;
	}
}
