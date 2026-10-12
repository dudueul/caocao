using UnityEngine;

public class PopupSkillList : CaocaoUIModalPopup
{
	private SkillUI m_selectedSkill;

	public UILabelLocalized m_useSkillBtnLabel;

	public UserUnit userUnit { get; private set; }

	public UnitData unitData { get; private set; }

	public static void Show(UserUnit userUnit, UnitData unitData)
	{
	}

	public static void Show()
	{
	}

	public static void Close()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
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

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void OnSkillItemClicked(SkillUI skill)
	{
	}

	private void OnSkillItemDoubleClicked(SkillUI skill)
	{
	}

	public void ClickUseSkill()
	{
	}

	private void UseSkill(EnumData.Skills skillId)
	{
	}

	private bool HasEnoughMp(EnumData.Skills skillId)
	{
		return false;
	}

	private void UpdateUI()
	{
	}

	private SkillUI CreateGridItem(Transform gridTransform, EnumData.Skills unitSkill, bool available)
	{
		return null;
	}

	private void FailToUseSkill(string msg)
	{
	}
}
