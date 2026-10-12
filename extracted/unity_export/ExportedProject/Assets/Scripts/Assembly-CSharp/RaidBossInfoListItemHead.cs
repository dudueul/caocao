using UnityEngine;

public class RaidBossInfoListItemHead : MonoBehaviour
{
	[SerializeField]
	private UITexture m_unitFaceTexture;

	[SerializeField]
	private GameObject m_unitNormalFrame;

	[SerializeField]
	private GameObject m_unitSpecialFrame;

	[SerializeField]
	private UILabelLocalized m_unitName;

	[SerializeField]
	private UILabelLocalized m_unitDesc;

	public void Initialize(int unitId)
	{
	}
}
