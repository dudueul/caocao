using System.Collections.Generic;
using UnityEngine;

public abstract class KeyEventListenerBase : MonoBehaviour, IKeyEventListener
{
	protected List<IKeyEventData> m_eventDatas;

	public abstract InputLayer depth { get; }

	protected abstract void OnInitKeyEventListener();

	protected virtual void Awake()
	{
	}

	public void SetKeyEvent(IKeyEventData eventData)
	{
	}

	public List<IKeyEventData> GetKeyEvents()
	{
		return null;
	}

	public void UpdateEventStates()
	{
	}
}
