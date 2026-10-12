using UnityEngine;

public class UserRankingUI : MonoBehaviour
{
	public UILabelLocalized nationName;

	public UITexture unitFace;

	public UILabelLocalized rankScore;

	public UILabelLocalized rankNum;

	public GameObject highlight;

	public Texture defaultTexture;

	public void SetActive(bool active)
	{
	}

	public void SetHighLight(bool active)
	{
	}

	public void InitUI()
	{
	}

	public void UpdateUI(int rank, string name, ulong score, int faceUnitId, int skinItemId)
	{
	}
}
