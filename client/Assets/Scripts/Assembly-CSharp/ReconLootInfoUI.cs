using UnityEngine;

public class ReconLootInfoUI : MonoBehaviour
{
	[SerializeField]
	private UISprite m_icon;

	[SerializeField]
	private UILabelLocalized m_value;

	public void Init(bool isGoods, int typeId, int lootValue, int bonus = 0)
	{
	}
}
