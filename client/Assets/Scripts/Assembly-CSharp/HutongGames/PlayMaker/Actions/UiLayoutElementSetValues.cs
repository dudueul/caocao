using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiLayoutElementSetValues : ComponentAction<LayoutElement>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat minWidth;

		public FsmFloat minHeight;

		public FsmFloat preferredWidth;

		public FsmFloat preferredHeight;

		public FsmFloat flexibleWidth;

		public FsmFloat flexibleHeight;

		public bool everyFrame;

		private LayoutElement layoutElement;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetValues()
		{
		}
	}
}
