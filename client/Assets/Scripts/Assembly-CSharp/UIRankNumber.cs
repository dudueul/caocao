using UnityEngine;

public class UIRankNumber : MonoBehaviour
{
	[SerializeField]
	private GameObject[] rankObjs;

	[SerializeField]
	private UILabelLocalized[] rankNums;

	public void UpdateUI(int rank, float rankPct)
	{
	}

	private void SetRankString(int rank, float rankPct, UILabelLocalized label)
	{
	}
}
