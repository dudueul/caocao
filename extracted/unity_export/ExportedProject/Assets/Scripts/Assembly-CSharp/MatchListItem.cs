using Multiplay;
using UnityEngine;

public class MatchListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public MultiplayUserData userData { get; set; }

		public virtual int tierGrade { get; private set; }

		public virtual int point { get; private set; }

		public MultiplayUserData opponentData { get; set; }

		public virtual int opponentTierGrade { get; private set; }

		public virtual int opponentPoint { get; private set; }

		public virtual void SetData(object data)
		{
		}
	}

	[SerializeField]
	private UIMultiplayUserData userDataUI;

	[SerializeField]
	private UIMultiplayUserData opponentDataUI;

	public CellData cellData
	{
		get
		{
			return null;
		}
		protected set
		{
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	public virtual void UpdateUI()
	{
	}

	public virtual void OnShare()
	{
	}

	public void OnBtnHostFace()
	{
	}

	public void OnBtnOpponentFace()
	{
	}
}
