using BattleRecorder;

public class UndoActionClickHandler : ClickHandler
{
	public Tuple<TimelineMarker, UnitActionComplete> timeline;

	public bool eligible;

	private void OnEnable()
	{
	}
}
