using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class DuelDialogBox : MonoBehaviour
{
	private PopUpDialog[] m_popupDialog;

	public const float fadeTime = 0.15f;

	private const float m_finalDelay = 1f;

	public bool IsUpdateCompleted(bool isOurUnit)
	{
		return false;
	}

	private void Start()
	{
	}

	public void ShowDialogBox(bool isOurUnit, int unitId, int faceId, string dialog, int faceType, EnumData.DialogType dialogType)
	{
	}

	private void Update()
	{
	}

	private void OnClick()
	{
	}

	public void HideAllPopupDialog()
	{
	}

	[DebuggerHidden]
	public IEnumerator HidePopupDialog(bool isOurUnit, float interval)
	{
		return null;
	}
}
