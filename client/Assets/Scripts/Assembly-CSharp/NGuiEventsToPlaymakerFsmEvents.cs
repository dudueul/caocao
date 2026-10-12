using System.Collections.Generic;
using UnityEngine;

public class NGuiEventsToPlaymakerFsmEvents : MonoBehaviour
{
	public bool debug;

	public bool OnlyShowImplemented;

	public static UICamera.MouseOrTouch currentTouch;

	public PlayMakerFSM targetFSM;

	public List<NGuiPlayMakerDelegates> customEventsKeys;

	public List<string> customEventsValues;

	private int[] _usage;

	private UIInput _input;

	private UIProgressBar _pBar;

	private UIToggle _toggle;

	public int getUsage(NGuiPlayMakerDelegates fsmEventDelegate)
	{
		return 0;
	}

	private void OnEnable()
	{
	}

	public bool DoesTargetMissEventImplementation(PlayMakerFSM fsm, NGuiPlayMakerDelegates fsmEventDelegate)
	{
		return false;
	}

	public bool DoesTargetMissEventImplementation(PlayMakerFSM fsm, string fsmEvent)
	{
		return false;
	}

	public bool DoesTargetImplementsEvent(PlayMakerFSM fsm, NGuiPlayMakerDelegates fsmEventDelegate)
	{
		return false;
	}

	public bool DoesTargetImplementsEvent(PlayMakerFSM fsm, string fsmEvent)
	{
		return false;
	}

	private void FireNGUIPlayMakerEvent(NGuiPlayMakerDelegates _event)
	{
	}

	private void OnClick()
	{
	}

	private void OnHover(bool isOver)
	{
	}

	private void OnPress(bool pressed)
	{
	}

	private void OnSelect(bool selected)
	{
	}

	private void OnDrag(Vector2 delta)
	{
	}

	private void OnDrop(GameObject go)
	{
	}

	private void OnTooltip(bool show)
	{
	}

	private void OnSubmitChange()
	{
	}

	private void OnSliderChange(float value)
	{
	}

	private void OnSelectionChange(string item)
	{
	}

	private void OnChange()
	{
	}

	public void SetCurrentSelection()
	{
	}
}
