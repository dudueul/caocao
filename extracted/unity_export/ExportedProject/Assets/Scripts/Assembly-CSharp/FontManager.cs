using UnityEngine;

public class FontManager : Singleton<FontManager>
{
	private UIFont m_uiFontNara;

	private UIFont m_uiFontTaebaek;

	public Font fontNara { get; set; }

	public Font fontTaebaek { get; set; }

	public void SetSmallFont()
	{
	}

	public void SetFont()
	{
	}

	public void SetTextMeshFont(GameObject prefab)
	{
	}

	protected override void OnDestroy()
	{
	}
}
