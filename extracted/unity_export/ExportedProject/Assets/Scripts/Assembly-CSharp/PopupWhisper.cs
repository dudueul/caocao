using System;
using Chat;
using LitJson;
using UnityEngine;

public class PopupWhisper : MonoBehaviour
{
	private const float WidthAdjustValue = 265f;

	private const float HeightAdjustValue = 190f;

	[SerializeField]
	private GameObject m_popupWindow;

	[SerializeField]
	private UILabelLocalized m_name;

	private string m_targetUserId;

	private int m_targetMsgIndex;

	private IDepthManagerblePopup parentPopup;

	public Action<string> onBtnClick { get; set; }

	public Action onBlockBtnClick { get; set; }

	public void InitUI(string name, string uid, int msgIndex, IDepthManagerblePopup parent)
	{
	}

	private void AdjustWindowPos()
	{
	}

	public void OnBtnClick()
	{
	}

	public void OnBtnBlock()
	{
	}

	public void OnBtnUserInfo()
	{
	}

	private void RequestBlock()
	{
	}

	private void OnChatBlockRecieved(JsonData response)
	{
	}

	private string GetBlockMsg(ChattingMessage chatMsg)
	{
		return null;
	}
}
