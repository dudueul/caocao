using UnityEngine;
using UnityEngine.EventSystems;

public class StateCollider : MonoBehaviour, IPointerClickHandler, IEventSystemHandler
{
	private int m_stateID;

	public int stateID
	{
		get
		{
			return 0;
		}
		private set
		{
		}
	}

	private void Start()
	{
	}

	public void OnPointerClick(PointerEventData eventData)
	{
	}
}
