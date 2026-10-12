namespace HutongGames.PlayMaker.Actions
{
	public class NguiLabelGetText : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString text;

		public bool everyFrame;

		private UILabel _label;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void GetText()
		{
		}
	}
}
