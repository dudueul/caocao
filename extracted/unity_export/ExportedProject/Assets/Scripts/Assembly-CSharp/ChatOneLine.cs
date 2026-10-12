using System;
using Chat;
using UnityEngine;

public class ChatOneLine : MonoBehaviour
{
	private const int MaxCharCount = 40;

	private float m_visibleTime;

	private float m_duration;

	[SerializeField]
	private UILabelLocalized m_msg;

	public Action onClose { get; set; }

	public void SetMsg(string msg, ChattingMessage.ChattingMessageType msgType)
	{
	}

	public void SetMsgColor(string msg, ChattingMessage.ChattingMessageType msgType)
	{
	}

	private void Update()
	{
	}

	private void Close()
	{
	}

	private void OnClick()
	{
	}

	private string GetMsgEdit(string msg)
	{
		return null;
	}
}
