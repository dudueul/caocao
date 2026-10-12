using System;
using UnityEngine;

[Serializable]
public class GuildUIMemberCategory : MonoBehaviour
{
	public interface IGuildUIMemberListBody
	{
		int CategoryLength { get; }

		EnumData.GuildMemberSortType GetSortType(int categoryIndex);

		void SetSortOrder(int categoryIndex, bool acsending);

		void SortGrid();
	}

	public GameObject objActivate;

	public GameObject objUpArrow;

	public GameObject objDownArrow;

	public int sortPiriority;

	public EnumData.GuildMemberSortType[] sortTypes;

	public UIButton rotateButton;

	public UILabelLocalized label;

	[NonSerialized]
	public GuildUIMemberCategory prev;

	[NonSerialized]
	public GuildUIMemberCategory next;

	private ClickHandler m_clickHandler;

	private Collider m_collider;

	private int m_rotateIndex;

	public EnumData.GuildMemberSortType sortType
	{
		get
		{
			return EnumData.GuildMemberSortType.Name;
		}
	}

	public IGuildUIMemberListBody body { get; private set; }

	public int customIndex { get; private set; }

	public int index { get; private set; }

	public Func<GuildUIMemberCategory, GuildUIMemberListObject.GuildMemberCellData, GuildUIMemberListObject.GuildMemberCellData, int> sortFunc { get; private set; }

	public bool isAscending
	{
		get
		{
			return false;
		}
	}

	public void Start()
	{
	}

	public void Initialize(IGuildUIMemberListBody body, Func<GuildUIMemberCategory, GuildUIMemberListObject.GuildMemberCellData, GuildUIMemberListObject.GuildMemberCellData, int> sortFunc, int customIndex = 0)
	{
	}

	public void SetActivate()
	{
	}

	public void SetOrderArrow(bool isAscending)
	{
	}

	public void SetCollider(bool enabled)
	{
	}

	public GuildUIMemberCategory GetCategory(int idx)
	{
		return null;
	}

	public void Release()
	{
	}

	public void OnRotate()
	{
	}
}
