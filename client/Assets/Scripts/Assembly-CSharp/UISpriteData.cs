using System;

[Serializable]
public class UISpriteData
{
	public string name;

	public int x;

	public int y;

	public int width;

	public int height;

	public int borderLeft;

	public int borderRight;

	public int borderTop;

	public int borderBottom;

	public int paddingLeft;

	public int paddingRight;

	public int paddingTop;

	public int paddingBottom;

	public bool hasBorder
	{
		get
		{
			return false;
		}
	}

	public bool hasPadding
	{
		get
		{
			return false;
		}
	}

	public void SetRect(int x, int y, int width, int height)
	{
	}

	public void SetPadding(int left, int bottom, int right, int top)
	{
	}

	public void SetBorder(int left, int bottom, int right, int top)
	{
	}

	public void CopyFrom(UISpriteData sd)
	{
	}

	public void CopyBorderFrom(UISpriteData sd)
	{
	}
}
