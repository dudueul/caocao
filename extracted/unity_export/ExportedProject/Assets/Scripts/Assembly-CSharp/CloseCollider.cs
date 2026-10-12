using UnityEngine;

public class CloseCollider : MonoBehaviour
{
	public enum CloseMethod
	{
		Deactivate = 0,
		Destroy = 1,
		SendMessage = 2
	}

	public GameObject m_target;

	public string m_action;

	public CloseMethod m_closeMethod;

	public static void AddTo(GameObject popup, Transform backPanel, CloseMethod closeMethod)
	{
	}

	private void OnClick()
	{
	}
}
