namespace HutongGames.PlayMaker.Actions
{
	public class SetFsmArrayItem : BaseFsmVariableIndexAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		public FsmString variableName;

		public FsmInt index;

		[RequiredField]
		public FsmVar value;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetFsmArray()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
