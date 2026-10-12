using UnityEngine;

public class RaidBossInfoListItemBody : MonoBehaviour
{
	[SerializeField]
	private PrefabInstance m_passiveInst;

	[SerializeField]
	private PrefabInstance m_skillInst;

	[SerializeField]
	private UILabelLocalized m_nameLabel;

	[SerializeField]
	private UILabelLocalized m_descLabel;

	private PassiveUI m_passiveUI;

	private SkillUI m_skillUI;

	private PassiveUI passiveUI
	{
		get
		{
			return null;
		}
	}

	private SkillUI skillUI
	{
		get
		{
			return null;
		}
	}

	public void Initialize(int pdId, string desc)
	{
	}

	private void SetSkill(int pdId, string desc)
	{
	}

	private void SetPassive(int pdId, string desc)
	{
	}

	private void SetDefault(string name, string desc)
	{
	}

	private void OnDestroy()
	{
	}
}
