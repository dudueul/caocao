using UnityEngine;

public class UIMultiplaySeasonStat : MonoBehaviour
{
	[SerializeField]
	private UITexture tierIcon;

	[SerializeField]
	private UILabelLocalized tierValue;

	[SerializeField]
	private UILabelLocalized scoreLabel;

	[SerializeField]
	private UILabelLocalized statLabel;

	[SerializeField]
	private UILabelLocalized rankLabel;

	[SerializeField]
	private GameObject dim;

	public void UpdateUI(int userRank, int score, int win, int lose, int rank, Tier tier)
	{
	}
}
