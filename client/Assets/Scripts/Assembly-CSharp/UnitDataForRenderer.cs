using Caocao;
using UnityEngine;

public class UnitDataForRenderer : MonoBehaviour
{
	public bool isBattleUnit;

	public int bfIndex;

	private UnitMaterial m_unitMaterial;

	private IntVector2 m_gridPos;

	private IntVector2 m_reservedPos;

	private IntVector2 m_spawnGridPos;

	public virtual bool isCompleteAction
	{
		get
		{
			return false;
		}
	}

	public virtual bool isCompleteMove
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public virtual bool inProgressingFlow
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public virtual int unitId { get; set; }

	public virtual int lookUnitId { get; set; }

	public virtual int skinId { get; set; }

	public virtual int jobType { get; set; }

	public virtual int jobLevel { get; set; }

	public virtual UnitSide unitSide { get; set; }

	public virtual UnitDirection faceDirection { get; set; }

	private UnitMaterial unitMaterial
	{
		get
		{
			return null;
		}
	}

	public virtual IntVector2 gridPos
	{
		get
		{
			return default(IntVector2);
		}
	}

	public virtual IntVector2 reservedPos
	{
		get
		{
			return default(IntVector2);
		}
		set
		{
		}
	}

	public virtual IntVector2 spawnGridPos
	{
		get
		{
			return default(IntVector2);
		}
		set
		{
		}
	}

	protected UnitDataForRenderer()
	{
	}

	private void NotifyUnitDirectionChanged()
	{
	}

	public void NotifyUnitChanged()
	{
	}

	public virtual float GetResPercentage(EnumData.UnitResType resType)
	{
		return 0f;
	}

	protected virtual void Start()
	{
	}

	protected virtual void OnDestroy()
	{
	}

	public void ChangeShapeByUnitId(int shapeUnitId)
	{
	}
}
