using UnityEngine;

public abstract class tk2dUIBaseItemControl : MonoBehaviour
{
	public tk2dUIItem uiItem;

	public GameObject SendMessageTarget
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static void ChangeGameObjectActiveState(GameObject go, bool isActive)
	{
	}

	public static void ChangeGameObjectActiveStateWithNullCheck(GameObject go, bool isActive)
	{
	}

	protected void DoSendMessage(string methodName, object parameter)
	{
	}
}
