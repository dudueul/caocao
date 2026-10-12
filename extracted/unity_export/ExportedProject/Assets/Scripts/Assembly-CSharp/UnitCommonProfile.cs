using System.Collections.Generic;
using UnityEngine;

public class UnitCommonProfile : MonoBehaviour
{
	private List<UISprite> m_starUIs;

	private List<GameObject> m_starEffObjs;

	private string m_spriteN;

	private string m_spriteS;

	public int unitId { get; private set; }

	private void Destroy()
	{
	}

	private void OnInit()
	{
	}

	public void UpdateUI(UserUnit curUnit, bool showStar = true, bool showCost = true, bool showLevel = true)
	{
	}

	public void UpdateUIByUnitID(int unitID, bool showStar = true, bool showCost = true, bool showLevel = true)
	{
	}

	public void UpdateUIByUnitIDFromMine(int unitID)
	{
	}

	public void UpdateUIBySoldier(UserUnit userUnit)
	{
	}

	private void _UpdateUI(UserUnit curUnit, bool showStar = true, bool showCost = true, bool showLevel = true)
	{
	}
}
