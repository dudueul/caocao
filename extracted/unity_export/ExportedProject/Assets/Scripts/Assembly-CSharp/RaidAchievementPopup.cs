using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class RaidAchievementPopup : CaocaoUIModalPopup
{
	private class Info
	{
		private int m_total;

		private int m_finished;

		private List<UserAchievement> m_userAchievements;

		public int Total
		{
			get
			{
				return 0;
			}
		}

		public int Finished
		{
			get
			{
				return 0;
			}
		}

		public List<UserAchievement> Achievements
		{
			get
			{
				return null;
			}
		}

		public Info(EnumData.RaidType raidType)
		{
		}

		public void Add(UserAchievement achievement)
		{
		}

		public void Clear()
		{
		}
	}

	[Serializable]
	private class Container
	{
		[SerializeField]
		private CaocaoButton m_button;

		[SerializeField]
		private UILabelLocalized m_state;

		public void Update(int m_finished, int m_total)
		{
		}
	}

	[SerializeField]
	private CaocaoTabButtons m_tab;

	[SerializeField]
	private Container m_blueDragonContainer;

	[SerializeField]
	private Container m_vermilionBirdContainer;

	[SerializeField]
	private Container m_blackTortoiseContainer;

	[SerializeField]
	private Container m_whiteTigerContainer;

	[SerializeField]
	private UIReuseScrollView m_scrollView;

	[SerializeField]
	private UIReuseGrid m_grid;

	private bool m_initialized;

	private EnumData.RaidType m_curType;

	private readonly Dictionary<EnumData.RaidType, Info> m_infoMap;

	public static void Show(EnumData.RaidType raidType)
	{
	}

	private void Initialize()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	private void UpdateInfo()
	{
	}

	private void UpdateState()
	{
	}

	private void UpdateGrid(bool resetScroll = true)
	{
	}

	private void UpdateComplete(Achievement achievement, JsonData response, bool treasure)
	{
	}

	private void ShowComplete(Achievement achievement, List<RewardInfo> rewardInfos, bool treasure)
	{
	}

	private void OnCompleteClose()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
