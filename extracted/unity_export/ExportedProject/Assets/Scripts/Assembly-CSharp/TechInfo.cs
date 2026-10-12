using System;
using UnityEngine;

public class TechInfo : MonoBehaviour
{
	private Tech m_tech;

	private int m_level;

	private const float intervalX = 120f;

	private const float intervalY = 117f;

	private const float offsetX = 380f;

	private const float offsetY = 376f;

	public Action<Tech, float> OnClickTechInfo;

	public Action OnNewTagUIChanged;

	public Tech tech
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public void OnClickTechItem()
	{
	}

	public void UpdateGridUI()
	{
	}

	public void UpdateIconUI()
	{
	}

	private void UpdateIcon()
	{
	}

	private void UpdateLevel()
	{
	}

	private void UpdateLock(out bool locked)
	{
		locked = default(bool);
	}

	private void UpdateNewTag()
	{
	}

	private void UpdateArrow(bool isGridItem)
	{
	}
}
