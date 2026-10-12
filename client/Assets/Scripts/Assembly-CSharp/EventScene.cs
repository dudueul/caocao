using System;

[Serializable]
public class EventScene : EventCmdBase
{
	public static readonly string ms_code;

	private bool m_isClosed;

	public bool isClosed
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public override string ToString()
	{
		return null;
	}

	public static void ApplyOrRevertSceneClose(int index, bool closed)
	{
	}
}
