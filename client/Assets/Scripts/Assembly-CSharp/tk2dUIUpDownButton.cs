using UnityEngine;

public class tk2dUIUpDownButton : tk2dUIBaseItemControl
{
	public GameObject upStateGO;

	public GameObject downStateGO;

	[SerializeField]
	private bool useOnReleaseInsteadOfOnUp;

	private bool isDown;

	public bool UseOnReleaseInsteadOfOnUp
	{
		get
		{
			return false;
		}
	}

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void ButtonUp()
	{
	}

	private void ButtonDown()
	{
	}

	private void SetState()
	{
	}

	public void InternalSetUseOnReleaseInsteadOfOnUp(bool state)
	{
	}
}
