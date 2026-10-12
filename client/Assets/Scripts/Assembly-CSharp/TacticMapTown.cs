using UnityEngine;
using UnityEngine.EventSystems;

public class TacticMapTown : MonoBehaviour, IPointerClickHandler, IEventSystemHandler
{
	private int m_townID;

	private GameObject attackableEffect;

	private static int? ms_colorPropId;

	protected MaterialPropertyBlock m_matProp;

	public int townID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	private void Awake()
	{
	}

	private void OnEnable()
	{
	}

	private void UpdateUI()
	{
	}

	public void OnPointerClick(PointerEventData eventData)
	{
	}
}
