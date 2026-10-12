using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiTextSetText : ComponentAction<Text>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString text;

		public FsmBool resetOnExit;

		public bool everyFrame;

		private Text uiText;

		private string originalString;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetTextValue()
		{
		}

		public override void OnExit()
		{
		}
	}
}
