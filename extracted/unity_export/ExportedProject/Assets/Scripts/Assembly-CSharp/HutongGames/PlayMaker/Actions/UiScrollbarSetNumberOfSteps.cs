using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiScrollbarSetNumberOfSteps : ComponentAction<Scrollbar>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt value;

		public FsmBool resetOnExit;

		public bool everyFrame;

		private Scrollbar scrollbar;

		private int originalValue;

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
