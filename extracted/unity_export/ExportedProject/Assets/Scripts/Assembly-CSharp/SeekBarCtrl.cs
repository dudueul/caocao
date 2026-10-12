using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

public class SeekBarCtrl : MonoBehaviour, IPointerDownHandler, IPointerUpHandler, IPointerEnterHandler, IPointerExitHandler, IDragHandler, IEventSystemHandler
{
	public MediaPlayerCtrl m_srcVideo;

	public Slider m_srcSlider;

	public float m_fDragTime;

	private bool m_bActiveDrag;

	private bool m_bUpdate;

	private float m_fDeltaTime;

	private float m_fLastValue;

	private float m_fLastSetValue;

	private void Start()
	{
	}

	private void Update()
	{
	}

	public void OnPointerEnter(PointerEventData eventData)
	{
	}

	public void OnPointerExit(PointerEventData eventData)
	{
	}

	public void OnPointerDown(PointerEventData eventData)
	{
	}

	public void OnPointerUp(PointerEventData eventData)
	{
	}

	public void OnDrag(PointerEventData eventData)
	{
	}
}
