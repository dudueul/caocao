using System;

public class FSMQueueElement
{
	private static int srlbase;

	public string stateName;

	public Action callback;

	public bool ignoreTrigger;

	public float postDelay;

	private bool m_isComplete;

	public int srl { get; private set; }

	public bool isComplete
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public FSMQueueElement(string stateName, Action callback, bool ignoreTrigger, float postDelay)
	{
	}

	public override string ToString()
	{
		return null;
	}
}
