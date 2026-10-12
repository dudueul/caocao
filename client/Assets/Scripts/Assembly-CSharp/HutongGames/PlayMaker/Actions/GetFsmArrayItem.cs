namespace HutongGames.PlayMaker.Actions
{
	public class GetFsmArrayItem : BaseFsmVariableIndexAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmString fsmName;

		[RequiredField]
		public FsmString variableName;

		public FsmInt index;

		[RequiredField]
		public FsmVar storeValue;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetFsmArray()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
