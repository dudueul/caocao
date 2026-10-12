namespace HutongGames.PlayMaker.Actions
{
	public class NguiLabelSetText : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

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

		private void SetText()
		{
		}
	}
}
