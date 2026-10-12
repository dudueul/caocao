using System;
using System.Collections.Generic;
using Caocao;
using UnityEngine;

public class BattleMapObject : MonoBehaviour
{
	public delegate void CurrentStateEventHandler(object sender, EventArgs args);

	public static int m_sortingOrderCounter;

	public const int destroyStatusId = 99;

	private static int ms_spawnStatusID;

	private int m_mapObjectId;

	private List<MapObjectData> m_mapObjectDatas;

	private UnitData m_unitData;

	private int m_redColorId;

	private int m_greenColorId;

	private int m_blueColorId;

	private MapObjectProperty m_property;

	private List<MapObjectConditionChecker> m_conditionChecker;

	private tk2dSpriteAnimator m_animator;

	private int m_currentState;

	private GameObject m_colorBox;

	private static string colorBoxName;

	private UnitSide m_owner;

	private CurrentStateEventHandler OnCurrentStateChanged__BackingField;

	private Material m_mapMaterial;

	public int mapObjectId
	{
		get
		{
			return 0;
		}
	}

	public List<MapObjectData> mapObjectDatas
	{
		get
		{
			return null;
		}
	}

	private MapObjectData m_pivot
	{
		get
		{
			return null;
		}
	}

	public IntVector2 pivotPos
	{
		get
		{
			return default(IntVector2);
		}
	}

	public int m_initState
	{
		get
		{
			return 0;
		}
	}

	public bool m_isUnit
	{
		get
		{
			return false;
		}
	}

	public UnitData unitData
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int m_levelCorrect
	{
		get
		{
			return 0;
		}
	}

	public EnumData.OccupyType m_occuType
	{
		get
		{
			return EnumData.OccupyType.IMPOSSIBLE;
		}
	}

	public bool m_isCastle
	{
		get
		{
			return false;
		}
	}

	public int redColorId
	{
		get
		{
			return 0;
		}
	}

	public int greenColorId
	{
		get
		{
			return 0;
		}
	}

	public int blueColorId
	{
		get
		{
			return 0;
		}
	}

	public MapObjectStatus currentStatus
	{
		get
		{
			return null;
		}
	}

	public List<MapObjectCondition> conditionProperty
	{
		get
		{
			return null;
		}
	}

	public List<MapObjectStatus> statusPropertyList
	{
		get
		{
			return null;
		}
	}

	private string curStateAnimation
	{
		get
		{
			return null;
		}
	}

	private tk2dSpriteAnimator animator
	{
		get
		{
			return null;
		}
	}

	public int currentState
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public UnitSide owner
	{
		get
		{
			return UnitSide.OWN;
		}
	}

	private Material mapMaterial
	{
		get
		{
			return null;
		}
	}

	public event CurrentStateEventHandler OnCurrentStateChanged
	{
		add
		{
		}
		remove
		{
		}
	}

	public void InitBattleMapObject(int mapObjectId, List<MapObjectData> mapObjectDatas, string sortingLayerName)
	{
	}

	private void SetColorBox()
	{
	}

	private void UpdateColorBoxSize()
	{
	}

	private void UpdateTileIds(object sender, EventArgs args)
	{
	}

	private void OnDestroy()
	{
	}

	public void ForceCallOnCurrentStateChanged()
	{
	}

	public void ArrangeHierarchy(Transform parent)
	{
	}

	public ErrorCode.MapObjectChangeStatus ChangeStatus(string type, string value, bool record = true)
	{
		return ErrorCode.MapObjectChangeStatus.SUCCEED;
	}

	public ErrorCode.MapObjectChangeStatus ChangeStatus(int newStatusId, bool record)
	{
		return ErrorCode.MapObjectChangeStatus.SUCCEED;
	}

	private void ChangeAnimationClip(object sender, EventArgs args)
	{
	}

	private void AnimationCompleted(tk2dSpriteAnimator caller, tk2dSpriteAnimationClip currentClip)
	{
	}

	private void CheckStatusProperty(object sender, EventArgs args)
	{
	}

	public UnitSide ChangeOwner(UnitSide ownerUnitSide = UnitSide.ENEMY)
	{
		return UnitSide.OWN;
	}

	private void Dye()
	{
	}

	public void UpdateConditionCheckers()
	{
	}

	public void DetachConditionCheckers()
	{
	}

	public static void UpdateMaterialColor()
	{
	}
}
