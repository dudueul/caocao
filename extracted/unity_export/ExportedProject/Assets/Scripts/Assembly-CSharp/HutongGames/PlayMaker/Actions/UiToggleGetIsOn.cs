using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiToggleGetIsOn : ComponentAction<Toggle>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool value;

		public FsmEvent isOnEvent;

		public FsmEvent isOffEvent;

		public bool everyFrame;

		private Toggle _toggle;

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
