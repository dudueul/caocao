using UnityEngine;

public abstract class PageBase : MonoBehaviour
{
	public string pageName;

	protected abstract void ShowPageImpl();

	public void ShowPage()
	{
	}

	protected abstract void ClosePageImpl();

	public void ClosePage()
	{
	}
}
