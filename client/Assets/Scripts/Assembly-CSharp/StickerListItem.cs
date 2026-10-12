using System;
using UnityEngine;

public class StickerListItem : MonoBehaviour
{
	[SerializeField]
	private UITexture m_stickerTexture;

	[SerializeField]
	private UIButton m_btn;

	private int m_stickerId;

	private Action<int> m_onClick;

	public void Init(bool enable, int stickerId, string icon, Action<int> onClick)
	{
	}

	public void OnStickerClick()
	{
	}
}
