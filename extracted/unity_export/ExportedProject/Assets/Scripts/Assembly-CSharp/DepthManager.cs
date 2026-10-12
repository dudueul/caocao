using System.Collections.Generic;
using UnityEngine;

public class DepthManager : SceneSingleton<DepthManager>
{
	public enum Grade
	{
		Normal = 500,
		Urgent = 1000
	}

	private Dictionary<Grade, List<GameObject>> m_dicAdjustObjs;

	private Color m_oneTimeCustomBgColor;

	private bool m_oneTimeCustomBgColorSettled;

	protected override void OnDestroy()
	{
	}

	public GameObject PushPopup(GameObject popup, IDepthManagerblePopup parent)
	{
		return null;
	}

	public void SetOneTimeCustomBgColor(Color color)
	{
	}

	public void OnTappingCloseCollider(GameObject popup)
	{
	}

	public void PopAllNormalPopups()
	{
	}

	public void PopPopup(GameObject willbePoped)
	{
	}

	private void FocusNextPopup(List<GameObject> popups)
	{
	}

	private bool __FocusNextPopup(List<GameObject> popups)
	{
		return false;
	}
}
