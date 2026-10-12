using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiScrollbarGetValue : ComponentAction<Scrollbar>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat value;

		public bool everyFrame;

		private Scrollbar scrollbar;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetValue()
		{
		}
	}
}
