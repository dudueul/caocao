using System.Collections.Generic;

public interface IKeyEventListener
{
	InputLayer depth { get; }

	void SetKeyEvent(IKeyEventData action);

	void UpdateEventStates();

	List<IKeyEventData> GetKeyEvents();
}
