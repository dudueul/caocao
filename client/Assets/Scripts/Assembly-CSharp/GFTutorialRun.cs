public class GFTutorialRun : GameFlowNodeBase
{
	private readonly int startTutorialId;

	public int currentId { get; set; }

	protected override void DefineFollowers()
	{
	}

	protected override int? _GetState()
	{
		return null;
	}

	protected override void _DoLogic()
	{
	}

	private void DoTutorial(TutorialFlow flow)
	{
	}

	private void LoadScenario(TutorialFlow flow)
	{
	}

	private void LoadBattle(TutorialFlow flow)
	{
	}

	private int GetSavedTutorialId()
	{
		return 0;
	}
}
