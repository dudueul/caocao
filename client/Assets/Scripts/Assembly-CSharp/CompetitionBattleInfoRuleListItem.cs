using UnityEngine;

public class CompetitionBattleInfoRuleListItem : MonoBehaviour
{
	[SerializeField]
	private UITexture m_iconTexture;

	[SerializeField]
	private UILabelLocalized m_nameLabel;

	[SerializeField]
	private UILabelLocalized m_descLabel;

	private GameModeRule m_defaultGameModeRule;

	private GameModeRule m_gameModeRule;

	public void SetBattleMode(EnumData.BattleMode battleMode)
	{
	}

	private void Awake()
	{
	}

	public void SetPassiveInfo(Passive passive)
	{
	}

	public void SetGameModeRule(GameModeRuleItem gameModeRuleItem)
	{
	}

	private string GetDesc(GameModeRuleItem gameModeRuleItem)
	{
		return null;
	}
}
