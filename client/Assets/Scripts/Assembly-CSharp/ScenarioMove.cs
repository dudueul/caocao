using System.Collections;
using System.Diagnostics;

public class ScenarioMove : Move
{
	private static float ms_moveStepTime;

	private UnitAnimation unitAnim;

	private UnitController unitCtrl;

	private MoveParam[] moveParams;

	private int currentOnGoingIdx;

	private MoveParam currentParams;

	public bool isPlaying { get; private set; }

	public bool isSucceded { get; private set; }

	public void StartAnimation(IntVector2 toGridPos)
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate(IntVector2 toGridPos)
	{
		return null;
	}

	private void GatherMoveStep(int startIdx)
	{
	}

	private void MoveStep(MoveParam startParmas, MoveParam endParams)
	{
	}

	private void UnitMoveStepStarted(MoveParam parameters)
	{
	}

	private void UnitMoveStepUpdated(float value)
	{
	}

	private void UnitMoveStepCompleted(MoveParam parameters)
	{
	}

	private void UnitMoveCompleted()
	{
	}

	private void __UnitMoveCompleted()
	{
	}
}
