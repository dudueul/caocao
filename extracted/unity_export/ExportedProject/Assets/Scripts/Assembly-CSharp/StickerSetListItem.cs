using System;
using UnityEngine;

public class StickerSetListItem : MonoBehaviour
{
	[SerializeField]
	private UITexture m_stickerTexture;

	[SerializeField]
	private GameObject m_selectGo;

	private int m_stickerSetId;

	private Action<int> m_onClick;

	public int stickerSetId
	{
		get
		{
			return 0;
		}
	}

	public void Init(int stickerSetId, Action<int> onClick, string thumbnail)
	{
	}

	public void OnStickerSetClick()
	{
	}

	public void Select(int selectedStickerSetId)
	{
	}
}
