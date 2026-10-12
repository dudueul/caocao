using Caocao;
using UnityEngine;
using nAI;

public class ExtremeUnitData : UnitData
{
	private UnitData m_parentData;

	private UserUnit m_userUnit;

	private Vector3 m_originLocalPosition;

	public UnitData parentData
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public override UserUnit userUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Vector3 originLocalPosition
	{
		get
		{
			return default(Vector3);
		}
		set
		{
		}
	}

	public override Ai unitAI
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public override UnitSide unitSide
	{
		get
		{
			return UnitSide.OWN;
		}
		set
		{
		}
	}

	public override UnitDirection faceDirection
	{
		get
		{
			return UnitDirection.back;
		}
		set
		{
		}
	}

	protected override void Start()
	{
	}

	private void OnUnitDirectionChanged(UnitAnimationBase animBase, UnitDirection unitDirection)
	{
	}

	public void AttachOnUnitDirectionChanged()
	{
	}

	public void DetachOnUnitDirectionChanged()
	{
	}

	protected override void OnDestroy()
	{
	}
}
