using System.Collections.Generic;
using UnityEngine;

public class WorldbossDamageRewardInfo : SelfRegister2UIComponentsMonobehaviour
{
	[SerializeField]
	private UILabelLocalized m_tierLabel;

	[SerializeField]
	private UILabelLocalized m_damageLabel;

	[SerializeField]
	private RewardItemUI m_rewardUI;

	private ulong m_maxDamage;

	private List<WorldBossDmgReward> m_pdList;

	private bool m_initialized;

	public void Initialize(int worldbossId, ulong maxDamage)
	{
	}

	public int SortFunc(WorldBossDmgReward x, WorldBossDmgReward y)
	{
		return 0;
	}

	public void UpdateUI(ulong damage)
	{
	}
}
