using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiTextGetText : ComponentAction<Text>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString text;

		public bool everyFrame;

		private Text uiText;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetTextValue()
		{
		}
	}
}
