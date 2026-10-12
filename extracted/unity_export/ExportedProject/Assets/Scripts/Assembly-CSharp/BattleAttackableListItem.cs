using UnityEngine;

public class BattleAttackableListItem : MonoBehaviour
{
	[SerializeField]
	private UILabelLocalized mainText;

	[SerializeField]
	private UILabelLocalized rankText;

	[SerializeField]
	private UILabelLocalized nationText;

	private UserTown userTown;

	private UserInfo m_userInfo;

	private BattleField battleFild;

	private const string stateTown = "{0} / {1}";

	private const string ownerRank = "Rank {0}";

	public void UpdateUI(UserTown borderTown)
	{
	}

	public void UpdateUI(BattleField battleFieldData)
	{
	}

	private void UpdateOwnerInfo(UserInfo userInfo)
	{
	}

	private void UpdateOwnerInfo()
	{
	}

	private void OnClick()
	{
	}

	private void OnDestroy()
	{
	}
}
