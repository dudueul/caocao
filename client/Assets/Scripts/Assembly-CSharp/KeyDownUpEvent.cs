using System;

public class KeyDownUpEvent : IKeyEventData
{
	private KeyBindId m_bindId;

	private Action m_callback;

	private int m_state;

	public KeyBindId BindId
	{
		get
		{
			return KeyBindId.NONE;
		}
	}

	public KeyBindData BindKey
	{
		get
		{
			return null;
		}
	}

	public KeyDownUpEvent(KeyBindId binderId, Action callback)
	{
	}

	public void UpdateState()
	{
	}
}
