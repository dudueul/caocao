namespace HutongGames.PlayMaker.Actions
{
	public class SetFsmArray : BaseFsmVariableAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		public FsmString variableName;

		[RequiredField]
		public FsmArray setValue;

		public bool copyValues;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetFsmArrayCopy()
		{
		}
	}
}
