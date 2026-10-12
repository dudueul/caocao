using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

public class InstanceTabSpecial : MonoBehaviour
{
	protected class CategoryObject
	{
		private SpecialInstance m_specialInst;

		private DateTime m_timeLimit;

		private Transform m_selected;

		public int Id { get; set; }

		public UITexture TitleImage { get; private set; }

		public UILabelLocalized Title { get; private set; }

		public UILabelLocalized Desc { get; private set; }

		public UILabelLocalized Time { get; private set; }

		public Transform Obj { get; private set; }

		public CategoryObject(Transform obj)
		{
		}

		public void UpdateUI(SpecialInstance specialInst)
		{
		}

		private void CheckDesc()
		{
		}

		private int GetMinRank()
		{
			return 0;
		}

		private int GetRemainCount()
		{
			return 0;
		}

		public void CheckTime()
		{
		}

		public void SetSelected(bool selected)
		{
		}
	}

	public Transform stateTrans;

	[ObjectWeakRef]
	public string weakPrefab;

	[ObjectWeakRef]
	public string silverPrefab;

	[ObjectWeakRef]
	public string expPrefab;

	private static readonly int DAILY_INSTANCE_CATEGORY_ID;

	private PanelInstanceLobby m_parent;

	private List<InstanceSlot> m_slotList;

	private int m_selectedCategory;

	private bool m_showCategory;

	private DayOfWeek m_selectedDay;

	private InstanceSlot m_lastSlot;

	private UIScrollView m_scrollView;

	private Transform m_scrollBar;

	private List<CategoryObject> m_categoryObjList;

	private UIButton m_stateButtonBack;

	private UIButton m_stateButtonList;

	private GameObject m_stateWeek;

	private GameObject m_stateSilver;

	private GameObject m_stateExp;

	private UILabelLocalized m_stateName;

	private bool m_initialize;

	private GameObject stateWeek
	{
		get
		{
			return null;
		}
	}

	private GameObject stateSilver
	{
		get
		{
			return null;
		}
	}

	private GameObject stateExp
	{
		get
		{
			return null;
		}
	}

	public void Initialize(PanelInstanceLobby parent)
	{
	}

	private void OnDestroy()
	{
	}

	public bool BackbuttonClicked()
	{
		return false;
	}

	private void UpdateTimeInfo()
	{
	}

	private string GetLastClearedInstanceKey()
	{
		return null;
	}

	private int GetLastClearedInstance()
	{
		return 0;
	}

	private void SetLastClearedInstance(int instanceId)
	{
	}

	private void onStateAutoCleared(List<ObscuredInt> instanceIds)
	{
	}

	public void SetVisible(bool visible)
	{
	}

	private void ShowCategory()
	{
	}

	private void ShowState(int categoryId)
	{
	}

	public void UpdateUI()
	{
	}

	private void UpdateCategoryUI()
	{
	}

	private CategoryObject GetLastSelectedCategory()
	{
		return null;
	}

	private void UpdateStateUI(int categoryId)
	{
	}

	private void InstanceSlotClicked(InstanceSlot slot)
	{
	}

	private void InstanceEnterButtonClicked()
	{
	}

	private void StateButtonBackClicked()
	{
	}

	private void StateButtonListClicked()
	{
	}

	private void ListItemClicked(InstanceEventListItem listItem)
	{
	}

	private void CategoryClicked(CategoryObject categoryObject)
	{
	}

	private void CategoryEnterClicked()
	{
	}

	private void StateMoveLeftClicked()
	{
	}

	private void StateMoveRightClicked()
	{
	}

	private void OnDayChange(DayOfWeek day)
	{
	}

	public void ForceSelectInstance(int categoryId, DayOfWeek day, int instanceId)
	{
	}
}
