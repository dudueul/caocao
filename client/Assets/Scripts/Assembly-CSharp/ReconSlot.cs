using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using Caocao;
using UnityEngine;
using UnityEngine.UI;

public class ReconSlot : MonoBehaviour
{
	public Image BgImage;

	public Image SlotImage;

	public Text SlotNumValue;

	public int groupNum;

	private int m_slotNum;

	private List<int> m_guildSiegeSlots;

	private Collider2D m_collider;

	private Sprite m_slotBGSprite;

	private UnitSide m_slotSide;

	private IEnumerator changeCoroutine;

	private const float m_blendingTerm = 0.15f;

	private int m_focusIndex;

	public int slotNum
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<int> guildSiegeSlots
	{
		get
		{
			return null;
		}
	}

	public Collider2D reconSlotCollider
	{
		get
		{
			return null;
		}
	}

	private Sprite slotBGSprite
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public UnitSide slotSide
	{
		get
		{
			return UnitSide.OWN;
		}
		set
		{
		}
	}

	private Sprite GetSlotSpriteByUnitSide(UnitSide unitSide)
	{
		return null;
	}

	public void SetTargetFocusing(bool isFocus)
	{
	}

	[DebuggerHidden]
	private IEnumerator ChangeTargetFocus()
	{
		return null;
	}
}
