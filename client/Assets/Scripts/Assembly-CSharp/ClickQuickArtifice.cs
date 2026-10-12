using UnityEngine;

public class ClickQuickArtifice : CaocaoUIButton
{
	[SerializeField]
	private SkillUI m_skillUI;

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnClick(SkillUI skillUI)
	{
	}

	private void FailToUseSkill(string msg)
	{
	}

	private bool HasSkill()
	{
		return false;
	}

	protected override void OnClickImpl()
	{
	}

	protected override void UpdateButtonState()
	{
	}
}
