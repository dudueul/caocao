using System.Collections.Generic;
using Caocao;
using UnityEngine;

public class UnitSpriteRotate : MonoBehaviour
{
	private UnitAnimation m_unitAnimation;

	private UnitMaterial m_unitMaterial;

	public int m_minDrag;

	private Vector3 m_dragStart;

	private int m_dirIdx;

	private List<UnitDirection> directions;

	private void Start()
	{
	}

	private void OnDestroy()
	{
	}

	private void OnDragStart()
	{
	}

	private void OnDragEnd()
	{
	}

	private void OnDrag(Vector2 delta)
	{
	}

	public void ChangeUnitAnimation(float diff)
	{
	}
}
