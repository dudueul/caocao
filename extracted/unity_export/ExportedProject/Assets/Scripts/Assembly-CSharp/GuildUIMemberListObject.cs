using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

[Serializable]
public class GuildUIMemberListObject : GuildUIMemberCategory.IGuildUIMemberListBody
{
	public class GuildMemberCellData : IReuseCellData
	{
		public int Index { get; set; }

		public GuildMember memberInfo { get; set; }

		public bool selected { get; set; }

		public virtual string GetCustomLabelText(int idx)
		{
			return null;
		}
	}

	public GuildUIMemberCategory[] categorys;

	public UIReuseGrid grid;

	public Action onSelectedItem;

	public Action onReselectedItem;

	protected List<GuildMember> listMember;

	protected List<GuildMemberCellData> listCellData;

	private bool initiated;

	private GuildUIMemberCategory frontCategory;

	private bool changedMember;

	private Func<GuildUIMemberCategory, GuildMemberCellData, GuildMemberCellData, int> defSortFunc;

	public GuildMemberCellData prevSelectedData { get; protected set; }

	public GuildMemberCellData selectedData { get; protected set; }

	public bool useSelection { get; set; }

	public int Count
	{
		get
		{
			return 0;
		}
	}

	public bool updatingGrid { get; private set; }

	public int CategoryLength
	{
		get
		{
			return 0;
		}
	}

	public EnumData.GuildMemberSortType GetSortType(int categoryIndex)
	{
		return EnumData.GuildMemberSortType.Name;
	}

	public void SetMembers(IEnumerable<GuildMember> listMember, bool updateGrid = true, bool copy = true)
	{
	}

	public void SetMembers(Dictionary<ulong, GuildApplicant> applicant, bool updateGrid = true)
	{
	}

	private void ApplicantToMember(Dictionary<ulong, GuildApplicant> applicant, bool updateGrid)
	{
	}

	public void Initiate()
	{
	}

	private void InitCategory()
	{
	}

	protected virtual Func<GuildUIMemberCategory, GuildMemberCellData, GuildMemberCellData, int> GenerateCustomSortFunc()
	{
		return null;
	}

	public void SetSortOrder(int categoryIdx, bool ascending)
	{
	}

	[DebuggerHidden]
	private IEnumerator InitMemberListUI()
	{
		return null;
	}

	private void UpdateSort()
	{
	}

	protected void Sort()
	{
	}

	protected virtual GuildMemberCellData GenerateCellData(int index, GuildMember member)
	{
		return null;
	}

	private void UpdateGrid()
	{
	}

	public void SortGrid()
	{
	}

	private void OnClickItem(int index)
	{
	}

	public void Destroy()
	{
	}
}
