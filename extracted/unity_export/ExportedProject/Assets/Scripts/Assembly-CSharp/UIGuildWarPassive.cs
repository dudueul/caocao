using UnityEngine;

public class UIGuildWarPassive : MonoBehaviour
{
	[SerializeField]
	private UISprite m_iconSprite;

	[SerializeField]
	private UILabelLocalized m_nameLabel;

	[SerializeField]
	private UILabelLocalized m_descLabel;

	private GuildWarPassive m_passive;

	private int m_passiveValue;

	private GuildWarPassiveAttribute m_passiveAttribute;

	public GuildWarPassive passive
	{
		get
		{
			return null;
		}
	}

	public int passiveValue
	{
		get
		{
			return 0;
		}
	}

	public void SetPassive(UIGuildWarPassiveData uIGuildWarPassiveData)
	{
	}

	private void UpdateIconUI()
	{
	}

	private void UpdateNameLabelUI()
	{
	}

	private void UpdateDescLabelUI()
	{
	}

	public void UpdateUI()
	{
	}
}
