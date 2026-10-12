using UnityEngine;

public class PanelTreasureEnhanceDetail : ScriptableObject
{
	protected class ResultInfo
	{
		private Transform m_parent;

		private UILabelLocalized m_enhanceState;

		private UILabelLocalized m_rate;

		private UILabelLocalized m_atk;

		private UILabelLocalized m_def;

		private UILabelLocalized m_int;

		private UILabelLocalized m_agi;

		private UILabelLocalized m_mor;

		private UILabelLocalized m_mov;

		private UILabelLocalized m_growPassive;

		private EnhancePassiveUI m_growPassiveUI;

		private int m_growPassiveId;

		public void Initialize(Transform parent)
		{
		}

		public void Clear()
		{
		}

		public void OnDestroy()
		{
		}

		public bool IsVisible()
		{
			return false;
		}

		public void SetVisible(bool visible)
		{
		}

		public void SetEnhanceState(int increaseLevel = 1)
		{
		}

		public void SetRate(int value)
		{
		}

		public void SetStat(TroopStat baseStat, TroopStat changedStat)
		{
		}

		private void SetLabel(EnumData.StatType stat, int value, int delta)
		{
		}

		public void SetGrowPassive(UserItem userItem, int levelDelta)
		{
		}

		private void GrowPassiveClicked()
		{
		}
	}

	private Transform m_parent;

	private ResultInfo m_fail;

	private ResultInfo m_succeed;

	private ResultInfo m_keep;

	private bool m_initialized;

	public void Initialize(Transform parent)
	{
	}

	public void UpdateUI(UserItem userItem, bool useKeeper = false, bool useGuarantee = false, int fixedEnhanceLevel = 0)
	{
	}

	public void OnDestroy()
	{
	}
}
