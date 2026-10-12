using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetVisibility : ComponentAction<Renderer>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool toggle;

		public FsmBool visible;

		public bool resetOnExit;

		private bool initialVisibility;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetVisibility(GameObject go)
		{
		}

		public override void OnExit()
		{
		}

		private void ResetVisibility()
		{
		}
	}
}
