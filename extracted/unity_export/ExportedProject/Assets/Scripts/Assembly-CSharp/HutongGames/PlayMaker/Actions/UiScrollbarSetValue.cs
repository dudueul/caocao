using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiScrollbarSetValue : ComponentAction<Scrollbar>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat value;

		public FsmBool resetOnExit;

		public bool everyFrame;

		private Scrollbar scrollbar;

		private float originalValue;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetValue()
		{
		}

		public override void OnExit()
		{
		}
	}
}
