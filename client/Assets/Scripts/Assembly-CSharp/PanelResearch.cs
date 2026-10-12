using System;
using UnityEngine;

public class PanelResearch : PanelSelfRegister2UIComponentsMonobehaviour
{
	public enum ResearchTab
	{
		Battle = 0,
		Administration = 1,
		Etc = 2
	}

	private ResearchTab m_currentTab;

	private UIScrollView m_scrollView;

	public Action OnResearchUIChanged;

	public ResearchTab currentTab
	{
		get
		{
			return ResearchTab.Battle;
		}
		set
		{
		}
	}

	private UIScrollView scrollView
	{
		get
		{
			return null;
		}
	}

	private void OnEnable()
	{
	}

	public void Show()
	{
	}

	public void OnClickTab1()
	{
	}

	public void OnClickTab2()
	{
	}

	public void OnClickTab3()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateGridUI()
	{
	}

	private void UpdateTabUI()
	{
	}

	private void ShowTooltip(Tech tech, float posX)
	{
	}

	private void CloseTooltip()
	{
	}

	private void SetTab(string path, string message, Color color)
	{
	}

	private void SetUILabel(Transform tf, string message)
	{
	}

	private void SetUIColor(Transform tf, Color color)
	{
	}

	private void SetRearrangTechList(UIGrid grid)
	{
	}

	private void SetTechGrid(Tech tech, UIGrid grid)
	{
	}

	private void SetTechProgress(int total, int cur)
	{
	}

	private void RepositionGrid()
	{
	}

	private UIGrid GetGrid()
	{
		return null;
	}

	private void ClearGrid(UIGrid grid)
	{
	}

	public void ShortCutSpecificTech(int techId)
	{
	}
}
