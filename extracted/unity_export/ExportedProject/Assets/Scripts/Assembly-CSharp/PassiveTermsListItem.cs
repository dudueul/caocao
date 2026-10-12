using UnityEngine;

public class PassiveTermsListItem : MonoBehaviour
{
	[SerializeField]
	private UILabelLocalized m_needLevelTitle;

	[SerializeField]
	private UILabelLocalized m_needJobLevelTitle;

	[SerializeField]
	private UILabelLocalized m_needLevel;

	[SerializeField]
	private UISprite[] m_starIcon;

	private Vector3 m_boxCenter;

	private Vector3 m_boxSize;

	public static PassiveTermsListItem AddListItemTo(Transform grid)
	{
		return null;
	}

	private void Init()
	{
	}

	public Vector3 UpdateUI(int currLv, int needLv, int currJobLv, int needJobLv, Vector3 localPos)
	{
		return default(Vector3);
	}
}
