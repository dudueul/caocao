using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiInputFieldGetPlaceHolder : ComponentAction<InputField>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmGameObject placeHolder;

		public FsmBool placeHolderDefined;

		public FsmEvent foundEvent;

		public FsmEvent notFoundEvent;

		private InputField inputField;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetValue()
		{
		}
	}
}
