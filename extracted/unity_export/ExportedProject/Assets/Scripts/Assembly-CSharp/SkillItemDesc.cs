using UnityEngine;

public class SkillItemDesc : MonoBehaviour
{
	private GameObject m_skillLIstObj;

	private UILabelLocalized m_skillName;

	private UILabelLocalized m_limitLv;

	private UILabelLocalized m_AttrName;

	private UILabelLocalized m_resCostTitle;

	private UILabelLocalized m_resCost;

	private UI2DSprite m_hitRegionIcon;

	private UI2DSprite m_effectRangeIcon;

	private UILabelLocalized m_longDesc;

	private UIButton m_btnUseSkill;

	private UILabelLocalized m_useSkillLabel;

	private UISprite m_useSkillColor;

	private UILabelLocalized m_effectRangeTitle;

	private Color m_colorGrayLabel;

	private Color m_colorGrayBg;

	private Color m_colorOriginalBg;

	private bool m_init;

	private void OnDestroy()
	{
	}

	private void Start()
	{
	}

	private void InitUI()
	{
	}

	public void OnSkillItemClicked(SkillUI skill)
	{
	}

	private string GetEffectRange(Skill pdSkill)
	{
		return null;
	}
}
