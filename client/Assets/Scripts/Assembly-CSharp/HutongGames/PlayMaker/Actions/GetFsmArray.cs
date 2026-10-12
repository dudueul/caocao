namespace HutongGames.PlayMaker.Actions
{
	public class GetFsmArray : BaseFsmVariableAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		public FsmString variableName;

		[RequiredField]
		public FsmArray storeValue;

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
