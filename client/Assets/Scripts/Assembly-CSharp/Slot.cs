using System;
using UnityEngine;

public class Slot : MonoBehaviour, IComparable
{
	private int m_order;

	private IntVector2 m_pos;

	private UnitData m_unitOnSlot;

	private UserUnit m_userUnitOnSlot;

	private int m_faceDirection;

	private int m_group;

	private int m_teamfaction;

	private int m_type;

	public int order
	{
		get
		{
			return 0;
		}
	}

	public IntVector2 pos
	{
		get
		{
			return default(IntVector2);
		}
	}

	public UnitData unitOnSlot
	{
		get
		{
			return null;
		}
	}

	public UserUnit userUnitOnSlot
	{
		get
		{
			return null;
		}
	}

	public int gridX
	{
		get
		{
			return 0;
		}
	}

	public int gridY
	{
		get
		{
			return 0;
		}
	}

	public int faceDirection
	{
		get
		{
			return 0;
		}
	}

	public int group
	{
		get
		{
			return 0;
		}
	}

	public int teamfaction
	{
		get
		{
			return 0;
		}
	}

	public int type
	{
		get
		{
			return 0;
		}
	}

	public Slot()
	{
	}

	public Slot(Slot input)
	{
	}

	public void SetDatas(int order, IntVector2 pos, int faceDir, int gr, int tf, int slotType)
	{
	}

	public void SetUnitOnSlot(UnitData unitData)
	{
	}

	public void SetUnitOnSlot(UnitData unitData, UserUnit userUnit)
	{
	}

	int IComparable.CompareTo(object inp)
	{
		return 0;
	}
}
