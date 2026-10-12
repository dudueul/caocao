using UnityEngine;

public class ActionGuideController : MonoBehaviour
{
	[SerializeField]
	private GameObject actionGuide;

	[SerializeField]
	private GameObject actionMessage;

	[SerializeField]
	private GameObject fire;

	[SerializeField]
	private GameObject water;

	[SerializeField]
	private GameObject wind;

	[SerializeField]
	private GameObject earth;

	[SerializeField]
	private GameObject normal;

	private UILabelLocalized actionGuideLabelCtx;

	private UILabelLocalized fireLabelCtx;

	private UILabelLocalized waterLabelCtx;

	private UILabelLocalized windLabelCtx;

	private UILabelLocalized earthLabelCtx;

	private UILabelLocalized normalLabelCtx;

	private StringDefines pdStringDefines;

	private void Start()
	{
	}

	private void OnDestroy()
	{
	}

	private void SetActiveFalse()
	{
	}

	private void SetActiveTrue()
	{
	}

	public void TurnOffActionGuide()
	{
	}

	public void TurnOnActionGuide()
	{
	}

	public void TurnOffActionMessage()
	{
	}

	public void TurnOnActionMessage()
	{
	}

	public void TurnOffActionMessageAll()
	{
	}

	public void UpdateMessageByType(EnumData.ActionMessageType actionGuideSkillType, string label)
	{
	}

	public void UpdateGuideByActionType(EnumData.ActionGuideType actionGuideType)
	{
	}

	public void UpdateActionGuideMessage(ControlFlow.statusType statusType)
	{
	}

	private void UpdateGuideMessageByControlSkillId()
	{
	}

	private void UpdateGuideMessageByControlItemId()
	{
	}
}
