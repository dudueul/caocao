using System;
using UnityEngine;

public class ChatStickerListItem : MonoBehaviour
{
	[SerializeField]
	private UITexture m_stickerTexture;

	[SerializeField]
	private UIButton m_closeBtn;

	[HideInInspector]
	public int m_stickerId;

	private Action<int> m_onClick;

	public void Init(int stickerId, string icon, string name, Action<int> onClick)
	{
	}

	public void OnStickerClick()
	{
	}
}
