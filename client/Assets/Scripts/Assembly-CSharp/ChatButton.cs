using System;
using Chat;
using UnityEngine;

public class ChatButton : MonoBehaviour
{
	[SerializeField]
	private GameObject m_objNewDot;

	[SerializeField]
	private GameObject m_objOff;

	[SerializeField]
	private ChatOneLine m_msgAllChat;

	[SerializeField]
	private ChatOneLine m_msgNormalChat;

	[SerializeField]
	private ChatOneLine m_msgGuildChat;

	[SerializeField]
	private ChatOneLine m_msgWhisperChat;

	private bool try2Connect;

	private ChattingMessage.ChattingMessageType m_visibleType;

	protected virtual void Awake()
	{
	}

	protected virtual void OnDestroy()
	{
	}

	protected virtual void OnEnable()
	{
	}

	protected virtual void OnDisable()
	{
	}

	protected void OnClick()
	{
	}

	private void OnClickImpl()
	{
	}

	public void OnNotification(object sender, EventArgs e)
	{
	}

	public void OnMessage(object sender, EventArgs e)
	{
	}

	private void SetOneLineChat(ChattingMessage msg)
	{
	}

	private void OnDisconnected(object sender, EventArgs e)
	{
	}

	private void OnConncected(object sender, EventArgs e)
	{
	}

	private void UpdateBtnStatus()
	{
	}

	private void AddEventHandler()
	{
	}

	private void RemoveEventHandler()
	{
	}

	private void OneLineChatClose()
	{
	}

	private void EnableMsgObj(ChattingMessage.ChattingMessageType type)
	{
	}

	private string GetOneLineMsg(ChattingMessage msg)
	{
		return null;
	}

	private bool VisibleOrderType(ChattingMessage.ChattingMessageType inType)
	{
		return false;
	}
}
