using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class UITutorial : MonoBehaviour, IPointerClickHandler, IEventSystemHandler
{
	public enum Action
	{
		HideGameObject = 0,
		DisableCollider = 1,
		ExcuteScriptOnClose = 2,
		ExcuteScriptOnClick = 3,
		AttatchButtonEffect = 4,
		DimmingButton = 5,
		HelpScript = 6
	}

	[SerializeField]
	private List<UITutorialInfo> m_infos;

	[SerializeField]
	private List<UITutorialInfo> m_appliedInfos;

	public void AddInfo(bool enable, TutorialUI tutorialUI)
	{
	}

	public void AddInfo(UITutorialInfo info)
	{
	}

	private void OnEnable()
	{
	}

	public void CheckOnEnable()
	{
	}

	private void CheckOnEnable(UITutorialInfo info)
	{
	}

	public bool WillHidePopup()
	{
		return false;
	}

	private bool WillHidePopup(UITutorialInfo info)
	{
		return false;
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	public void CheckOnDisable()
	{
	}

	private void CheckOnDisable(UITutorialInfo info)
	{
	}

	private bool IsObjectExistAndActive(string name)
	{
		return false;
	}

	public void OnClick()
	{
	}

	public void OnPointerClick(PointerEventData eventData)
	{
	}

	private void CheckOnClick(UITutorialInfo info)
	{
	}

	private void HideObject(UITutorialInfo info)
	{
	}

	private void SetColliderDisabled(UITutorialInfo info)
	{
	}

	private void DimmingButton(UITutorialInfo info)
	{
	}

	private void ExcuteScript(UITutorialInfo info)
	{
	}

	private void ExcuteHelpScript(UITutorialInfo info)
	{
	}

	private void LoadHelpAndRun(string script)
	{
	}

	public void Refresh()
	{
	}

	private void CheckApplied()
	{
	}

	private void Revert(UITutorialInfo info)
	{
	}
}
