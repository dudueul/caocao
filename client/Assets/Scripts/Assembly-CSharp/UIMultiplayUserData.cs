using Multiplay;
using UnityEngine;

public class UIMultiplayUserData : MonoBehaviour
{
	[SerializeField]
	private UITexture face;

	[SerializeField]
	private UITexture tierGrade;

	[SerializeField]
	private UILabelLocalized point;

	[SerializeField]
	private UILabelLocalized nationName;

	[SerializeField]
	private UILabelLocalized guildName;

	private MultiplayUserData m_userData;

	private int m_tierGrade;

	private int m_point;

	public void SetData(MultiplayUserData userData, int tierGrade, int point)
	{
	}

	private void UpdateUI()
	{
	}
}
