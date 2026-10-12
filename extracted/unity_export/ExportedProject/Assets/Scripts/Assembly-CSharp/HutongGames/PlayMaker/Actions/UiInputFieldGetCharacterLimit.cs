using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiInputFieldGetCharacterLimit : ComponentAction<InputField>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt characterLimit;

		public FsmEvent hasNoLimitEvent;

		public FsmEvent isLimitedEvent;

		public bool everyFrame;

		private InputField inputField;

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
