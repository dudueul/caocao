using UnityEngine;

public class UIGuildWarPassiveListGridItem : MonoBehaviour
{
	[SerializeField]
	private GameObject selectGameObject;

	private bool m_selected;

	private GuildWarPassiveList m_pPassiveList;

	public bool selected
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private void Start()
	{
	}

	private void Update()
	{
	}

	public void UpdateUI(GuildWarPassiveList p_passiveList)
	{
	}
}
